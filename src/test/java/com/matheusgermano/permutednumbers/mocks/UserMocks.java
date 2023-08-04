package com.matheusgermano.permutednumbers.mocks;

import com.matheusgermano.permutednumbers.adapters.CryptoAdapter;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import org.mockito.InjectMocks;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UserMocks {

    public static User foundUser(String encryptedPassword) throws NoSuchAlgorithmException {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName("Mocked Name");
        user.setEmail("mocked@email.com");
        user.setPassword(encryptedPassword);

        return user;
    }
}
