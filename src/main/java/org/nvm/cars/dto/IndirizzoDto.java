package org.nvm.cars.dto;


import java.math.BigDecimal;

/**
 * DTO generato automaticamente per Indirizzo.
 */
public class IndirizzoDto {

    private Long id;

    private String via;

    private String civico;

    private String citta;

    private String cap;

    private String provincia;

    private BigDecimal latitudine;

    private BigDecimal longitudine;

    private Long indirizzoId;

    public IndirizzoDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVia() {
        return this.via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return this.civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCitta() {
        return this.citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return this.cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public BigDecimal getLatitudine() {
        return this.latitudine;
    }

    public void setLatitudine(BigDecimal latitudine) {
        this.latitudine = latitudine;
    }

    public BigDecimal getLongitudine() {
        return this.longitudine;
    }

    public void setLongitudine(BigDecimal longitudine) {
        this.longitudine = longitudine;
    }

    public Long getIndirizzoId() {
        return this.indirizzoId;
    }

    public void setIndirizzoId(Long indirizzoId) {
        this.indirizzoId = indirizzoId;
    }
}
