package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.dtos.UserSignUpDTO;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;

import java.util.Optional;

public class UserSignUpUseCase {
    private UsersRepository usersRepository;

    public UserSignUpUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Boolean execute(UserSignUpDTO userToSignUp) {
        String userEmail = userToSignUp.getEmail();
        Optional<User> foundUser = usersRepository.findByEmail(userEmail);

        if (!foundUser.isEmpty()) {
            throw new Error("There is already a user with this provided e-mail");
        }

        return true;
    }
}
