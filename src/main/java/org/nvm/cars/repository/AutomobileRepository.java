
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Automobile;

@ApplicationScoped
public class AutomobileRepository extends SimpleRepositoryImpl<Automobile> {

    public AutomobileRepository() {
        super(Automobile.class);
    }

    public List<Automobile> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Automobile> query = this.getEntityManager().createQuery(
                "SELECT a FROM Automobile a WHERE "
                + "LOWER(a.targa) LIKE :keyword"
                + " OR "
                + "LOWER(a.marca) LIKE :keyword"
                + " OR "
                + "LOWER(a.Modello) LIKE :keyword"                ,
                Automobile.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<Automobile> findByAutomobiliId(Long id) {
        TypedQuery<Automobile> query = this.getEntityManager().createQuery(
                "SELECT a FROM Automobile a WHERE a.automobili.id = :id",
                Automobile.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
