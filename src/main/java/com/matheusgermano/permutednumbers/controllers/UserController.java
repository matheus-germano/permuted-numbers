package com.matheusgermano.permutednumbers.controllers;

import com.matheusgermano.permutednumbers.dtos.SignInResponseDTO;
import com.matheusgermano.permutednumbers.dtos.UserSignInDTO;
import com.matheusgermano.permutednumbers.useCases.UserSignInUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("user")
public class UserController {
    private UserSignInUseCase userSignInUseCase;

    public UserController(UserSignInUseCase userSignInUseCase) {
        this.userSignInUseCase = userSignInUseCase;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody UserSignInDTO userSignInData) throws NoSuchAlgorithmException {
        String token = userSignInUseCase.execute(userSignInData.getEmail(), userSignInData.getPassword());

        SignInResponseDTO response = SignInResponseDTO.builder()
                .token(token)
                .build();

        return ResponseEntity.ok(response);
    }
}
