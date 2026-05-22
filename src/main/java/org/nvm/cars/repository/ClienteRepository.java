
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Cliente;

@ApplicationScoped
public class ClienteRepository extends SimpleRepositoryImpl<Cliente> {

    public ClienteRepository() {
        super(Cliente.class);
    }

    public List<Cliente> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        TypedQuery<Cliente> query = this.getEntityManager().createQuery(
                "SELECT c FROM Cliente c WHERE "
                + "LOWER(c.nome) LIKE :keyword"
                + " OR "
                + "LOWER(c.cognome) LIKE :keyword"
                + " OR "
                + "LOWER(c.numero) LIKE :keyword"                ,
                Cliente.class);
        query.setParameter("keyword", pattern);
        return query.getResultList();
    }

    public List<Cliente> findByAutomobiliId(Long id) {
        TypedQuery<Cliente> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT c FROM Cliente c JOIN c.automobili relation WHERE relation.id = :id",
                Cliente.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Cliente> findByPrenotazioniId(Long id) {
        TypedQuery<Cliente> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT c FROM Cliente c JOIN c.prenotazioni relation WHERE relation.id = :id",
                Cliente.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
