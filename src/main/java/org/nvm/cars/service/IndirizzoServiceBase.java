
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Indirizzo;
import org.nvm.cars.repository.IndirizzoRepository;

@ApplicationScoped
public class IndirizzoServiceBase {

    @Inject
    protected IndirizzoRepository repository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Indirizzo> findAll() {
        return this.repository.findAll();
    }

    public Optional<Indirizzo> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Indirizzo> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Indirizzo save(Indirizzo entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Indirizzo entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Indirizzo entity = this.repository.findById(id);
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

    private void prepareForCreate(Indirizzo entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Indirizzo entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Indirizzo");
        }
    }

    private void validateEntityForWrite(Indirizzo entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Indirizzo obbligatorio");
        }
    }
}
