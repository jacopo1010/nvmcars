
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
import org.nvm.cars.model.Prenotazione;
import org.nvm.cars.model.Cliente;
import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.dto.PrenotazioneDto;
import org.nvm.cars.service.PrenotazioneService;

public abstract class PrenotazioneControllerBase {

    @Inject
    protected PrenotazioneService prenotazioneService;

    @GET
    public Response getAllPrenotaziones() {
        List<Prenotazione> prenotaziones = this.prenotazioneService.findAll();
        return Response.ok(this.toDtoList(prenotaziones)).build();
    }

    @GET
    @Path("/count")
    public Response countPrenotaziones() {
        return Response.ok(this.prenotazioneService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsPrenotazione(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.prenotazioneService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.prenotazioneService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getPrenotazione(@PathParam("id") Long id) {
        Optional<Prenotazione> prenotazione = this.prenotazioneService.findById(id);
        if (!prenotazione.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(prenotazione.get())).build();
    }

    @POST
    public Response createPrenotazione(PrenotazioneDto prenotazione) {
        try {
            Prenotazione created = this.prenotazioneService.save(this.toEntity(prenotazione));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/prenotazioni/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePrenotazione(@PathParam("id") Long id, PrenotazioneDto prenotazione) {
        if (prenotazione == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            prenotazione.setId(id);
            Prenotazione entity = this.toEntity(prenotazione);
            boolean updated = this.prenotazioneService.update(entity);
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
        this.prenotazioneService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.prenotazioneService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-prenotazioni/{prenotazioniId}")
    public Response findByPrenotazioniId(@PathParam("prenotazioniId") Long prenotazioniId) {
        if (prenotazioniId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.prenotazioneService.findByPrenotazioniId(prenotazioniId))).build();
    }

    @GET
    @Path("/by-riga_prenotazione/{rigaPrenotazioneId}")
    public Response findByRigaPrenotazioneId(@PathParam("rigaPrenotazioneId") Long rigaPrenotazioneId) {
        if (rigaPrenotazioneId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.prenotazioneService.findByRigaPrenotazioneId(rigaPrenotazioneId))).build();
    }

    protected PrenotazioneDto toDto(Prenotazione entity) {
        if (entity == null) {
            return null;
        }
        PrenotazioneDto dto = new PrenotazioneDto();
        dto.setId(entity.getId());
        dto.setDataCreazione(entity.getDataCreazione());
        dto.setStato(entity.getStato());
        if (entity.getPrenotazioni() != null) {
            dto.setPrenotazioniId(entity.getPrenotazioni().getId());
        }
        if (entity.getRigaPrenotazione() != null) {
            List<Long> rigaPrenotazioneIds = new java.util.ArrayList<>();
            for (RigaPrenotazione relationEntity : entity.getRigaPrenotazione()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    rigaPrenotazioneIds.add(relationEntity.getId());
                }
            }
            dto.setRigaPrenotazioneIds(rigaPrenotazioneIds);
        }
        return dto;
    }

    protected List<PrenotazioneDto> toDtoList(List<Prenotazione> entities) {
        List<PrenotazioneDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Prenotazione entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Prenotazione toEntity(PrenotazioneDto dto) {
        if (dto == null) {
            return null;
        }
        Prenotazione entity = new Prenotazione();
        entity.setId(dto.getId());
        entity.setDataCreazione(dto.getDataCreazione());
        entity.setStato(dto.getStato());
        if (dto.getPrenotazioniId() != null) {
            Cliente relationEntity = new Cliente();
            relationEntity.setId(dto.getPrenotazioniId());
            entity.setPrenotazioni(relationEntity);
        }
        if (dto.getRigaPrenotazioneIds() != null) {
            List<RigaPrenotazione> rigaPrenotazioneEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getRigaPrenotazioneIds()) {
                if (relationId != null) {
                    RigaPrenotazione relationEntity = new RigaPrenotazione();
                    relationEntity.setId(relationId);
                    rigaPrenotazioneEntities.add(relationEntity);
                }
            }
            entity.setRigaPrenotazione(rigaPrenotazioneEntities);
        }
        return entity;
    }
}
