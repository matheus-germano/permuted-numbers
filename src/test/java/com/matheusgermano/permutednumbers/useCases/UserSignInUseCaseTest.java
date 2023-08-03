package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserSignInUseCaseTest {
    @InjectMocks
    private UserSignInUseCase userSignInUseCase;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private ICryptoAdapter cryptoAdapter;

    @Test
    @DisplayName("Should throw an error if there is no user with provided e-mail")
    public void whenUserNotFoundWithProvidedEmail() {
        when(usersRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        Assertions.assertThrows(Error.class, () ->
                userSignInUseCase.execute("nonexistent@example.com", "123")
        );
    }

    @Test
    @DisplayName("Should throw an error if there is no user with provided e-mail")
    public void whenUserNotFoundWithProvidedPassword() {
        when(usersRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        Assertions.assertThrows(Error.class, () ->
                userSignInUseCase.execute("nonexistent@example.com", "123")
        );
    }
}