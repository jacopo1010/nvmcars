
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Cliente;
import org.nvm.cars.repository.ClienteRepository;

@ApplicationScoped
public class ClienteServiceBase {

    @Inject
    protected ClienteRepository repository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Cliente> findAll() {
        return this.repository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Cliente> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<Cliente> findByAutomobiliId(Long id) {
        return this.repository.findByAutomobiliId(id);
    }

    public List<Cliente> findByPrenotazioniId(Long id) {
        return this.repository.findByPrenotazioniId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Cliente save(Cliente entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Cliente entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Cliente entity = this.repository.findById(id);
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

    private void prepareForCreate(Cliente entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Cliente entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Cliente");
        }
    }

    private void validateEntityForWrite(Cliente entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cliente obbligatorio");
        }
    }
}
