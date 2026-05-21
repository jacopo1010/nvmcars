
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.model.Prenotazione;
import org.nvm.cars.model.Disponibilita;
import org.nvm.cars.repository.RigaPrenotazioneRepository;
import org.nvm.cars.repository.ServizioRepository;
import org.nvm.cars.repository.PrenotazioneRepository;
import org.nvm.cars.repository.DisponibilitaRepository;

@ApplicationScoped
public class RigaPrenotazioneServiceBase {

    @Inject
    protected RigaPrenotazioneRepository repository;
    @Inject
    protected ServizioRepository servizioRepository;
    @Inject
    protected PrenotazioneRepository prenotazioneRepository;
    @Inject
    protected DisponibilitaRepository disponibilitaRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<RigaPrenotazione> findAll() {
        return this.repository.findAll();
    }

    public Optional<RigaPrenotazione> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<RigaPrenotazione> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<RigaPrenotazione> findByRighePrenotazioneId(Long id) {
        return this.repository.findByRighePrenotazioneId(id);
    }

    public List<RigaPrenotazione> findByPrenotazioneId(Long id) {
        return this.repository.findByPrenotazioneId(id);
    }

    public List<RigaPrenotazione> findByPrenotazioniId(Long id) {
        return this.repository.findByPrenotazioniId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public RigaPrenotazione save(RigaPrenotazione entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(RigaPrenotazione entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        RigaPrenotazione entity = this.repository.findById(id);
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

    private void prepareForCreate(RigaPrenotazione entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(RigaPrenotazione entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di RigaPrenotazione");
        }
    }

    private void validateEntityForWrite(RigaPrenotazione entity) {
        if (entity == null) {
            throw new IllegalArgumentException("RigaPrenotazione obbligatorio");
        }

        Servizio righePrenotazione = entity.getRighePrenotazione();
        if (righePrenotazione == null || righePrenotazione.getId() == null) {
            throw new IllegalArgumentException("Servizio associato obbligatorio per RigaPrenotazione");
        }
        if (righePrenotazione != null) {
            if (righePrenotazione.getId() == null) {
                throw new IllegalArgumentException("Id di Servizio associato obbligatorio");
            }
            if (!this.servizioRepository.existingById(righePrenotazione.getId())) {
                throw new IllegalArgumentException("Servizio associato non esistente: "
                    + righePrenotazione.getId());
            }
        }

        Prenotazione prenotazione = entity.getPrenotazione();
        if (prenotazione == null || prenotazione.getId() == null) {
            throw new IllegalArgumentException("Prenotazione associato obbligatorio per RigaPrenotazione");
        }
        if (prenotazione != null) {
            if (prenotazione.getId() == null) {
                throw new IllegalArgumentException("Id di Prenotazione associato obbligatorio");
            }
            if (!this.prenotazioneRepository.existingById(prenotazione.getId())) {
                throw new IllegalArgumentException("Prenotazione associato non esistente: "
                    + prenotazione.getId());
            }
        }

        Disponibilita prenotazioni = entity.getPrenotazioni();
        if (prenotazioni == null || prenotazioni.getId() == null) {
            throw new IllegalArgumentException("Disponibilita associato obbligatorio per RigaPrenotazione");
        }
        if (prenotazioni != null) {
            if (prenotazioni.getId() == null) {
                throw new IllegalArgumentException("Id di Disponibilita associato obbligatorio");
            }
            if (!this.disponibilitaRepository.existingById(prenotazioni.getId())) {
                throw new IllegalArgumentException("Disponibilita associato non esistente: "
                    + prenotazioni.getId());
            }
        }
    }
}
