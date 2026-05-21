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
 * Elemento generato automaticamente: Disponiblita
 */
@Entity
@Table(name = "disponibilita")
public class Disponiblita {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: dataOraInizio
     */
    @Column(name = "data_ora_inizio", nullable = false)
    private Timestamp dataOraInizio;
    /**
     * Elemento generato automaticamente: dataOraFine
     */
    @Column(name = "data_ora_fine", nullable = false)
    private Timestamp dataOraFine;
    /**
     * Elemento generato automaticamente: postiDisponibili
     */
    @Column(name = "posti_disponibili", nullable = false)
    private Integer postiDisponibili;
    /**
     * Elemento generato automaticamente: attivita
     */
    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "attivita_id")
    private Attivita attivita;
    /**
     * Elemento generato automaticamente: disponibilita
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "servizio_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Servizio disponibilita;
    /**
     * Elemento generato automaticamente: prenotazioni
     */
    @OneToMany(mappedBy = "prenotazioni", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<RigaPrenotazione> prenotazioni = new ArrayList<>();

    // --- COSTRUTTORE ---
    public Disponiblita() {
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
     * Restituisce dataOraInizio.
     *
     * @return elemento generato automaticamente: dataOraInizio
     */
    public Timestamp getDataOraInizio() {
        return this.dataOraInizio;
    }

    /**
     * Imposta dataOraInizio.
     *
     * @param dataOraInizio elemento generato automaticamente: dataOraInizio
     */
    public void setDataOraInizio(Timestamp dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }
    /**
     * Restituisce dataOraFine.
     *
     * @return elemento generato automaticamente: dataOraFine
     */
    public Timestamp getDataOraFine() {
        return this.dataOraFine;
    }

    /**
     * Imposta dataOraFine.
     *
     * @param dataOraFine elemento generato automaticamente: dataOraFine
     */
    public void setDataOraFine(Timestamp dataOraFine) {
        this.dataOraFine = dataOraFine;
    }
    /**
     * Restituisce postiDisponibili.
     *
     * @return elemento generato automaticamente: postiDisponibili
     */
    public Integer getPostiDisponibili() {
        return this.postiDisponibili;
    }

    /**
     * Imposta postiDisponibili.
     *
     * @param postiDisponibili elemento generato automaticamente: postiDisponibili
     */
    public void setPostiDisponibili(Integer postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }
    /**
     * Restituisce attivita.
     *
     * @return elemento generato automaticamente: attivita
     */
    public Attivita getAttivita() {
        return this.attivita;
    }

    /**
     * Imposta attivita.
     *
     * @param attivita elemento generato automaticamente: attivita
     */
    public void setAttivita(Attivita attivita) {
        this.attivita = attivita;
    }
    /**
     * Restituisce disponibilita.
     *
     * @return elemento generato automaticamente: disponibilita
     */
    public Servizio getDisponibilita() {
        return this.disponibilita;
    }

    /**
     * Imposta disponibilita.
     *
     * @param disponibilita elemento generato automaticamente: disponibilita
     */
    public void setDisponibilita(Servizio disponibilita) {
        this.disponibilita = disponibilita;
    }
    /**
     * Restituisce prenotazioni.
     *
     * @return elemento generato automaticamente: prenotazioni
     */
    public List<RigaPrenotazione> getPrenotazioni() {
        return this.prenotazioni;
    }

    /**
     * Imposta prenotazioni.
     *
     * @param prenotazioni elemento generato automaticamente: prenotazioni
     */
    public void setPrenotazioni(List<RigaPrenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
