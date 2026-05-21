
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Disponiblita;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.repository.DisponiblitaRepository;
import org.nvm.cars.repository.ServizioRepository;

@ApplicationScoped
public class DisponiblitaServiceBase {

    @Inject
    protected DisponiblitaRepository repository;
    @Inject
    protected ServizioRepository servizioRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Disponiblita> findAll() {
        return this.repository.findAll();
    }

    public Optional<Disponiblita> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Disponiblita> findByDisponibilitaId(Long id) {
        return this.repository.findByDisponibilitaId(id);
    }

    public List<Disponiblita> findByPrenotazioniId(Long id) {
        return this.repository.findByPrenotazioniId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Disponiblita save(Disponiblita entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Disponiblita entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Disponiblita entity = this.repository.findById(id);
        if (entity == null) {
            return false;
        }
        this.repository.delete(entity);
        return true;
    }

    @Transactional
    public void deleteAll() {
        this.repository.deleteAll();
    }

    // -------------------------------------------------------------------------
    // VALIDAZIONE
    // -------------------------------------------------------------------------

    private void prepareForCreate(Disponiblita entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Disponiblita entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Disponiblita");
        }
    }

    private void validateEntityForWrite(Disponiblita entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Disponiblita obbligatorio");
        }

        Servizio disponibilita = entity.getDisponibilita();
        if (disponibilita == null || disponibilita.getId() == null) {
            throw new IllegalArgumentException("Servizio associato obbligatorio per Disponiblita");
        }
        if (disponibilita != null) {
            if (disponibilita.getId() == null) {
                throw new IllegalArgumentException("Id di Servizio associato obbligatorio");
            }
            if (!this.servizioRepository.existingById(disponibilita.getId())) {
                throw new IllegalArgumentException("Servizio associato non esistente: "
                    + disponibilita.getId());
            }
        }
    }
}
