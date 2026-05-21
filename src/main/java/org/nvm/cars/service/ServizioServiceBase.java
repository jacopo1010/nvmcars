
package org.nvm.cars.service;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.nvm.cars.model.Servizio;
import org.nvm.cars.model.ListinoServizi;
import org.nvm.cars.repository.ServizioRepository;
import org.nvm.cars.repository.ListinoServiziRepository;

@ApplicationScoped
public class ServizioServiceBase {

    @Inject
    protected ServizioRepository repository;
    @Inject
    protected ListinoServiziRepository listinoServiziRepository;

    // -------------------------------------------------------------------------
    // READ
    // -------------------------------------------------------------------------

    public List<Servizio> findAll() {
        return this.repository.findAll();
    }

    public Optional<Servizio> findById(Long id) {
        return Optional.ofNullable(this.repository.findById(id));
    }

    public boolean existsById(Long id) {
        return this.repository.existingById(id);
    }

    public long count() {
        return this.repository.count();
    }

    public List<Servizio> findByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }

    public List<Servizio> findByServiziId(Long id) {
        return this.repository.findByServiziId(id);
    }

    public List<Servizio> findByDisponibilitaId(Long id) {
        return this.repository.findByDisponibilitaId(id);
    }

    public List<Servizio> findByRighePrenotazioneId(Long id) {
        return this.repository.findByRighePrenotazioneId(id);
    }


    // -------------------------------------------------------------------------
    // WRITE
    // -------------------------------------------------------------------------

    @Transactional
    public Servizio save(Servizio entity) {
        this.prepareForCreate(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public boolean update(Servizio entity) {
        this.prepareForUpdate(entity);
        return this.repository.update(entity);
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------

    @Transactional
    public boolean delete(Long id) {
        Servizio entity = this.repository.findById(id);
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

    private void prepareForCreate(Servizio entity) {
        this.validateEntityForWrite(entity);
    }

    private void prepareForUpdate(Servizio entity) {
        this.validateEntityForWrite(entity);
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id obbligatorio per l'aggiornamento di Servizio");
        }
    }

    private void validateEntityForWrite(Servizio entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Servizio obbligatorio");
        }

        ListinoServizi servizi = entity.getServizi();
        if (servizi == null || servizi.getId() == null) {
            throw new IllegalArgumentException("ListinoServizi associato obbligatorio per Servizio");
        }
        if (servizi != null) {
            if (servizi.getId() == null) {
                throw new IllegalArgumentException("Id di ListinoServizi associato obbligatorio");
            }
            if (!this.listinoServiziRepository.existingById(servizi.getId())) {
                throw new IllegalArgumentException("ListinoServizi associato non esistente: "
                    + servizi.getId());
            }
        }
    }
}
