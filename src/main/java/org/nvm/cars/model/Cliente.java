package org.nvm.cars.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Elemento generato automaticamente: Cliente
 */
@Entity
@Table(name = "clienti")
public class Cliente {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: nome
     */
    @Column(name = "nome", nullable = false)
    private String nome;
    /**
     * Elemento generato automaticamente: cognome
     */
    @Column(name = "cognome", nullable = false)
    private String cognome;
    /**
     * Elemento generato automaticamente: dataDiNascita
     */
    @Column(name = "data_di_nascita", nullable = false)
    private Timestamp dataDiNascita;
    /**
     * Elemento generato automaticamente: numero
     */
    @Column(name = "numero", nullable = false)
    private String numero;
    /**
     * Elemento generato automaticamente: possiede
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "credenziali_id", nullable = false)
    private Credenziali possiede;
    /**
     * Elemento generato automaticamente: prenotazioni
     */
    @OneToMany(mappedBy = "prenotazioni", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    // --- COSTRUTTORE ---
    public Cliente() {
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
     * Restituisce nome.
     *
     * @return elemento generato automaticamente: nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Imposta nome.
     *
     * @param nome elemento generato automaticamente: nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce cognome.
     *
     * @return elemento generato automaticamente: cognome
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Imposta cognome.
     *
     * @param cognome elemento generato automaticamente: cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * Restituisce dataDiNascita.
     *
     * @return elemento generato automaticamente: dataDiNascita
     */
    public Timestamp getDataDiNascita() {
        return this.dataDiNascita;
    }

    /**
     * Imposta dataDiNascita.
     *
     * @param dataDiNascita elemento generato automaticamente: dataDiNascita
     */
    public void setDataDiNascita(Timestamp dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    /**
     * Restituisce numero.
     *
     * @return elemento generato automaticamente: numero
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * Imposta numero.
     *
     * @param numero elemento generato automaticamente: numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * Restituisce possiede.
     *
     * @return elemento generato automaticamente: possiede
     */
    public Credenziali getPossiede() {
        return this.possiede;
    }

    /**
     * Imposta possiede.
     *
     * @param possiede elemento generato automaticamente: possiede
     */
    public void setPossiede(Credenziali possiede) {
        this.possiede = possiede;
    }
    /**
     * Restituisce prenotazioni.
     *
     * @return elemento generato automaticamente: prenotazioni
     */
    public List<Prenotazione> getPrenotazioni() {
        return this.prenotazioni;
    }

    /**
     * Imposta prenotazioni.
     *
     * @param prenotazioni elemento generato automaticamente: prenotazioni
     */
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
