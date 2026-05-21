package org.nvm.cars.model;

import java.math.BigDecimal;
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
 * Elemento generato automaticamente: Indirizzo
 */
@Entity
@Table(name = "indirizzo")
public class Indirizzo {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: via
     */
    @Column(name = "via", nullable = false)
    private String via;
    /**
     * Elemento generato automaticamente: civico
     */
    @Column(name = "civico", nullable = false)
    private String civico;
    /**
     * Elemento generato automaticamente: citta
     */
    @Column(name = "citta", nullable = false)
    private String citta;
    /**
     * Elemento generato automaticamente: cap
     */
    @Column(name = "cap", nullable = false)
    private String cap;
    /**
     * Elemento generato automaticamente: provincia
     */
    @Column(name = "provincia", nullable = false)
    private String provincia;
    /**
     * Elemento generato automaticamente: latitudine
     */
    @Column(name = "latitudine")
    private BigDecimal latitudine;
    /**
     * Elemento generato automaticamente: longitudine
     */
    @Column(name = "longitudine")
    private BigDecimal longitudine;
    /**
     * Elemento generato automaticamente: indirizzo
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "attivita_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attivita indirizzo;

    // --- COSTRUTTORE ---
    public Indirizzo() {
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
     * Restituisce via.
     *
     * @return elemento generato automaticamente: via
     */
    public String getVia() {
        return this.via;
    }

    /**
     * Imposta via.
     *
     * @param via elemento generato automaticamente: via
     */
    public void setVia(String via) {
        this.via = via;
    }
    /**
     * Restituisce civico.
     *
     * @return elemento generato automaticamente: civico
     */
    public String getCivico() {
        return this.civico;
    }

    /**
     * Imposta civico.
     *
     * @param civico elemento generato automaticamente: civico
     */
    public void setCivico(String civico) {
        this.civico = civico;
    }
    /**
     * Restituisce citta.
     *
     * @return elemento generato automaticamente: citta
     */
    public String getCitta() {
        return this.citta;
    }

    /**
     * Imposta citta.
     *
     * @param citta elemento generato automaticamente: citta
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }
    /**
     * Restituisce cap.
     *
     * @return elemento generato automaticamente: cap
     */
    public String getCap() {
        return this.cap;
    }

    /**
     * Imposta cap.
     *
     * @param cap elemento generato automaticamente: cap
     */
    public void setCap(String cap) {
        this.cap = cap;
    }
    /**
     * Restituisce provincia.
     *
     * @return elemento generato automaticamente: provincia
     */
    public String getProvincia() {
        return this.provincia;
    }

    /**
     * Imposta provincia.
     *
     * @param provincia elemento generato automaticamente: provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    /**
     * Restituisce latitudine.
     *
     * @return elemento generato automaticamente: latitudine
     */
    public BigDecimal getLatitudine() {
        return this.latitudine;
    }

    /**
     * Imposta latitudine.
     *
     * @param latitudine elemento generato automaticamente: latitudine
     */
    public void setLatitudine(BigDecimal latitudine) {
        this.latitudine = latitudine;
    }
    /**
     * Restituisce longitudine.
     *
     * @return elemento generato automaticamente: longitudine
     */
    public BigDecimal getLongitudine() {
        return this.longitudine;
    }

    /**
     * Imposta longitudine.
     *
     * @param longitudine elemento generato automaticamente: longitudine
     */
    public void setLongitudine(BigDecimal longitudine) {
        this.longitudine = longitudine;
    }
    /**
     * Restituisce indirizzo.
     *
     * @return elemento generato automaticamente: indirizzo
     */
    public Attivita getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Imposta indirizzo.
     *
     * @param indirizzo elemento generato automaticamente: indirizzo
     */
    public void setIndirizzo(Attivita indirizzo) {
        this.indirizzo = indirizzo;
    }
}
