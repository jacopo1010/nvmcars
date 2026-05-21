
package org.nvm.cars.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.nvm.cars.model.Disponibilita;
import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.dto.DisponibilitaDto;
import org.nvm.cars.service.DisponibilitaService;

public abstract class DisponibilitaControllerBase {

    @Inject
    protected DisponibilitaService disponibilitaService;

    @GET
    public Response getAllDisponibilitas() {
        List<Disponibilita> disponibilitas = this.disponibilitaService.findAll();
        return Response.ok(this.toDtoList(disponibilitas)).build();
    }

    @GET
    @Path("/count")
    public Response countDisponibilitas() {
        return Response.ok(this.disponibilitaService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsDisponibilita(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.disponibilitaService.existsById(id)).build();
    }

    @GET
    @Path("/{id}")
    public Response getDisponibilita(@PathParam("id") Long id) {
        Optional<Disponibilita> disponibilita = this.disponibilitaService.findById(id);
        if (!disponibilita.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(disponibilita.get())).build();
    }

    @POST
    public Response createDisponibilita(DisponibilitaDto disponibilita) {
        try {
            Disponibilita created = this.disponibilitaService.save(this.toEntity(disponibilita));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/disponibilita/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateDisponibilita(@PathParam("id") Long id, DisponibilitaDto disponibilita) {
        if (disponibilita == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            disponibilita.setId(id);
            Disponibilita entity = this.toEntity(disponibilita);
            boolean updated = this.disponibilitaService.update(entity);
            if (!updated) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.ok(this.toDto(entity)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @DELETE
    public Response deleteAll() {
        this.disponibilitaService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.disponibilitaService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-attivita/{attivitaId}")
    public Response findByAttivitaId(@PathParam("attivitaId") Long attivitaId) {
        if (attivitaId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.disponibilitaService.findByAttivitaId(attivitaId))).build();
    }

    @GET
    @Path("/by-servizio/{servizioId}")
    public Response findByServizioId(@PathParam("servizioId") Long servizioId) {
        if (servizioId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.disponibilitaService.findByServizioId(servizioId))).build();
    }

    @GET
    @Path("/by-prenotazioni/{prenotazioniId}")
    public Response findByPrenotazioniId(@PathParam("prenotazioniId") Long prenotazioniId) {
        if (prenotazioniId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.disponibilitaService.findByPrenotazioniId(prenotazioniId))).build();
    }

    protected DisponibilitaDto toDto(Disponibilita entity) {
        if (entity == null) {
            return null;
        }
        DisponibilitaDto dto = new DisponibilitaDto();
        dto.setId(entity.getId());
        dto.setDataOraInizio(entity.getDataOraInizio());
        dto.setDataOraFine(entity.getDataOraFine());
        dto.setPostiDisponibili(entity.getPostiDisponibili());
        if (entity.getAttivita() != null) {
            dto.setAttivitaId(entity.getAttivita().getId());
        }
        if (entity.getServizio() != null) {
            dto.setServizioId(entity.getServizio().getId());
        }
        if (entity.getPrenotazioni() != null) {
            List<Long> prenotazioniIds = new java.util.ArrayList<>();
            for (RigaPrenotazione relationEntity : entity.getPrenotazioni()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    prenotazioniIds.add(relationEntity.getId());
                }
            }
            dto.setPrenotazioniIds(prenotazioniIds);
        }
        return dto;
    }

    protected List<DisponibilitaDto> toDtoList(List<Disponibilita> entities) {
        List<DisponibilitaDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Disponibilita entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Disponibilita toEntity(DisponibilitaDto dto) {
        if (dto == null) {
            return null;
        }
        Disponibilita entity = new Disponibilita();
        entity.setId(dto.getId());
        entity.setDataOraInizio(dto.getDataOraInizio());
        entity.setDataOraFine(dto.getDataOraFine());
        entity.setPostiDisponibili(dto.getPostiDisponibili());
        if (dto.getAttivitaId() != null) {
            Attivita relationEntity = new Attivita();
            relationEntity.setId(dto.getAttivitaId());
            entity.setAttivita(relationEntity);
        }
        if (dto.getServizioId() != null) {
            Servizio relationEntity = new Servizio();
            relationEntity.setId(dto.getServizioId());
            entity.setServizio(relationEntity);
        }
        if (dto.getPrenotazioniIds() != null) {
            List<RigaPrenotazione> prenotazioniEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getPrenotazioniIds()) {
                if (relationId != null) {
                    RigaPrenotazione relationEntity = new RigaPrenotazione();
                    relationEntity.setId(relationId);
                    prenotazioniEntities.add(relationEntity);
                }
            }
            entity.setPrenotazioni(prenotazioniEntities);
        }
        return entity;
    }
}
