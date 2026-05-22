package org.nvm.cars.dto;

import java.sql.Timestamp;

public class RegisterClienteDto {

    private String nome;

    private String cognome;

    private Timestamp dataDiNascita;

    private String numero;

    private String username;

    private String password;

    private String email;

    public RegisterClienteDto() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Timestamp getDataDiNascita() {
        return this.dataDiNascita;
    }

    public void setDataDiNascita(Timestamp dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
