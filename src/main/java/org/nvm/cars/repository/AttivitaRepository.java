
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Attivita;

@ApplicationScoped
public class AttivitaRepository extends SimpleRepositoryImpl<Attivita> {

    public AttivitaRepository() {
        super(Attivita.class);
    }

    public List<Attivita> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Attivita> query = this.getEntityManager().createQuery(
                "SELECT a FROM Attivita a WHERE "
                + "LOWER(a.nome) LIKE :keyword"
                + " OR "
                + "LOWER(a.descrizione) LIKE :keyword"
                + " OR "
                + "LOWER(a.numero) LIKE :keyword"
                + " OR "
                + "LOWER(a.email) LIKE :keyword"                ,
                Attivita.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<Attivita> findByAttivitaId(Long id) {
        TypedQuery<Attivita> query = this.getEntityManager().createQuery(
                "SELECT a FROM Attivita a WHERE a.attivita.id = :id",
                Attivita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Attivita> findByDisponibilitaId(Long id) {
        TypedQuery<Attivita> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT a FROM Attivita a JOIN a.disponibilita relation WHERE relation.id = :id",
                Attivita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
