package org.nvm.cars.dto;


import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Disponiblita.
 */
public class DisponiblitaDto {

    private Long id;

    private Timestamp dataOraInizio;

    private Timestamp dataOraFine;

    private Integer postiDisponibili;

    private Long attivitaId;

    private Long disponibilitaId;

    private List<Long> prenotazioniIds = new ArrayList<>();

    public DisponiblitaDto() {
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

    public Long getDisponibilitaId() {
        return this.disponibilitaId;
    }

    public void setDisponibilitaId(Long disponibilitaId) {
        this.disponibilitaId = disponibilitaId;
    }

    public List<Long> getPrenotazioniIds() {
        return this.prenotazioniIds;
    }

    public void setPrenotazioniIds(List<Long> prenotazioniIds) {
        this.prenotazioniIds = prenotazioniIds;
    }
}
