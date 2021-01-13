package com.example.filmland_subscription_microservice.dto;

import javax.validation.constraints.NotNull;

public class SubscribeDTO {
    @NotNull
    private String email;

    @NotNull
    private String availableCategory;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailableCategory() {
        return availableCategory;
    }

    public void setAvailableCategory(String availableCategory) {
        this.availableCategory = availableCategory;
    }
}
