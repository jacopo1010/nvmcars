
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Prenotazione;
import org.nvm.cars.model.Cliente;
import org.nvm.cars.repository.PrenotazioneRepository;
import org.nvm.cars.repository.ClienteRepository;

@ApplicationScoped
public class PrenotazioneServiceBase {

    @Inject
    protected PrenotazioneRepository repository;
    @Inject
    protected ClienteRepository clienteRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Prenotazione> findAll() {
        return this.repository.findAll();
    }

    public Optional<Prenotazione> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Prenotazione> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<Prenotazione> findByPrenotazioniId(Long id) {
        return this.repository.findByPrenotazioniId(id);
    }

    public List<Prenotazione> findByRigaPrenotazioneId(Long id) {
        return this.repository.findByRigaPrenotazioneId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Prenotazione save(Prenotazione entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Prenotazione entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Prenotazione entity = this.repository.findById(id);
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

    private void prepareForCreate(Prenotazione entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Prenotazione entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Prenotazione");
        }
    }

    private void validateEntityForWrite(Prenotazione entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Prenotazione obbligatorio");
        }

        Cliente prenotazioni = entity.getPrenotazioni();
        if (prenotazioni == null || prenotazioni.getId() == null) {
            throw new IllegalArgumentException("Cliente associato obbligatorio per Prenotazione");
        }
        if (prenotazioni != null) {
            if (prenotazioni.getId() == null) {
                throw new IllegalArgumentException("Id di Cliente associato obbligatorio");
            }
            if (!this.clienteRepository.existingById(prenotazioni.getId())) {
                throw new IllegalArgumentException("Cliente associato non esistente: "
                    + prenotazioni.getId());
            }
        }
    }
}
