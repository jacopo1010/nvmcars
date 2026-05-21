
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Credenziali;

@ApplicationScoped
public class CredenzialiRepository extends SimpleRepositoryImpl<Credenziali> {

    public CredenzialiRepository() {
        super(Credenziali.class);
    }

    public List<Credenziali> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Credenziali> query = this.getEntityManager().createQuery(
                "SELECT c FROM Credenziali c WHERE "
                + "LOWER(c.username) LIKE :keyword"
                + " OR "
                + "LOWER(c.password) LIKE :keyword"
                + " OR "
                + "LOWER(c.email) LIKE :keyword"                ,
                Credenziali.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

}
