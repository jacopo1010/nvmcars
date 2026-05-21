
package org.nvm.cars.repository;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import org.nvm.cars.model.Disponiblita;

@ApplicationScoped
public class DisponiblitaRepository extends SimpleRepositoryImpl<Disponiblita> {

    public DisponiblitaRepository() {
        super(Disponiblita.class);
    }

    public List<Disponiblita> findByDisponibilitaId(Long id) {
        TypedQuery<Disponiblita> query = this.getEntityManager().createQuery(
                "SELECT d FROM Disponiblita d WHERE d.disponibilita.id = :id",
                Disponiblita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Disponiblita> findByPrenotazioniId(Long id) {
        TypedQuery<Disponiblita> query = this.getEntityManager().createQuery(
                "SELECT DISTINCT d FROM Disponiblita d JOIN d.prenotazioni relation WHERE relation.id = :id",
                Disponiblita.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
