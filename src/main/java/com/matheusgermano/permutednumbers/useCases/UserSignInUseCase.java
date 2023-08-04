package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;

import java.util.Optional;

public class UserSignInUseCase {
    private UsersRepository usersRepository;
    private ICryptoAdapter cryptoAdapter;

    public UserSignInUseCase(UsersRepository usersRepository, ICryptoAdapter cryptoAdapter) {
        this.usersRepository = usersRepository;
        this.cryptoAdapter = cryptoAdapter;
    }

    public String execute(String email, String password) {
        // com o Optional podemos fazer esse código aqui: evita fazer IF
        User foundUser = usersRepository.findByEmail(email)
                .orElseThrow(()-> new Error("User not found with this e-mail or password"));

        boolean passwordsMatches = cryptoAdapter.matches(password, foundUser.getPassword());

        if (!passwordsMatches) {
            throw new Error("Unauthorized");
        }

        return "";
    }
}
