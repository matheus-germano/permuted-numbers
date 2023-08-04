package com.matheusgermano.permutednumbers.adapters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.IAuthAdapter;

import java.util.UUID;

public class AuthAdapter implements IAuthAdapter {
    @Override
    public String generateToken(String name, UUID id) {
        Algorithm algorithm = Algorithm.HMAC256("permuted@numbers");
        return JWT.create()
            .withIssuer("auth0")
            .withSubject(name)
            .withClaim("id", String.valueOf(id))
            .sign(algorithm);
    }

    @Override
    public boolean isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("permuted@numbers");
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);

            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }
}
