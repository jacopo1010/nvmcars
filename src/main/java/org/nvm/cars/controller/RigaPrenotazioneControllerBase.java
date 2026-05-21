
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
import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.model.Automobile;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.model.Prenotazione;
import org.nvm.cars.model.Disponiblita;
import org.nvm.cars.dto.RigaPrenotazioneDto;
import org.nvm.cars.service.RigaPrenotazioneService;

public abstract class RigaPrenotazioneControllerBase {

    @Inject
    protected RigaPrenotazioneService rigaPrenotazioneService;

    @GET
    public Response getAllRigaPrenotaziones() {
        List<RigaPrenotazione> rigaPrenotaziones = this.rigaPrenotazioneService.findAll();
        return Response.ok(this.toDtoList(rigaPrenotaziones)).build();
    }

    @GET
    @Path("/count")
    public Response countRigaPrenotaziones() {
        return Response.ok(this.rigaPrenotazioneService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsRigaPrenotazione(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.rigaPrenotazioneService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.rigaPrenotazioneService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getRigaPrenotazione(@PathParam("id") Long id) {
        Optional<RigaPrenotazione> rigaPrenotazione = this.rigaPrenotazioneService.findById(id);
        if (!rigaPrenotazione.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(rigaPrenotazione.get())).build();
    }

    @POST
    public Response createRigaPrenotazione(RigaPrenotazioneDto rigaPrenotazione) {
        try {
            RigaPrenotazione created = this.rigaPrenotazioneService.save(this.toEntity(rigaPrenotazione));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/righe-prenotazione/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateRigaPrenotazione(@PathParam("id") Long id, RigaPrenotazioneDto rigaPrenotazione) {
        if (rigaPrenotazione == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            rigaPrenotazione.setId(id);
            RigaPrenotazione entity = this.toEntity(rigaPrenotazione);
            boolean updated = this.rigaPrenotazioneService.update(entity);
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
        this.rigaPrenotazioneService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.rigaPrenotazioneService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-righe_prenotazione/{righePrenotazioneId}")
    public Response findByRighePrenotazioneId(@PathParam("righePrenotazioneId") Long righePrenotazioneId) {
        if (righePrenotazioneId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.rigaPrenotazioneService.findByRighePrenotazioneId(righePrenotazioneId))).build();
    }

    @GET
    @Path("/by-riga_prenotazione/{rigaPrenotazioneId}")
    public Response findByRigaPrenotazioneId(@PathParam("rigaPrenotazioneId") Long rigaPrenotazioneId) {
        if (rigaPrenotazioneId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.rigaPrenotazioneService.findByRigaPrenotazioneId(rigaPrenotazioneId))).build();
    }

    @GET
    @Path("/by-prenotazioni/{prenotazioniId}")
    public Response findByPrenotazioniId(@PathParam("prenotazioniId") Long prenotazioniId) {
        if (prenotazioniId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.rigaPrenotazioneService.findByPrenotazioniId(prenotazioniId))).build();
    }

    protected RigaPrenotazioneDto toDto(RigaPrenotazione entity) {
        if (entity == null) {
            return null;
        }
        RigaPrenotazioneDto dto = new RigaPrenotazioneDto();
        dto.setId(entity.getId());
        dto.setNote(entity.getNote());
        if (entity.getPrenotazioneAffiliata() != null) {
            dto.setPrenotazioneAffiliataId(entity.getPrenotazioneAffiliata().getId());
        }
        if (entity.getRighePrenotazione() != null) {
            dto.setRighePrenotazioneId(entity.getRighePrenotazione().getId());
        }
        if (entity.getRigaPrenotazione() != null) {
            dto.setRigaPrenotazioneId(entity.getRigaPrenotazione().getId());
        }
        if (entity.getPrenotazioni() != null) {
            dto.setPrenotazioniId(entity.getPrenotazioni().getId());
        }
        return dto;
    }

    protected List<RigaPrenotazioneDto> toDtoList(List<RigaPrenotazione> entities) {
        List<RigaPrenotazioneDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (RigaPrenotazione entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected RigaPrenotazione toEntity(RigaPrenotazioneDto dto) {
        if (dto == null) {
            return null;
        }
        RigaPrenotazione entity = new RigaPrenotazione();
        entity.setId(dto.getId());
        entity.setNote(dto.getNote());
        if (dto.getPrenotazioneAffiliataId() != null) {
            Automobile relationEntity = new Automobile();
            relationEntity.setId(dto.getPrenotazioneAffiliataId());
            entity.setPrenotazioneAffiliata(relationEntity);
        }
        if (dto.getRighePrenotazioneId() != null) {
            Servizio relationEntity = new Servizio();
            relationEntity.setId(dto.getRighePrenotazioneId());
            entity.setRighePrenotazione(relationEntity);
        }
        if (dto.getRigaPrenotazioneId() != null) {
            Prenotazione relationEntity = new Prenotazione();
            relationEntity.setId(dto.getRigaPrenotazioneId());
            entity.setRigaPrenotazione(relationEntity);
        }
        if (dto.getPrenotazioniId() != null) {
            Disponiblita relationEntity = new Disponiblita();
            relationEntity.setId(dto.getPrenotazioniId());
            entity.setPrenotazioni(relationEntity);
        }
        return entity;
    }
}
