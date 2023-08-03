package com.matheusgermano.permutednumbers.entities;

import java.util.UUID;

public class Guess {
    private UUID userId;
    private String guessNumber;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(String guessNumber) {
        this.guessNumber = guessNumber;
    }
}
