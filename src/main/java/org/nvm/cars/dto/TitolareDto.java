package org.nvm.cars.dto;


import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Titolare.
 */
public class TitolareDto {

    private Long id;

    private String nome;

    private String cognome;

    private Timestamp dataDiNascita;

    private String numero;

    private String regioneSociale;

    private String partitaIva;

    private String codiceFiscale;

    private List<Long> attivitaIds = new ArrayList<>();

    private Long possiedeId;

    public TitolareDto() {
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

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Timestamp getDataDiNascita() {
        return this.dataDiNascita;
    }

    public void setDataDiNascita(Timestamp dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRegioneSociale() {
        return this.regioneSociale;
    }

    public void setRegioneSociale(String regioneSociale) {
        this.regioneSociale = regioneSociale;
    }

    public String getPartitaIva() {
        return this.partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public List<Long> getAttivitaIds() {
        return this.attivitaIds;
    }

    public void setAttivitaIds(List<Long> attivitaIds) {
        this.attivitaIds = attivitaIds;
    }

    public Long getPossiedeId() {
        return this.possiedeId;
    }

    public void setPossiedeId(Long possiedeId) {
        this.possiedeId = possiedeId;
    }
}
