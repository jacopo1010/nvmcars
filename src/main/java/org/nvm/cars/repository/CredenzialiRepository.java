
package org.nvm.cars.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import org.nvm.cars.model.Credenziali;

import java.util.Collections;
import java.util.List;

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

    public Credenziali findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        TypedQuery<Credenziali> query = this.getEntityManager().createQuery(
                "SELECT c FROM Credenziali c WHERE username = :username",
                Credenziali.class);
        query.setParameter("username", username);
        List<Credenziali> result = query.setMaxResults(1).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
