package com.matheusgermano.permutednumbers.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Round {
    private UUID id;
    private ArrayList<Guess> guesses;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArrayList<Guess> getGuesses() {
        return guesses;
    }

    public void setGuesses(ArrayList<Guess> guesses) {
        this.guesses = guesses;
    }
}
