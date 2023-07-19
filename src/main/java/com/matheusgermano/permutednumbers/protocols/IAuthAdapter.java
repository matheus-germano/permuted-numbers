package com.matheusgermano.permutednumbers.protocols;

import com.matheusgermano.permutednumbers.entities.User;

public interface IAuthAdapter {
    public String generateToken(User user);
    public boolean isValid(String token);
}
