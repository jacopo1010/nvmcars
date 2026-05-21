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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Elemento generato automaticamente: RigaPrenotazione
 */
@Entity
@Table(name = "righe_prenotazione")
public class RigaPrenotazione {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: note
     */
    @Column(name = "note", nullable = false)
    private String note;
    /**
     * Elemento generato automaticamente: prenotazioneAffiliata
     */
    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "automobile_id", nullable = true)
    private Automobile prenotazioneAffiliata;
    /**
     * Elemento generato automaticamente: righePrenotazione
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "servizio_id", nullable = false)
    private Servizio righePrenotazione;
    /**
     * Elemento generato automaticamente: prenotazione
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "prenotazione_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Prenotazione prenotazione;
    /**
     * Elemento generato automaticamente: prenotazioni
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "disponiblita_id", nullable = false)
    private Disponiblita prenotazioni;

    // --- COSTRUTTORE ---
    public RigaPrenotazione() {
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
     * Restituisce note.
     *
     * @return elemento generato automaticamente: note
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Imposta note.
     *
     * @param note elemento generato automaticamente: note
     */
    public void setNote(String note) {
        this.note = note;
    }
    /**
     * Restituisce prenotazioneAffiliata.
     *
     * @return elemento generato automaticamente: prenotazioneAffiliata
     */
    public Automobile getPrenotazioneAffiliata() {
        return this.prenotazioneAffiliata;
    }

    /**
     * Imposta prenotazioneAffiliata.
     *
     * @param prenotazioneAffiliata elemento generato automaticamente: prenotazioneAffiliata
     */
    public void setPrenotazioneAffiliata(Automobile prenotazioneAffiliata) {
        this.prenotazioneAffiliata = prenotazioneAffiliata;
    }
    /**
     * Restituisce righePrenotazione.
     *
     * @return elemento generato automaticamente: righePrenotazione
     */
    public Servizio getRighePrenotazione() {
        return this.righePrenotazione;
    }

    /**
     * Imposta righePrenotazione.
     *
     * @param righePrenotazione elemento generato automaticamente: righePrenotazione
     */
    public void setRighePrenotazione(Servizio righePrenotazione) {
        this.righePrenotazione = righePrenotazione;
    }
    /**
     * Restituisce prenotazione.
     *
     * @return elemento generato automaticamente: prenotazione
     */
    public Prenotazione getPrenotazione() {
        return this.prenotazione;
    }

    /**
     * Imposta prenotazione.
     *
     * @param prenotazione elemento generato automaticamente: prenotazione
     */
    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }
    /**
     * Restituisce prenotazioni.
     *
     * @return elemento generato automaticamente: prenotazioni
     */
    public Disponiblita getPrenotazioni() {
        return this.prenotazioni;
    }

    /**
     * Imposta prenotazioni.
     *
     * @param prenotazioni elemento generato automaticamente: prenotazioni
     */
    public void setPrenotazioni(Disponiblita prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
