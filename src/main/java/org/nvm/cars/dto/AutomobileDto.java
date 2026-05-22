package org.nvm.cars.dto;


import java.sql.Timestamp;

/**
 * DTO generato automaticamente per Automobile.
 */
public class AutomobileDto {

    private Long id;

    private String targa;

    private String marca;

    private String Modello;

    private Timestamp anno;

    private Long automobiliId;

    private Long prenotazioneAffiliataId;

    public AutomobileDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarga() {
        return this.targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return this.Modello;
    }

    public void setModello(String Modello) {
        this.Modello = Modello;
    }

    public Timestamp getAnno() {
        return this.anno;
    }

    public void setAnno(Timestamp anno) {
        this.anno = anno;
    }

    public Long getAutomobiliId() {
        return this.automobiliId;
    }

    public void setAutomobiliId(Long automobiliId) {
        this.automobiliId = automobiliId;
    }

    public Long getPrenotazioneAffiliataId() {
        return this.prenotazioneAffiliataId;
    }

    public void setPrenotazioneAffiliataId(Long prenotazioneAffiliataId) {
        this.prenotazioneAffiliataId = prenotazioneAffiliataId;
    }
}
