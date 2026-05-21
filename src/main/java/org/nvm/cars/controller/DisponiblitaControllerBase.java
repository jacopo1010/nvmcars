
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
import org.nvm.cars.model.Disponiblita;
import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.dto.DisponiblitaDto;
import org.nvm.cars.service.DisponiblitaService;

public abstract class DisponiblitaControllerBase {

    @Inject
    protected DisponiblitaService disponiblitaService;

    @GET
    public Response getAllDisponiblitas() {
        List<Disponiblita> disponiblitas = this.disponiblitaService.findAll();
        return Response.ok(this.toDtoList(disponiblitas)).build();
    }

    @GET
    @Path("/count")
    public Response countDisponiblitas() {
        return Response.ok(this.disponiblitaService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsDisponiblita(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.disponiblitaService.existsById(id)).build();
    }

    @GET
    @Path("/{id}")
    public Response getDisponiblita(@PathParam("id") Long id) {
        Optional<Disponiblita> disponiblita = this.disponiblitaService.findById(id);
        if (!disponiblita.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(disponiblita.get())).build();
    }

    @POST
    public Response createDisponiblita(DisponiblitaDto disponiblita) {
        try {
            Disponiblita created = this.disponiblitaService.save(this.toEntity(disponiblita));
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
    public Response updateDisponiblita(@PathParam("id") Long id, DisponiblitaDto disponiblita) {
        if (disponiblita == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            disponiblita.setId(id);
            Disponiblita entity = this.toEntity(disponiblita);
            boolean updated = this.disponiblitaService.update(entity);
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
        this.disponiblitaService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.disponiblitaService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-disponibilita/{disponibilitaId}")
    public Response findByDisponibilitaId(@PathParam("disponibilitaId") Long disponibilitaId) {
        if (disponibilitaId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.disponiblitaService.findByDisponibilitaId(disponibilitaId))).build();
    }

    @GET
    @Path("/by-prenotazioni/{prenotazioniId}")
    public Response findByPrenotazioniId(@PathParam("prenotazioniId") Long prenotazioniId) {
        if (prenotazioniId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.disponiblitaService.findByPrenotazioniId(prenotazioniId))).build();
    }

    protected DisponiblitaDto toDto(Disponiblita entity) {
        if (entity == null) {
            return null;
        }
        DisponiblitaDto dto = new DisponiblitaDto();
        dto.setId(entity.getId());
        dto.setDataOraInizio(entity.getDataOraInizio());
        dto.setDataOraFine(entity.getDataOraFine());
        dto.setPostiDisponibili(entity.getPostiDisponibili());
        if (entity.getAttivita() != null) {
            dto.setAttivitaId(entity.getAttivita().getId());
        }
        if (entity.getDisponibilita() != null) {
            dto.setDisponibilitaId(entity.getDisponibilita().getId());
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

    protected List<DisponiblitaDto> toDtoList(List<Disponiblita> entities) {
        List<DisponiblitaDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Disponiblita entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Disponiblita toEntity(DisponiblitaDto dto) {
        if (dto == null) {
            return null;
        }
        Disponiblita entity = new Disponiblita();
        entity.setId(dto.getId());
        entity.setDataOraInizio(dto.getDataOraInizio());
        entity.setDataOraFine(dto.getDataOraFine());
        entity.setPostiDisponibili(dto.getPostiDisponibili());
        if (dto.getAttivitaId() != null) {
            Attivita relationEntity = new Attivita();
            relationEntity.setId(dto.getAttivitaId());
            entity.setAttivita(relationEntity);
        }
        if (dto.getDisponibilitaId() != null) {
            Servizio relationEntity = new Servizio();
            relationEntity.setId(dto.getDisponibilitaId());
            entity.setDisponibilita(relationEntity);
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
