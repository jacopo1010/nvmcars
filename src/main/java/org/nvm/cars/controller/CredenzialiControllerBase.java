
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
import org.nvm.cars.model.Credenziali;
import org.nvm.cars.model.Titolare;
import org.nvm.cars.dto.CredenzialiDto;
import org.nvm.cars.service.CredenzialiService;

public abstract class CredenzialiControllerBase {

    @Inject
    protected CredenzialiService credenzialiService;

    @GET
    public Response getAllCredenzialis() {
        List<Credenziali> credenzialis = this.credenzialiService.findAll();
        return Response.ok(this.toDtoList(credenzialis)).build();
    }

    @GET
    @Path("/count")
    public Response countCredenzialis() {
        return Response.ok(this.credenzialiService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsCredenziali(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.credenzialiService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.credenzialiService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getCredenziali(@PathParam("id") Long id) {
        Optional<Credenziali> credenziali = this.credenzialiService.findById(id);
        if (!credenziali.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(credenziali.get())).build();
    }

    @POST
    public Response createCredenziali(CredenzialiDto credenziali) {
        try {
            Credenziali created = this.credenzialiService.save(this.toEntity(credenziali));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/credenziali/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCredenziali(@PathParam("id") Long id, CredenzialiDto credenziali) {
        if (credenziali == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            credenziali.setId(id);
            Credenziali entity = this.toEntity(credenziali);
            boolean updated = this.credenzialiService.update(entity);
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
        this.credenzialiService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.credenzialiService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    protected CredenzialiDto toDto(Credenziali entity) {
        if (entity == null) {
            return null;
        }
        CredenzialiDto dto = new CredenzialiDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        if (entity.getPossiede() != null) {
            dto.setPossiedeId(entity.getPossiede().getId());
        }
        return dto;
    }

    protected List<CredenzialiDto> toDtoList(List<Credenziali> entities) {
        List<CredenzialiDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Credenziali entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Credenziali toEntity(CredenzialiDto dto) {
        if (dto == null) {
            return null;
        }
        Credenziali entity = new Credenziali();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        if (dto.getPossiedeId() != null) {
            Titolare relationEntity = new Titolare();
            relationEntity.setId(dto.getPossiedeId());
            entity.setPossiede(relationEntity);
        }
        return entity;
    }
}
