
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
import org.nvm.cars.model.Servizio;
import org.nvm.cars.model.ListinoServizi;
import org.nvm.cars.model.Disponibilita;
import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.dto.ServizioDto;
import org.nvm.cars.service.ServizioService;

public abstract class ServizioControllerBase {

    @Inject
    protected ServizioService servizioService;

    @GET
    public Response getAllServizios() {
        List<Servizio> servizios = this.servizioService.findAll();
        return Response.ok(this.toDtoList(servizios)).build();
    }

    @GET
    @Path("/count")
    public Response countServizios() {
        return Response.ok(this.servizioService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsServizio(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.servizioService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.servizioService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getServizio(@PathParam("id") Long id) {
        Optional<Servizio> servizio = this.servizioService.findById(id);
        if (!servizio.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(servizio.get())).build();
    }

    @POST
    public Response createServizio(ServizioDto servizio) {
        try {
            Servizio created = this.servizioService.save(this.toEntity(servizio));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/service/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateServizio(@PathParam("id") Long id, ServizioDto servizio) {
        if (servizio == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            servizio.setId(id);
            Servizio entity = this.toEntity(servizio);
            boolean updated = this.servizioService.update(entity);
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
        this.servizioService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.servizioService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-servizi/{serviziId}")
    public Response findByServiziId(@PathParam("serviziId") Long serviziId) {
        if (serviziId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.servizioService.findByServiziId(serviziId))).build();
    }

    @GET
    @Path("/by-disponibilita/{disponibilitaId}")
    public Response findByDisponibilitaId(@PathParam("disponibilitaId") Long disponibilitaId) {
        if (disponibilitaId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.servizioService.findByDisponibilitaId(disponibilitaId))).build();
    }

    @GET
    @Path("/by-righe_prenotazione/{righePrenotazioneId}")
    public Response findByRighePrenotazioneId(@PathParam("righePrenotazioneId") Long righePrenotazioneId) {
        if (righePrenotazioneId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.servizioService.findByRighePrenotazioneId(righePrenotazioneId))).build();
    }

    protected ServizioDto toDto(Servizio entity) {
        if (entity == null) {
            return null;
        }
        ServizioDto dto = new ServizioDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescrizione(entity.getDescrizione());
        dto.setPrezzo(entity.getPrezzo());
        dto.setDurata(entity.getDurata());
        if (entity.getServizi() != null) {
            dto.setServiziId(entity.getServizi().getId());
        }
        if (entity.getDisponibilita() != null) {
            List<Long> disponibilitaIds = new java.util.ArrayList<>();
            for (Disponibilita relationEntity : entity.getDisponibilita()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    disponibilitaIds.add(relationEntity.getId());
                }
            }
            dto.setDisponibilitaIds(disponibilitaIds);
        }
        if (entity.getRighePrenotazione() != null) {
            List<Long> righePrenotazioneIds = new java.util.ArrayList<>();
            for (RigaPrenotazione relationEntity : entity.getRighePrenotazione()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    righePrenotazioneIds.add(relationEntity.getId());
                }
            }
            dto.setRighePrenotazioneIds(righePrenotazioneIds);
        }
        return dto;
    }

    protected List<ServizioDto> toDtoList(List<Servizio> entities) {
        List<ServizioDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Servizio entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Servizio toEntity(ServizioDto dto) {
        if (dto == null) {
            return null;
        }
        Servizio entity = new Servizio();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDescrizione(dto.getDescrizione());
        entity.setPrezzo(dto.getPrezzo());
        entity.setDurata(dto.getDurata());
        if (dto.getServiziId() != null) {
            ListinoServizi relationEntity = new ListinoServizi();
            relationEntity.setId(dto.getServiziId());
            entity.setServizi(relationEntity);
        }
        if (dto.getDisponibilitaIds() != null) {
            List<Disponibilita> disponibilitaEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getDisponibilitaIds()) {
                if (relationId != null) {
                    Disponibilita relationEntity = new Disponibilita();
                    relationEntity.setId(relationId);
                    disponibilitaEntities.add(relationEntity);
                }
            }
            entity.setDisponibilita(disponibilitaEntities);
        }
        if (dto.getRighePrenotazioneIds() != null) {
            List<RigaPrenotazione> righePrenotazioneEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getRighePrenotazioneIds()) {
                if (relationId != null) {
                    RigaPrenotazione relationEntity = new RigaPrenotazione();
                    relationEntity.setId(relationId);
                    righePrenotazioneEntities.add(relationEntity);
                }
            }
            entity.setRighePrenotazione(righePrenotazioneEntities);
        }
        return entity;
    }
}
