package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.adapters.CryptoAdapter;
import com.matheusgermano.permutednumbers.dtos.UserSignUpDTO;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.mocks.UserMocks;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserSignUpUseCaseTest {
    @InjectMocks
    private UserSignUpUseCase userSignUpUseCase;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private ICryptoAdapter cryptoAdapter;

    @Test
    @DisplayName("Should throw an error if there is already a user with provided e-mail")
    public void whenProvidedEmailAlreadyExists() {
        User existentUser = UserMocks.existentUser();
        UserSignUpDTO invalidUserToSignUp = UserMocks.invalidUserToSignUp();

        when(usersRepository.findByEmail(invalidUserToSignUp.getEmail())).thenReturn(Optional.of(existentUser));

        Assertions.assertThatExceptionOfType(Error.class).isThrownBy(()->
                userSignUpUseCase.execute(invalidUserToSignUp)
        );
    }

    @Test
    @DisplayName("Should encrypt raw password")
    public void whenEncryptRawPassword() throws NoSuchAlgorithmException {
        CryptoAdapter crypto = new CryptoAdapter();
        String rawPassword = "123";
        String encryptedPassword = crypto.encrypt(rawPassword);

        when(cryptoAdapter.encrypt(rawPassword)).thenReturn(encryptedPassword);
    }

    @Test
    @DisplayName("Should throw an error if encrypted password is wrong")
    public void whenEncryptPasswordIsWrong() throws NoSuchAlgorithmException {
        when(cryptoAdapter.encrypt(any())).thenThrow(RuntimeException.class);

        Assertions.assertThatExceptionOfType(Exception.class)
                .isThrownBy(() ->
                        userSignUpUseCase.execute(UserSignUpDTO.builder().build())
                );
    }

    @Test
    @DisplayName("Should return true if user is created successfully")
    public void whenUserIsCreatedSuccessfully() throws NoSuchAlgorithmException {
        when(userSignUpUseCase.execute(UserMocks.validUserToSignUp())).thenReturn(true);
    }
}
