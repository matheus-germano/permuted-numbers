package com.matheusgermano.permutednumbers.entities;

import com.matheusgermano.permutednumbers.dtos.PlayerDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Game {
    public enum Difficulty {
        EASY,
        NORMAL,
        HARD
    }

    private UUID id;
    private Difficulty difficulty;
    private String drawnNumber;
    private ArrayList<PlayerDTO> players;
    private ArrayList<Round> rounds;
    private Guess winner;
    private Date startedAt;
    private Date endedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDrawnNumber() {
        return drawnNumber;
    }

    public void setDrawnNumber(String drawnNumber) {
        this.drawnNumber = drawnNumber;
    }

    public ArrayList<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerDTO> players) {
        this.players = players;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public Guess getWinner() {
        return winner;
    }

    public void setWinner(Guess winner) {
        this.winner = winner;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }
}
