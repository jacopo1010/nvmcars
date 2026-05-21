
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.RigaPrenotazione;

@ApplicationScoped
public class RigaPrenotazioneRepository extends SimpleRepositoryImpl<RigaPrenotazione> {

    public RigaPrenotazioneRepository() {
        super(RigaPrenotazione.class);
    }

    public List<RigaPrenotazione> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<RigaPrenotazione> query = this.getEntityManager().createQuery(
                "SELECT r FROM RigaPrenotazione r WHERE "
                + "LOWER(r.note) LIKE :keyword"                ,
                RigaPrenotazione.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<RigaPrenotazione> findByRighePrenotazioneId(Long id) {
        TypedQuery<RigaPrenotazione> query = this.getEntityManager().createQuery(
                "SELECT r FROM RigaPrenotazione r WHERE r.righePrenotazione.id = :id",
                RigaPrenotazione.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<RigaPrenotazione> findByRigaPrenotazioneId(Long id) {
        TypedQuery<RigaPrenotazione> query = this.getEntityManager().createQuery(
                "SELECT r FROM RigaPrenotazione r WHERE r.rigaPrenotazione.id = :id",
                RigaPrenotazione.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<RigaPrenotazione> findByPrenotazioniId(Long id) {
        TypedQuery<RigaPrenotazione> query = this.getEntityManager().createQuery(
                "SELECT r FROM RigaPrenotazione r WHERE r.prenotazioni.id = :id",
                RigaPrenotazione.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
