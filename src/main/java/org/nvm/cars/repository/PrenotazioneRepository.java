
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Prenotazione;

@ApplicationScoped
public class PrenotazioneRepository extends SimpleRepositoryImpl<Prenotazione> {

    public PrenotazioneRepository() {
        super(Prenotazione.class);
    }

    public List<Prenotazione> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Prenotazione> query = this.getEntityManager().createQuery(
                "SELECT p FROM Prenotazione p WHERE "
                + "LOWER(p.stato) LIKE :keyword"                ,
                Prenotazione.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<Prenotazione> findByPrenotazioniId(Long id) {
        TypedQuery<Prenotazione> query = this.getEntityManager().createQuery(
                "SELECT p FROM Prenotazione p WHERE p.prenotazioni.id = :id",
                Prenotazione.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Prenotazione> findByRigaPrenotazioneId(Long id) {
        TypedQuery<Prenotazione> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT p FROM Prenotazione p JOIN p.rigaPrenotazione relation WHERE relation.id = :id",
                Prenotazione.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
