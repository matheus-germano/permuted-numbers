package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.dtos.UserSignUpDTO;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.mocks.UserMocks;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserSignUpUseCasesTest {
    @InjectMocks
    private UserSignUpUseCase userSignUpUseCase;
    @Mock
    private UsersRepository usersRepository;

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
}
