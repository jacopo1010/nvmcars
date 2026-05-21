
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Credenziali;
import org.nvm.cars.repository.CredenzialiRepository;

@ApplicationScoped
public class CredenzialiServiceBase {

    @Inject
    protected CredenzialiRepository repository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Credenziali> findAll() {
        return this.repository.findAll();
    }

    public Optional<Credenziali> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Credenziali> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Credenziali save(Credenziali entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Credenziali entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Credenziali entity = this.repository.findById(id);
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

    private void prepareForCreate(Credenziali entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Credenziali entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Credenziali");
        }
    }

    private void validateEntityForWrite(Credenziali entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Credenziali obbligatorio");
        }
    }
}
