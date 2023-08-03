package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;

public class UserSignInUseCase {
    private UsersRepository usersRepository;
    private ICryptoAdapter cryptoAdapter;

    public UserSignInUseCase(UsersRepository usersRepository, ICryptoAdapter cryptoAdapter) {
        this.usersRepository = usersRepository;
        this.cryptoAdapter = cryptoAdapter;
    }

    public String execute(String email, String password) {
        User foundUser = usersRepository.findByEmail(email);

        if (foundUser == null) {
            throw new Error("User not found with this e-mail or password");
        }

        boolean passwordsMatches = cryptoAdapter.matches(password, foundUser.getPassword());

        if (!passwordsMatches) {
            throw new Error("User not found with this e-mail or password");
        }

        return "";
    }
}
