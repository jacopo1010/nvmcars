
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.ListinoServizi;

@ApplicationScoped
public class ListinoServiziRepository extends SimpleRepositoryImpl<ListinoServizi> {

    public ListinoServiziRepository() {
        super(ListinoServizi.class);
    }

    public List<ListinoServizi> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<ListinoServizi> query = this.getEntityManager().createQuery(
                "SELECT l FROM ListinoServizi l WHERE "
                + "LOWER(l.nome) LIKE :keyword"                ,
                ListinoServizi.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<ListinoServizi> findByServiziId(Long id) {
        TypedQuery<ListinoServizi> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT l FROM ListinoServizi l JOIN l.servizi relation WHERE relation.id = :id",
                ListinoServizi.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
