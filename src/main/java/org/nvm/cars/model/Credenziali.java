package org.nvm.cars.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Elemento generato automaticamente: Credenziali
 */
@Entity
@Table(name = "credenziali")
public class Credenziali {

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
    @Column(name = "password", nullable = false, length = 12, unique = true)
    private String password;
    /**
     * Elemento generato automaticamente: email
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    /**
     * Elemento generato automaticamente: possiede
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "titolare_id", nullable = false)
    private Titolare possiede;

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
    /**
     * Restituisce possiede.
     *
     * @return elemento generato automaticamente: possiede
     */
    public Titolare getPossiede() {
        return this.possiede;
    }

    /**
     * Imposta possiede.
     *
     * @param possiede elemento generato automaticamente: possiede
     */
    public void setPossiede(Titolare possiede) {
        this.possiede = possiede;
    }
}
