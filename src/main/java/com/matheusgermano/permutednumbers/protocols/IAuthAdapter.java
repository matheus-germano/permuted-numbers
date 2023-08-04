package com.matheusgermano.permutednumbers.protocols;

import com.matheusgermano.permutednumbers.entities.User;

import java.util.UUID;

public interface IAuthAdapter {
    public String generateToken(String name, UUID id);
    public boolean isValid(String token);
}
