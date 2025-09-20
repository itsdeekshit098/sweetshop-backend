package com.Incubyte.sweetshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean admin; // optional: allow registering admin if you want, guard with env in prod
}