
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Disponibilita;

@ApplicationScoped
public class DisponibilitaRepository extends SimpleRepositoryImpl<Disponibilita> {

    public DisponibilitaRepository() {
        super(Disponibilita.class);
    }

    public List<Disponibilita> findByAttivitaId(Long id) {
        TypedQuery<Disponibilita> query = this.getEntityManager().createQuery(
                "SELECT d FROM Disponibilita d WHERE d.attivita.id = :id",
                Disponibilita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Disponibilita> findByServizioId(Long id) {
        TypedQuery<Disponibilita> query = this.getEntityManager().createQuery(
                "SELECT d FROM Disponibilita d WHERE d.servizio.id = :id",
                Disponibilita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Disponibilita> findByPrenotazioniId(Long id) {
        TypedQuery<Disponibilita> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT d FROM Disponibilita d JOIN d.prenotazioni relation WHERE relation.id = :id",
                Disponibilita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
