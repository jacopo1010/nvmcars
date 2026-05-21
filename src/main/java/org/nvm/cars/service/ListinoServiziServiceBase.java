
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.ListinoServizi;
import org.nvm.cars.repository.ListinoServiziRepository;

@ApplicationScoped
public class ListinoServiziServiceBase {

    @Inject
    protected ListinoServiziRepository repository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<ListinoServizi> findAll() {
        return this.repository.findAll();
    }

    public Optional<ListinoServizi> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<ListinoServizi> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<ListinoServizi> findByServiziId(Long id) {
        return this.repository.findByServiziId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public ListinoServizi save(ListinoServizi entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(ListinoServizi entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        ListinoServizi entity = this.repository.findById(id);
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

    private void prepareForCreate(ListinoServizi entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(ListinoServizi entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di ListinoServizi");
        }
    }

    private void validateEntityForWrite(ListinoServizi entity) {
        if (entity == null) {
            throw new IllegalArgumentException("ListinoServizi obbligatorio");
        }
    }
}
