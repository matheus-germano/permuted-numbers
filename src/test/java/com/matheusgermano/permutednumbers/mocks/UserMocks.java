package com.matheusgermano.permutednumbers.mocks;

import com.matheusgermano.permutednumbers.adapters.CryptoAdapter;
import com.matheusgermano.permutednumbers.dtos.UserSignInDTO;
import com.matheusgermano.permutednumbers.dtos.UserSignUpDTO;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import org.mockito.InjectMocks;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UserMocks {
    public static UUID id = UUID.randomUUID();

    public static User foundUser(String encryptedPassword) {
        return User.builder()
                .id(id)
                .name("Mocked Name")
                .email("mocked@email.com")
                .password(encryptedPassword)
                .build();
    }

    public static User existentUser() {
        return User.builder()
                .id(id)
                .name("Mocked Name")
                .email("mocked@email.com")
                .build();
    }

    public static UserSignUpDTO invalidUserToSignUp() {
        return UserSignUpDTO.builder()
                .name("Mocked Name")
                .email("mocked@email.com")
                .build();
    }

    public static UserSignUpDTO validUserToSignUp() {
        return UserSignUpDTO.builder()
                .name("Mocked Name")
                .profileAvatar("mockedProfile")
                .password("mockedPassword")
                .email("mocked@email.com")
                .build();
    }

    public static UserSignInDTO validUserToSignIn() {
        return UserSignInDTO.builder()
                .email("mocked@email.com")
                .password("mockedPassword")
                .build();
    }
}
