
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Titolare;
import org.nvm.cars.repository.AttivitaRepository;
import org.nvm.cars.repository.TitolareRepository;

@ApplicationScoped
public class AttivitaServiceBase {

    @Inject
    protected AttivitaRepository repository;
    @Inject
    protected TitolareRepository titolareRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Attivita> findAll() {
        return this.repository.findAll();
    }

    public Optional<Attivita> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Attivita> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<Attivita> findByAttivitaId(Long id) {
        return this.repository.findByAttivitaId(id);
    }

    public List<Attivita> findByDisponibilitaId(Long id) {
        return this.repository.findByDisponibilitaId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Attivita save(Attivita entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Attivita entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Attivita entity = this.repository.findById(id);
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

    private void prepareForCreate(Attivita entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Attivita entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Attivita");
        }
    }

    private void validateEntityForWrite(Attivita entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Attivita obbligatorio");
        }

        Titolare attivita = entity.getAttivita();
        if (attivita == null || attivita.getId() == null) {
            throw new IllegalArgumentException("Titolare associato obbligatorio per Attivita");
        }
        if (attivita != null) {
            if (attivita.getId() == null) {
                throw new IllegalArgumentException("Id di Titolare associato obbligatorio");
            }
            if (!this.titolareRepository.existingById(attivita.getId())) {
                throw new IllegalArgumentException("Titolare associato non esistente: "
                    + attivita.getId());
            }
        }
    }
}
