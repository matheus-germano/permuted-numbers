package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.adapters.AuthAdapter;
import com.matheusgermano.permutednumbers.adapters.CryptoAdapter;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.mocks.UserMocks;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserSignInUseCaseTest {
    @InjectMocks
    private UserSignInUseCase userSignInUseCase;
    @InjectMocks
    private CryptoAdapter cryptoAdapter;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private ICryptoAdapter iCryptoAdapter;
    @Mock
    private IAuthAdapter authAdapter;

    User mockedUser;

    @BeforeEach
    public void setup() throws NoSuchAlgorithmException {
        String encryptedPassword = cryptoAdapter.encrypt("123");
        mockedUser = UserMocks.foundUser(encryptedPassword);

        when(iCryptoAdapter.encrypt(any())).thenReturn(mockedUser.getPassword());
        when(iCryptoAdapter.matches(any(), any())).thenReturn(true);
        when(usersRepository.findByEmail(any())).thenReturn(Optional.of(mockedUser));
    }

    @Test
    @DisplayName("Should return a token when sign in is done successfully")
    public void whenSignInIsDoneSuccessfully() {
        when(authAdapter.generateToken(mockedUser.getName(), mockedUser.getId())).thenReturn("non-null-token");
        String token = userSignInUseCase.execute(mockedUser.getEmail(), "123");

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
        when(iCryptoAdapter.matches(anyString(), anyString())).thenReturn(false);

        Assertions.assertThatExceptionOfType(Error.class).isThrownBy(() ->
            userSignInUseCase.execute(mockedUser.getEmail(), "password")
        );
    }
}