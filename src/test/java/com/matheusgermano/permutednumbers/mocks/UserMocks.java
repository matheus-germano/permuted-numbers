package com.matheusgermano.permutednumbers.mocks;

import com.matheusgermano.permutednumbers.adapters.CryptoAdapter;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UserMocks {
    private static ICryptoAdapter cryptoAdapter;

    public static User foundUser() throws NoSuchAlgorithmException {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setName("Mocked Name");
        user.setEmail("mocked@email.com");
        user.setPassword(cryptoAdapter.encrypt("123"));

        return user;
    }
}
