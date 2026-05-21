
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Servizio;

@ApplicationScoped
public class ServizioRepository extends SimpleRepositoryImpl<Servizio> {

    public ServizioRepository() {
        super(Servizio.class);
    }

    public List<Servizio> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Servizio> query = this.getEntityManager().createQuery(
                "SELECT s FROM Servizio s WHERE "
                + "LOWER(s.nome) LIKE :keyword"
                + " OR "
                + "LOWER(s.descrizione) LIKE :keyword"                ,
                Servizio.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<Servizio> findByServiziId(Long id) {
        TypedQuery<Servizio> query = this.getEntityManager().createQuery(
                "SELECT s FROM Servizio s WHERE s.servizi.id = :id",
                Servizio.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Servizio> findByDisponibilitaId(Long id) {
        TypedQuery<Servizio> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT s FROM Servizio s JOIN s.disponibilita relation WHERE relation.id = :id",
                Servizio.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Servizio> findByRighePrenotazioneId(Long id) {
        TypedQuery<Servizio> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT s FROM Servizio s JOIN s.righePrenotazione relation WHERE relation.id = :id",
                Servizio.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
