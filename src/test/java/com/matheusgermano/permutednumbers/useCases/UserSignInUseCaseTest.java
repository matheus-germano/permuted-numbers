package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.IAuthAdapter;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserSignInUseCaseTest {
    @InjectMocks
    private UserSignInUseCase userSignInUseCase;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private ICryptoAdapter cryptoAdapter;
    @Mock
    private IAuthAdapter authAdapter;

    @BeforeEach
    public void setup() throws NoSuchAlgorithmException {
        String password = "123";
        String encryptedPassword = cryptoAdapter.encrypt(password);

        when(cryptoAdapter.encrypt(password)).thenReturn(encryptedPassword);
        when(cryptoAdapter.matches(password, encryptedPassword)).thenReturn(true);
        when(usersRepository.findByEmail("existent@example.com")).thenReturn(Optional.of(new User()));
    }

    @Test
    @DisplayName("Should return a token when sign in is done successfully")
    public void whenSignInIsDoneSuccessfully() {
        String token = userSignInUseCase.execute("existent@example.com", "123");

        Assertions.assertThat(token).isNotNull();
        Assertions.assertThat(token).isNotBlank();
    }

    @Test
    @DisplayName("Should throw an error if there is no user with provided e-mail")
    public void whenUserNotFoundWithProvidedEmail() {
        when(usersRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(Error.class).isThrownBy(()->
            userSignInUseCase.execute("nonexistent@example.com", "123")
        );
    }

    @Test
    @DisplayName("Should throw an error if there is no user with provided password")
    public void whenUserNotFoundWithProvidedPassword() {
        Assertions.assertThatExceptionOfType(Error.class).isThrownBy(()->
            userSignInUseCase.execute("nonexistent@example.com", "123")
        );
    }

    @Test
    @DisplayName("Should throw an error if raw password does not match with the hash")
    public void whenRawPasswordDoesNotMatchWithHash() {
        when(cryptoAdapter.matches(anyString(), anyString())).thenReturn(false);

        Assertions.assertThatExceptionOfType(Error.class)
            .isThrownBy(() ->
                userSignInUseCase.execute("nonexistent@example.com", "123")
            );
    }
}