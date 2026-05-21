
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Indirizzo;

@ApplicationScoped
public class IndirizzoRepository extends SimpleRepositoryImpl<Indirizzo> {

    public IndirizzoRepository() {
        super(Indirizzo.class);
    }

    public List<Indirizzo> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Indirizzo> query = this.getEntityManager().createQuery(
                "SELECT i FROM Indirizzo i WHERE "
                + "LOWER(i.via) LIKE :keyword"
                + " OR "
                + "LOWER(i.civico) LIKE :keyword"
                + " OR "
                + "LOWER(i.citta) LIKE :keyword"
                + " OR "
                + "LOWER(i.cap) LIKE :keyword"
                + " OR "
                + "LOWER(i.provincia) LIKE :keyword"                ,
                Indirizzo.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

}
