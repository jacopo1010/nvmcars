
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Titolare;
import org.nvm.cars.repository.TitolareRepository;

@ApplicationScoped
public class TitolareServiceBase {

    @Inject
    protected TitolareRepository repository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Titolare> findAll() {
        return this.repository.findAll();
    }

    public Optional<Titolare> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Titolare> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<Titolare> findByAttivitaId(Long id) {
        return this.repository.findByAttivitaId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Titolare save(Titolare entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Titolare entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Titolare entity = this.repository.findById(id);
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

    private void prepareForCreate(Titolare entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Titolare entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Titolare");
        }
    }

    private void validateEntityForWrite(Titolare entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Titolare obbligatorio");
        }
    }
}
