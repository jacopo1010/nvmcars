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
 * Elemento generato automaticamente: Titolare
 */
@Entity
@Table(name = "titolari")
public class Titolare {

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
     * Elemento generato automaticamente: regioneSociale
     */
    @Column(name = "regione_sociale", nullable = false, unique = true)
    private String regioneSociale;
    /**
     * Elemento generato automaticamente: partitaIva
     */
    @Column(name = "partita_iva", nullable = false, unique = true)
    private String partitaIva;
    /**
     * Elemento generato automaticamente: codiceFiscale
     */
    @Column(name = "codice_fiscale", nullable = false, unique = true)
    private String codiceFiscale;
    /**
     * Elemento generato automaticamente: attivita
     */
    @OneToMany(mappedBy = "attivita", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    private List<Attivita> attivita = new ArrayList<>();
    /**
     * Elemento generato automaticamente: possiede
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "credenziali_id", nullable = false)
    private Credenziali possiede;

    // --- COSTRUTTORE ---
    public Titolare() {
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
     * Restituisce regioneSociale.
     *
     * @return elemento generato automaticamente: regioneSociale
     */
    public String getRegioneSociale() {
        return this.regioneSociale;
    }

    /**
     * Imposta regioneSociale.
     *
     * @param regioneSociale elemento generato automaticamente: regioneSociale
     */
    public void setRegioneSociale(String regioneSociale) {
        this.regioneSociale = regioneSociale;
    }
    /**
     * Restituisce partitaIva.
     *
     * @return elemento generato automaticamente: partitaIva
     */
    public String getPartitaIva() {
        return this.partitaIva;
    }

    /**
     * Imposta partitaIva.
     *
     * @param partitaIva elemento generato automaticamente: partitaIva
     */
    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }
    /**
     * Restituisce codiceFiscale.
     *
     * @return elemento generato automaticamente: codiceFiscale
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Imposta codiceFiscale.
     *
     * @param codiceFiscale elemento generato automaticamente: codiceFiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    /**
     * Restituisce attivita.
     *
     * @return elemento generato automaticamente: attivita
     */
    public List<Attivita> getAttivita() {
        return this.attivita;
    }

    /**
     * Imposta attivita.
     *
     * @param attivita elemento generato automaticamente: attivita
     */
    public void setAttivita(List<Attivita> attivita) {
        this.attivita = attivita;
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
}
