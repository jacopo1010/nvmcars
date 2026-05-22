package org.nvm.cars.dto;

public class AuthDto {

    public String token;

    public String username;

    public String password;

    public String getRuolo() {
        return Ruolo;
    }

    public void setRuolo(String ruolo) {
        Ruolo = ruolo;
    }

    public String Ruolo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
