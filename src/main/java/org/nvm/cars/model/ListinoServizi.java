package org.nvm.cars.model;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Elemento generato automaticamente: ListinoServizi
 */
@Entity
@Table(name = "listino_servizi")
public class ListinoServizi {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: nome
     */
    @Column(name = "nome", nullable = false)
    private String nome;
    /**
     * Elemento generato automaticamente: listino
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "attivita_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attivita listino;
    /**
     * Elemento generato automaticamente: servizi
     */
    @OneToMany(mappedBy = "servizi", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    private List<Servizio> servizi = new ArrayList<>();

    // --- COSTRUTTORE ---
    public ListinoServizi() {
    }

    // --- GETTER E SETTER ---
    /**
     * Restituisce id.
     *
     * @return elemento generato automaticamente: id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Imposta id.
     *
     * @param id elemento generato automaticamente: id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Restituisce nome.
     *
     * @return elemento generato automaticamente: nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Imposta nome.
     *
     * @param nome elemento generato automaticamente: nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce listino.
     *
     * @return elemento generato automaticamente: listino
     */
    public Attivita getListino() {
        return this.listino;
    }

    /**
     * Imposta listino.
     *
     * @param listino elemento generato automaticamente: listino
     */
    public void setListino(Attivita listino) {
        this.listino = listino;
    }
    /**
     * Restituisce servizi.
     *
     * @return elemento generato automaticamente: servizi
     */
    public List<Servizio> getServizi() {
        return this.servizi;
    }

    /**
     * Imposta servizi.
     *
     * @param servizi elemento generato automaticamente: servizi
     */
    public void setServizi(List<Servizio> servizi) {
        this.servizi = servizi;
    }
}
