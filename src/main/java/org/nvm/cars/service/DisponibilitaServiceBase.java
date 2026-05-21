
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Disponibilita;
import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.repository.DisponibilitaRepository;
import org.nvm.cars.repository.AttivitaRepository;
import org.nvm.cars.repository.ServizioRepository;

@ApplicationScoped
public class DisponibilitaServiceBase {

    @Inject
    protected DisponibilitaRepository repository;
    @Inject
    protected AttivitaRepository attivitaRepository;
    @Inject
    protected ServizioRepository servizioRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Disponibilita> findAll() {
        return this.repository.findAll();
    }

    public Optional<Disponibilita> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Disponibilita> findByAttivitaId(Long id) {
        return this.repository.findByAttivitaId(id);
    }

    public List<Disponibilita> findByServizioId(Long id) {
        return this.repository.findByServizioId(id);
    }

    public List<Disponibilita> findByPrenotazioniId(Long id) {
        return this.repository.findByPrenotazioniId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Disponibilita save(Disponibilita entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Disponibilita entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Disponibilita entity = this.repository.findById(id);
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

    private void prepareForCreate(Disponibilita entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Disponibilita entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Disponibilita");
        }
    }

    private void validateEntityForWrite(Disponibilita entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Disponibilita obbligatorio");
        }

        Attivita attivita = entity.getAttivita();
        if (attivita == null || attivita.getId() == null) {
            throw new IllegalArgumentException("Attivita associato obbligatorio per Disponibilita");
        }
        if (attivita != null) {
            if (attivita.getId() == null) {
                throw new IllegalArgumentException("Id di Attivita associato obbligatorio");
            }
            if (!this.attivitaRepository.existingById(attivita.getId())) {
                throw new IllegalArgumentException("Attivita associato non esistente: "
                    + attivita.getId());
            }
        }

        Servizio servizio = entity.getServizio();
        if (servizio == null || servizio.getId() == null) {
            throw new IllegalArgumentException("Servizio associato obbligatorio per Disponibilita");
        }
        if (servizio != null) {
            if (servizio.getId() == null) {
                throw new IllegalArgumentException("Id di Servizio associato obbligatorio");
            }
            if (!this.servizioRepository.existingById(servizio.getId())) {
                throw new IllegalArgumentException("Servizio associato non esistente: "
                    + servizio.getId());
            }
        }
    }
}
