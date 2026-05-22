
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Automobile;
import org.nvm.cars.model.Cliente;
import org.nvm.cars.repository.AutomobileRepository;
import org.nvm.cars.repository.ClienteRepository;

@ApplicationScoped
public class AutomobileServiceBase {

    @Inject
    protected AutomobileRepository repository;
    @Inject
    protected ClienteRepository clienteRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Automobile> findAll() {
        return this.repository.findAll();
    }

    public Optional<Automobile> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Automobile> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<Automobile> findByAutomobiliId(Long id) {
        return this.repository.findByAutomobiliId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Automobile save(Automobile entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Automobile entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Automobile entity = this.repository.findById(id);
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

    private void prepareForCreate(Automobile entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Automobile entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Automobile");
        }
    }

    private void validateEntityForWrite(Automobile entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Automobile obbligatorio");
        }

        Cliente automobili = entity.getAutomobili();
        if (automobili == null || automobili.getId() == null) {
            throw new IllegalArgumentException("Cliente associato obbligatorio per Automobile");
        }
        if (automobili != null) {
            if (automobili.getId() == null) {
                throw new IllegalArgumentException("Id di Cliente associato obbligatorio");
            }
            if (!this.clienteRepository.existingById(automobili.getId())) {
                throw new IllegalArgumentException("Cliente associato non esistente: "
                    + automobili.getId());
            }
        }
    }
}
