package org.nvm.cars.dto;



/**
 * DTO generato automaticamente per RigaPrenotazione.
 */
public class RigaPrenotazioneDto {

    private Long id;

    private String note;

    private Long prenotazioneAffiliataId;

    private Long righePrenotazioneId;

    private Long rigaPrenotazioneId;

    private Long prenotazioniId;

    public RigaPrenotazioneDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getPrenotazioneAffiliataId() {
        return this.prenotazioneAffiliataId;
    }

    public void setPrenotazioneAffiliataId(Long prenotazioneAffiliataId) {
        this.prenotazioneAffiliataId = prenotazioneAffiliataId;
    }

    public Long getRighePrenotazioneId() {
        return this.righePrenotazioneId;
    }

    public void setRighePrenotazioneId(Long righePrenotazioneId) {
        this.righePrenotazioneId = righePrenotazioneId;
    }

    public Long getRigaPrenotazioneId() {
        return this.rigaPrenotazioneId;
    }

    public void setRigaPrenotazioneId(Long rigaPrenotazioneId) {
        this.rigaPrenotazioneId = rigaPrenotazioneId;
    }

    public Long getPrenotazioniId() {
        return this.prenotazioniId;
    }

    public void setPrenotazioniId(Long prenotazioniId) {
        this.prenotazioniId = prenotazioniId;
    }
}
