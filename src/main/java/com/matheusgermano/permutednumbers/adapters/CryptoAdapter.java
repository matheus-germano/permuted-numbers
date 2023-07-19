package com.matheusgermano.permutednumbers.adapters;

import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoAdapter implements ICryptoAdapter {
    @Override
    public String encrypt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        throw new Error("Password is invalid");
    }

    @Override
    public boolean matches(String password, String hash) {
        String encryptedPassword = this.encrypt(password);
        boolean passwordsMatches = encryptedPassword.equals(hash);

        return passwordsMatches;
    }
}
