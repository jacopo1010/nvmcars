
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
import org.nvm.cars.model.Automobile;
import org.nvm.cars.model.Cliente;
import org.nvm.cars.model.RigaPrenotazione;
import org.nvm.cars.dto.AutomobileDto;
import org.nvm.cars.service.AutomobileService;

public abstract class AutomobileControllerBase {

    @Inject
    protected AutomobileService automobileService;

    @GET
    public Response getAllAutomobiles() {
        List<Automobile> automobiles = this.automobileService.findAll();
        return Response.ok(this.toDtoList(automobiles)).build();
    }

    @GET
    @Path("/count")
    public Response countAutomobiles() {
        return Response.ok(this.automobileService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsAutomobile(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.automobileService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.automobileService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getAutomobile(@PathParam("id") Long id) {
        Optional<Automobile> automobile = this.automobileService.findById(id);
        if (!automobile.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(automobile.get())).build();
    }

    @POST
    public Response createAutomobile(AutomobileDto automobile) {
        try {
            Automobile created = this.automobileService.save(this.toEntity(automobile));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/myAuto/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAutomobile(@PathParam("id") Long id, AutomobileDto automobile) {
        if (automobile == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            automobile.setId(id);
            Automobile entity = this.toEntity(automobile);
            boolean updated = this.automobileService.update(entity);
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
        this.automobileService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.automobileService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-automobili/{automobiliId}")
    public Response findByAutomobiliId(@PathParam("automobiliId") Long automobiliId) {
        if (automobiliId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.automobileService.findByAutomobiliId(automobiliId))).build();
    }

    protected AutomobileDto toDto(Automobile entity) {
        if (entity == null) {
            return null;
        }
        AutomobileDto dto = new AutomobileDto();
        dto.setId(entity.getId());
        dto.setTarga(entity.getTarga());
        dto.setMarca(entity.getMarca());
        dto.setModello(entity.getModello());
        dto.setAnno(entity.getAnno());
        if (entity.getAutomobili() != null) {
            dto.setAutomobiliId(entity.getAutomobili().getId());
        }
        if (entity.getPrenotazioneAffiliata() != null) {
            dto.setPrenotazioneAffiliataId(entity.getPrenotazioneAffiliata().getId());
        }
        return dto;
    }

    protected List<AutomobileDto> toDtoList(List<Automobile> entities) {
        List<AutomobileDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Automobile entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Automobile toEntity(AutomobileDto dto) {
        if (dto == null) {
            return null;
        }
        Automobile entity = new Automobile();
        entity.setId(dto.getId());
        entity.setTarga(dto.getTarga());
        entity.setMarca(dto.getMarca());
        entity.setModello(dto.getModello());
        entity.setAnno(dto.getAnno());
        if (dto.getAutomobiliId() != null) {
            Cliente relationEntity = new Cliente();
            relationEntity.setId(dto.getAutomobiliId());
            entity.setAutomobili(relationEntity);
        }
        if (dto.getPrenotazioneAffiliataId() != null) {
            RigaPrenotazione relationEntity = new RigaPrenotazione();
            relationEntity.setId(dto.getPrenotazioneAffiliataId());
            entity.setPrenotazioneAffiliata(relationEntity);
        }
        return entity;
    }
}
