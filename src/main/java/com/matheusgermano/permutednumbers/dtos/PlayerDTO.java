package com.matheusgermano.permutednumbers.dtos;

import java.util.UUID;

public class PlayerDTO {
    private UUID id;
    private String name;
    private String profileAvatar;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileAvatar() {
        return profileAvatar;
    }

    public void setProfileAvatar(String profileAvatar) {
        this.profileAvatar = profileAvatar;
    }
}
