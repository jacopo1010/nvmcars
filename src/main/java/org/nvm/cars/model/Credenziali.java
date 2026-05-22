package org.nvm.cars.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Elemento generato automaticamente: Credenziali
 */
@Entity
@Table(name = "credenziali")
public class Credenziali {

    public static final String UTENTE = "UTENTE";
    public static final String TITOLARE = "TITOLARE";

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: username
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /**
     * Elemento generato automaticamente: password
     */
    @Column(name = "password", nullable = false, unique = false)
    private String password;
    /**
     * Elemento generato automaticamente: email
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    /**
     * Elemento generato automaticamente: ruolo
     */
    private String ruolo;
    /**
     * Elemento generato automaticamente: possiede
     */

    // --- COSTRUTTORE ---
    public Credenziali() {
    }

    // --- GETTER E SETTER ---
    /**
     * Restituisce id.
     *
     * @return elemento generato automaticamente: id
     */
    public Long getId() {
        return this.id;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Imposta id.
     *
     * @param id elemento generato automaticamente: id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Restituisce username.
     *
     * @return elemento generato automaticamente: username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Imposta username.
     *
     * @param username elemento generato automaticamente: username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Restituisce password.
     *
     * @return elemento generato automaticamente: password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Imposta password.
     *
     * @param password elemento generato automaticamente: password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Restituisce email.
     *
     * @return elemento generato automaticamente: email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Imposta email.
     *
     * @param email elemento generato automaticamente: email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
