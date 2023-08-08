package com.matheusgermano.permutednumbers.protocols;

import java.security.NoSuchAlgorithmException;

public interface ICryptoAdapter {
    public String encrypt(String password) throws NoSuchAlgorithmException;
    public boolean matches(String password, String hash) throws NoSuchAlgorithmException;
}
