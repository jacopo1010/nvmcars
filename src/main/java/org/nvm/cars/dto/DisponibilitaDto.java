package org.nvm.cars.dto;


import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Disponibilita.
 */
public class DisponibilitaDto {

    private Long id;

    private Timestamp dataOraInizio;

    private Timestamp dataOraFine;

    private Integer postiDisponibili;

    private Long attivitaId;

    private Long servizioId;

    private List<Long> prenotazioniIds = new ArrayList<>();

    public DisponibilitaDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataOraInizio() {
        return this.dataOraInizio;
    }

    public void setDataOraInizio(Timestamp dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }

    public Timestamp getDataOraFine() {
        return this.dataOraFine;
    }

    public void setDataOraFine(Timestamp dataOraFine) {
        this.dataOraFine = dataOraFine;
    }

    public Integer getPostiDisponibili() {
        return this.postiDisponibili;
    }

    public void setPostiDisponibili(Integer postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public Long getAttivitaId() {
        return this.attivitaId;
    }

    public void setAttivitaId(Long attivitaId) {
        this.attivitaId = attivitaId;
    }

    public Long getServizioId() {
        return this.servizioId;
    }

    public void setServizioId(Long servizioId) {
        this.servizioId = servizioId;
    }

    public List<Long> getPrenotazioniIds() {
        return this.prenotazioniIds;
    }

    public void setPrenotazioniIds(List<Long> prenotazioniIds) {
        this.prenotazioniIds = prenotazioniIds;
    }
}
