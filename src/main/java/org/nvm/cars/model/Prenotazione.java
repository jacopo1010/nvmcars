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
 * Elemento generato automaticamente: Prenotazione
 */
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: dataCreazione
     */
    @Column(name = "data_creazione", nullable = false)
    private Timestamp dataCreazione;
    /**
     * Elemento generato automaticamente: stato
     */
    @Column(name = "stato", nullable = false)
    private String stato;
    /**
     * Elemento generato automaticamente: prenotazioni
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "cliente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente prenotazioni;
    /**
     * Elemento generato automaticamente: rigaPrenotazione
     */
    @OneToMany(mappedBy = "prenotazione", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    private List<RigaPrenotazione> rigaPrenotazione = new ArrayList<>();

    // --- COSTRUTTORE ---
    public Prenotazione() {
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
     * Restituisce dataCreazione.
     *
     * @return elemento generato automaticamente: dataCreazione
     */
    public Timestamp getDataCreazione() {
        return this.dataCreazione;
    }

    /**
     * Imposta dataCreazione.
     *
     * @param dataCreazione elemento generato automaticamente: dataCreazione
     */
    public void setDataCreazione(Timestamp dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
    /**
     * Restituisce stato.
     *
     * @return elemento generato automaticamente: stato
     */
    public String getStato() {
        return this.stato;
    }

    /**
     * Imposta stato.
     *
     * @param stato elemento generato automaticamente: stato
     */
    public void setStato(String stato) {
        this.stato = stato;
    }
    /**
     * Restituisce prenotazioni.
     *
     * @return elemento generato automaticamente: prenotazioni
     */
    public Cliente getPrenotazioni() {
        return this.prenotazioni;
    }

    /**
     * Imposta prenotazioni.
     *
     * @param prenotazioni elemento generato automaticamente: prenotazioni
     */
    public void setPrenotazioni(Cliente prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
    /**
     * Restituisce rigaPrenotazione.
     *
     * @return elemento generato automaticamente: rigaPrenotazione
     */
    public List<RigaPrenotazione> getRigaPrenotazione() {
        return this.rigaPrenotazione;
    }

    /**
     * Imposta rigaPrenotazione.
     *
     * @param rigaPrenotazione elemento generato automaticamente: rigaPrenotazione
     */
    public void setRigaPrenotazione(List<RigaPrenotazione> rigaPrenotazione) {
        this.rigaPrenotazione = rigaPrenotazione;
    }
}
