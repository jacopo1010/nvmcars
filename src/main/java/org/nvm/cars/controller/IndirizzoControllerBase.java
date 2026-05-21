
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
import org.nvm.cars.model.Indirizzo;
import org.nvm.cars.model.Attivita;
import org.nvm.cars.dto.IndirizzoDto;
import org.nvm.cars.service.IndirizzoService;

public abstract class IndirizzoControllerBase {

    @Inject
    protected IndirizzoService indirizzoService;

    @GET
    public Response getAllIndirizzos() {
        List<Indirizzo> indirizzos = this.indirizzoService.findAll();
        return Response.ok(this.toDtoList(indirizzos)).build();
    }

    @GET
    @Path("/count")
    public Response countIndirizzos() {
        return Response.ok(this.indirizzoService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsIndirizzo(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.indirizzoService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.indirizzoService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getIndirizzo(@PathParam("id") Long id) {
        Optional<Indirizzo> indirizzo = this.indirizzoService.findById(id);
        if (!indirizzo.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(indirizzo.get())).build();
    }

    @POST
    public Response createIndirizzo(IndirizzoDto indirizzo) {
        try {
            Indirizzo created = this.indirizzoService.save(this.toEntity(indirizzo));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/indirizzo/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateIndirizzo(@PathParam("id") Long id, IndirizzoDto indirizzo) {
        if (indirizzo == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            indirizzo.setId(id);
            Indirizzo entity = this.toEntity(indirizzo);
            boolean updated = this.indirizzoService.update(entity);
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
        this.indirizzoService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.indirizzoService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    protected IndirizzoDto toDto(Indirizzo entity) {
        if (entity == null) {
            return null;
        }
        IndirizzoDto dto = new IndirizzoDto();
        dto.setId(entity.getId());
        dto.setVia(entity.getVia());
        dto.setCivico(entity.getCivico());
        dto.setCitta(entity.getCitta());
        dto.setCap(entity.getCap());
        dto.setProvincia(entity.getProvincia());
        dto.setLatitudine(entity.getLatitudine());
        dto.setLongitudine(entity.getLongitudine());
        if (entity.getAttivita() != null) {
            dto.setAttivitaId(entity.getAttivita().getId());
        }
        return dto;
    }

    protected List<IndirizzoDto> toDtoList(List<Indirizzo> entities) {
        List<IndirizzoDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Indirizzo entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Indirizzo toEntity(IndirizzoDto dto) {
        if (dto == null) {
            return null;
        }
        Indirizzo entity = new Indirizzo();
        entity.setId(dto.getId());
        entity.setVia(dto.getVia());
        entity.setCivico(dto.getCivico());
        entity.setCitta(dto.getCitta());
        entity.setCap(dto.getCap());
        entity.setProvincia(dto.getProvincia());
        entity.setLatitudine(dto.getLatitudine());
        entity.setLongitudine(dto.getLongitudine());
        if (dto.getAttivitaId() != null) {
            Attivita relationEntity = new Attivita();
            relationEntity.setId(dto.getAttivitaId());
            entity.setAttivita(relationEntity);
        }
        return entity;
    }
}
