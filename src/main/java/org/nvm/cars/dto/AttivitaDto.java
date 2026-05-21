package org.nvm.cars.dto;


import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per Attivita.
 */
public class AttivitaDto {

    private Long id;

    private String nome;

    private String descrizione;

    private String numero;

    private String email;

    private Long titolareId;

    private Long indirizzoId;

    private Long listinoId;

    private List<Long> disponibilitaIds = new ArrayList<>();

    public AttivitaDto() {
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

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTitolareId() {
        return this.titolareId;
    }

    public void setTitolareId(Long titolareId) {
        this.titolareId = titolareId;
    }

    public Long getIndirizzoId() {
        return this.indirizzoId;
    }

    public void setIndirizzoId(Long indirizzoId) {
        this.indirizzoId = indirizzoId;
    }

    public Long getListinoId() {
        return this.listinoId;
    }

    public void setListinoId(Long listinoId) {
        this.listinoId = listinoId;
    }

    public List<Long> getDisponibilitaIds() {
        return this.disponibilitaIds;
    }

    public void setDisponibilitaIds(List<Long> disponibilitaIds) {
        this.disponibilitaIds = disponibilitaIds;
    }
}
