package org.nvm.cars.model;

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
 * Elemento generato automaticamente: Attivita
 */
@Entity
@Table(name = "attivita_registrate")
public class Attivita {

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
     * Elemento generato automaticamente: descrizione
     */
    @Column(name = "descrizione")
    private String descrizione;
    /**
     * Elemento generato automaticamente: numero
     */
    @Column(name = "numero", nullable = false)
    private String numero;
    /**
     * Elemento generato automaticamente: email
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    /**
     * Elemento generato automaticamente: titolare
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "titolare_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Titolare titolare;
    /**
     * Elemento generato automaticamente: indirizzo
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "indirizzo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Indirizzo indirizzo;
    /**
     * Elemento generato automaticamente: listino
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "listino_servizi_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ListinoServizi listino;
    /**
     * Elemento generato automaticamente: disponibilita
     */
    @OneToMany(mappedBy = "attivita", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    private List<Disponibilita> disponibilita = new ArrayList<>();

    // --- COSTRUTTORE ---
    public Attivita() {
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
     * Restituisce descrizione.
     *
     * @return elemento generato automaticamente: descrizione
     */
    public String getDescrizione() {
        return this.descrizione;
    }

    /**
     * Imposta descrizione.
     *
     * @param descrizione elemento generato automaticamente: descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
     * Restituisce titolare.
     *
     * @return elemento generato automaticamente: titolare
     */
    public Titolare getTitolare() {
        return this.titolare;
    }

    /**
     * Imposta titolare.
     *
     * @param titolare elemento generato automaticamente: titolare
     */
    public void setTitolare(Titolare titolare) {
        this.titolare = titolare;
    }
    /**
     * Restituisce indirizzo.
     *
     * @return elemento generato automaticamente: indirizzo
     */
    public Indirizzo getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Imposta indirizzo.
     *
     * @param indirizzo elemento generato automaticamente: indirizzo
     */
    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }
    /**
     * Restituisce listino.
     *
     * @return elemento generato automaticamente: listino
     */
    public ListinoServizi getListino() {
        return this.listino;
    }

    /**
     * Imposta listino.
     *
     * @param listino elemento generato automaticamente: listino
     */
    public void setListino(ListinoServizi listino) {
        this.listino = listino;
    }
    /**
     * Restituisce disponibilita.
     *
     * @return elemento generato automaticamente: disponibilita
     */
    public List<Disponibilita> getDisponibilita() {
        return this.disponibilita;
    }

    /**
     * Imposta disponibilita.
     *
     * @param disponibilita elemento generato automaticamente: disponibilita
     */
    public void setDisponibilita(List<Disponibilita> disponibilita) {
        this.disponibilita = disponibilita;
    }
}
