package org.nvm.cars.model;

import java.math.BigDecimal;
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
 * Elemento generato automaticamente: Servizio
 */
@Entity
@Table(name = "servizi")
public class Servizio {

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
     * Elemento generato automaticamente: prezzo
     */
    @Column(name = "prezzo", nullable = false)
    private BigDecimal prezzo;
    /**
     * Elemento generato automaticamente: durata
     */
    @Column(name = "durata", nullable = false)
    private Integer durata;
    /**
     * Elemento generato automaticamente: servizi
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "listino_servizi_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ListinoServizi servizi;
    /**
     * Elemento generato automaticamente: disponibilita
     */
    @OneToMany(mappedBy = "servizio", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    private List<Disponibilita> disponibilita = new ArrayList<>();
    /**
     * Elemento generato automaticamente: righePrenotazione
     */
    @OneToMany(mappedBy = "righePrenotazione", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<RigaPrenotazione> righePrenotazione = new ArrayList<>();

    // --- COSTRUTTORE ---
    public Servizio() {
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
     * Restituisce prezzo.
     *
     * @return elemento generato automaticamente: prezzo
     */
    public BigDecimal getPrezzo() {
        return this.prezzo;
    }

    /**
     * Imposta prezzo.
     *
     * @param prezzo elemento generato automaticamente: prezzo
     */
    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    /**
     * Restituisce durata.
     *
     * @return elemento generato automaticamente: durata
     */
    public Integer getDurata() {
        return this.durata;
    }

    /**
     * Imposta durata.
     *
     * @param durata elemento generato automaticamente: durata
     */
    public void setDurata(Integer durata) {
        this.durata = durata;
    }
    /**
     * Restituisce servizi.
     *
     * @return elemento generato automaticamente: servizi
     */
    public ListinoServizi getServizi() {
        return this.servizi;
    }

    /**
     * Imposta servizi.
     *
     * @param servizi elemento generato automaticamente: servizi
     */
    public void setServizi(ListinoServizi servizi) {
        this.servizi = servizi;
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
    /**
     * Restituisce righePrenotazione.
     *
     * @return elemento generato automaticamente: righePrenotazione
     */
    public List<RigaPrenotazione> getRighePrenotazione() {
        return this.righePrenotazione;
    }

    /**
     * Imposta righePrenotazione.
     *
     * @param righePrenotazione elemento generato automaticamente: righePrenotazione
     */
    public void setRighePrenotazione(List<RigaPrenotazione> righePrenotazione) {
        this.righePrenotazione = righePrenotazione;
    }
}
