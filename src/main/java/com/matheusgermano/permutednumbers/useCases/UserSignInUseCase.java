package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.IAuthAdapter;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;

import java.util.Optional;

public class UserSignInUseCase {
    private UsersRepository usersRepository;
    private ICryptoAdapter cryptoAdapter;
    private IAuthAdapter authAdapter;

    public UserSignInUseCase(UsersRepository usersRepository, ICryptoAdapter cryptoAdapter, IAuthAdapter authAdapter) {
        this.usersRepository = usersRepository;
        this.cryptoAdapter = cryptoAdapter;
        this.authAdapter = authAdapter;
    }

    public String execute(String email, String password) {
        User foundUser = usersRepository.findByEmail(email)
            .orElseThrow(() -> new Error("User not found with this e-mail or password"));

        boolean passwordsMatches = cryptoAdapter.matches(password, foundUser.getPassword());

        if (!passwordsMatches) {
            throw new Error("Unauthorized");
        }

        String token = authAdapter.generateToken(foundUser.getName(), foundUser.getId());

        return token;
    }
}
