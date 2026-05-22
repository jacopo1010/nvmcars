package org.nvm.cars.model;

import java.sql.Timestamp;
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
 * Elemento generato automaticamente: Automobile
 */
@Entity
@Table(name = "macchine_registrate")
public class Automobile {

    // --- ATTRIBUTI SEMPLICI ---
    /**
     * Elemento generato automaticamente: id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Elemento generato automaticamente: targa
     */
    @Column(name = "targa", nullable = false, unique = true)
    private String targa;
    /**
     * Elemento generato automaticamente: marca
     */
    @Column(name = "marca", nullable = false)
    private String marca;
    /**
     * Elemento generato automaticamente: Modello
     */
    @Column(name = "modello", nullable = false)
    private String Modello;
    /**
     * Elemento generato automaticamente: anno
     */
    @Column(name = "anno", nullable = false)
    private Timestamp anno;
    /**
     * Elemento generato automaticamente: automobili
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.REMOVE })
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "cliente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente automobili;
    /**
     * Elemento generato automaticamente: prenotazioneAffiliata
     */
    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "riga_prenotazione_id", nullable = true)
    private RigaPrenotazione prenotazioneAffiliata;

    // --- COSTRUTTORE ---
    public Automobile() {
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
     * Restituisce targa.
     *
     * @return elemento generato automaticamente: targa
     */
    public String getTarga() {
        return this.targa;
    }

    /**
     * Imposta targa.
     *
     * @param targa elemento generato automaticamente: targa
     */
    public void setTarga(String targa) {
        this.targa = targa;
    }
    /**
     * Restituisce marca.
     *
     * @return elemento generato automaticamente: marca
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     * Imposta marca.
     *
     * @param marca elemento generato automaticamente: marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Restituisce Modello.
     *
     * @return elemento generato automaticamente: Modello
     */
    public String getModello() {
        return this.Modello;
    }

    /**
     * Imposta Modello.
     *
     * @param Modello elemento generato automaticamente: Modello
     */
    public void setModello(String Modello) {
        this.Modello = Modello;
    }
    /**
     * Restituisce anno.
     *
     * @return elemento generato automaticamente: anno
     */
    public Timestamp getAnno() {
        return this.anno;
    }

    /**
     * Imposta anno.
     *
     * @param anno elemento generato automaticamente: anno
     */
    public void setAnno(Timestamp anno) {
        this.anno = anno;
    }
    /**
     * Restituisce automobili.
     *
     * @return elemento generato automaticamente: automobili
     */
    public Cliente getAutomobili() {
        return this.automobili;
    }

    /**
     * Imposta automobili.
     *
     * @param automobili elemento generato automaticamente: automobili
     */
    public void setAutomobili(Cliente automobili) {
        this.automobili = automobili;
    }
    /**
     * Restituisce prenotazioneAffiliata.
     *
     * @return elemento generato automaticamente: prenotazioneAffiliata
     */
    public RigaPrenotazione getPrenotazioneAffiliata() {
        return this.prenotazioneAffiliata;
    }

    /**
     * Imposta prenotazioneAffiliata.
     *
     * @param prenotazioneAffiliata elemento generato automaticamente: prenotazioneAffiliata
     */
    public void setPrenotazioneAffiliata(RigaPrenotazione prenotazioneAffiliata) {
        this.prenotazioneAffiliata = prenotazioneAffiliata;
    }
}
