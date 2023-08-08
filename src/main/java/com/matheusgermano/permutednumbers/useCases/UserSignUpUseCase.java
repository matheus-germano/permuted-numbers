package com.matheusgermano.permutednumbers.useCases;

import com.matheusgermano.permutednumbers.dtos.UserSignUpDTO;
import com.matheusgermano.permutednumbers.entities.User;
import com.matheusgermano.permutednumbers.protocols.ICryptoAdapter;
import com.matheusgermano.permutednumbers.repositories.UsersRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserSignUpUseCase {
    private UsersRepository usersRepository;
    private ICryptoAdapter cryptoAdapter;

    public UserSignUpUseCase(UsersRepository usersRepository, ICryptoAdapter cryptoAdapter) {
        this.usersRepository = usersRepository;
        this.cryptoAdapter = cryptoAdapter;
    }

    public Boolean execute(UserSignUpDTO userToSignUp) throws NoSuchAlgorithmException {
        String userEmail = userToSignUp.getEmail();
        Optional<User> foundUser = usersRepository.findByEmail(userEmail);

        if (!foundUser.isEmpty()) {
            throw new Error("There is already a user with this provided e-mail");
        }

        String encryptedPassword = cryptoAdapter.encrypt(userToSignUp.getPassword());
        userToSignUp.setPassword(encryptedPassword);

        User user = User.builder()
                .name(userToSignUp.getName())
                .email(userToSignUp.getEmail())
                .password(userToSignUp.getPassword())
                .profileAvatar(userToSignUp.getProfileAvatar())
                .build();

        User createdUser = usersRepository.save(user);

        if (createdUser == null) {
            return false;
        }

        return true;
    }
}
