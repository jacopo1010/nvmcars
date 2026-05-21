package org.nvm.cars.dto;


import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Cliente.
 */
public class ClienteDto {

    private Long id;

    private String nome;

    private String cognome;

    private Timestamp dataDiNascita;

    private String numero;

    private Long possiedeId;

    private List<Long> prenotazioniIds = new ArrayList<>();

    public ClienteDto() {
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

    public Long getPossiedeId() {
        return this.possiedeId;
    }

    public void setPossiedeId(Long possiedeId) {
        this.possiedeId = possiedeId;
    }

    public List<Long> getPrenotazioniIds() {
        return this.prenotazioniIds;
    }

    public void setPrenotazioniIds(List<Long> prenotazioniIds) {
        this.prenotazioniIds = prenotazioniIds;
    }
}
