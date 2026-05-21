package org.nvm.cars.dto;


import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Prenotazione.
 */
public class PrenotazioneDto {

    private Long id;

    private Timestamp dataCreazione;

    private String stato;

    private Long prenotazioniId;

    private List<Long> rigaPrenotazioneIds = new ArrayList<>();

    public PrenotazioneDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataCreazione() {
        return this.dataCreazione;
    }

    public void setDataCreazione(Timestamp dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getStato() {
        return this.stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Long getPrenotazioniId() {
        return this.prenotazioniId;
    }

    public void setPrenotazioniId(Long prenotazioniId) {
        this.prenotazioniId = prenotazioniId;
    }

    public List<Long> getRigaPrenotazioneIds() {
        return this.rigaPrenotazioneIds;
    }

    public void setRigaPrenotazioneIds(List<Long> rigaPrenotazioneIds) {
        this.rigaPrenotazioneIds = rigaPrenotazioneIds;
    }
}
