package com.matheusgermano.permutednumbers.controllers;

import com.matheusgermano.permutednumbers.dtos.SignInResponseDTO;
import com.matheusgermano.permutednumbers.mocks.UserMocks;
import com.matheusgermano.permutednumbers.useCases.UserSignInUseCase;
import com.matheusgermano.permutednumbers.useCases.UserSignUpUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserSignInUseCase userSignInUseCase;
    @Mock
    private UserSignUpUseCase userSignUpUseCase;

    @BeforeEach
    public void setup() throws NoSuchAlgorithmException {
        when(userSignInUseCase.execute(any(), any())).thenReturn("returnedToken");
    }

    @Test
    @DisplayName("Should return a object with auth token when sign in successfully")
    public void whenUserSignInSuccessfully() throws NoSuchAlgorithmException {
        SignInResponseDTO response = userController.signIn(UserMocks.validUserToSignIn()).getBody();

        Assertions.assertThat(response.getToken()).isNotNull();
        Assertions.assertThat(response.getToken()).isNotBlank();
    }
}