package org.nvm.cars.dto;


import java.util.List;
import java.util.ArrayList;

/**
 * DTO generato automaticamente per ListinoServizi.
 */
public class ListinoServiziDto {

    private Long id;

    private String nome;

    private Long listinoId;

    private List<Long> serviziIds = new ArrayList<>();

    public ListinoServiziDto() {
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

    public Long getListinoId() {
        return this.listinoId;
    }

    public void setListinoId(Long listinoId) {
        this.listinoId = listinoId;
    }

    public List<Long> getServiziIds() {
        return this.serviziIds;
    }

    public void setServiziIds(List<Long> serviziIds) {
        this.serviziIds = serviziIds;
    }
}
