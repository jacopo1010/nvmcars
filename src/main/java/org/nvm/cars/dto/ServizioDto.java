package org.nvm.cars.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Servizio.
 */
public class ServizioDto {

    private Long id;

    private String nome;

    private String descrizione;

    private BigDecimal prezzo;

    private Integer durata;

    private Long serviziId;

    private List<Long> disponibilitaIds = new ArrayList<>();

    private List<Long> righePrenotazioneIds = new ArrayList<>();

    public ServizioDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getDurata() {
        return this.durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Long getServiziId() {
        return this.serviziId;
    }

    public void setServiziId(Long serviziId) {
        this.serviziId = serviziId;
    }

    public List<Long> getDisponibilitaIds() {
        return this.disponibilitaIds;
    }

    public void setDisponibilitaIds(List<Long> disponibilitaIds) {
        this.disponibilitaIds = disponibilitaIds;
    }

    public List<Long> getRighePrenotazioneIds() {
        return this.righePrenotazioneIds;
    }

    public void setRighePrenotazioneIds(List<Long> righePrenotazioneIds) {
        this.righePrenotazioneIds = righePrenotazioneIds;
    }
}
