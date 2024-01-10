package com.example.Gestion.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private int Id;
    private String full_name;
    private String email;
    private String password;
    private String role;



    public RegisterRequest(String full_name, String email, String password, String role) {
        this.full_name=full_name;
        this.email = email;
        this.password = password;
        this.role = role;

    }

}