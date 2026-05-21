
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Titolare;

@ApplicationScoped
public class TitolareRepository extends SimpleRepositoryImpl<Titolare> {

    public TitolareRepository() {
        super(Titolare.class);
    }

    public List<Titolare> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Titolare> query = this.getEntityManager().createQuery(
                "SELECT t FROM Titolare t WHERE "
                + "LOWER(t.nome) LIKE :keyword"
                + " OR "
                + "LOWER(t.cognome) LIKE :keyword"
                + " OR "
                + "LOWER(t.numero) LIKE :keyword"
                + " OR "
                + "LOWER(t.regioneSociale) LIKE :keyword"
                + " OR "
                + "LOWER(t.partitaIva) LIKE :keyword"
                + " OR "
                + "LOWER(t.codiceFiscale) LIKE :keyword"                ,
                Titolare.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<Titolare> findByAttivitaId(Long id) {
        TypedQuery<Titolare> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT t FROM Titolare t JOIN t.attivita relation WHERE relation.id = :id",
                Titolare.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
