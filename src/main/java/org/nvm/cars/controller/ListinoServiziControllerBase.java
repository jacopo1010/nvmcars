
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
import org.nvm.cars.model.ListinoServizi;
import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Servizio;
import org.nvm.cars.dto.ListinoServiziDto;
import org.nvm.cars.service.ListinoServiziService;

public abstract class ListinoServiziControllerBase {

    @Inject
    protected ListinoServiziService listinoServiziService;

    @GET
    public Response getAllListinoServizis() {
        List<ListinoServizi> listinoServizis = this.listinoServiziService.findAll();
        return Response.ok(this.toDtoList(listinoServizis)).build();
    }

    @GET
    @Path("/count")
    public Response countListinoServizis() {
        return Response.ok(this.listinoServiziService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsListinoServizi(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.listinoServiziService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.listinoServiziService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getListinoServizi(@PathParam("id") Long id) {
        Optional<ListinoServizi> listinoServizi = this.listinoServiziService.findById(id);
        if (!listinoServizi.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(listinoServizi.get())).build();
    }

    @POST
    public Response createListinoServizi(ListinoServiziDto listinoServizi) {
        try {
            ListinoServizi created = this.listinoServiziService.save(this.toEntity(listinoServizi));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/listino/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateListinoServizi(@PathParam("id") Long id, ListinoServiziDto listinoServizi) {
        if (listinoServizi == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            listinoServizi.setId(id);
            ListinoServizi entity = this.toEntity(listinoServizi);
            boolean updated = this.listinoServiziService.update(entity);
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
        this.listinoServiziService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.listinoServiziService.delete(id);
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
        return Response.ok(this.toDtoList(this.listinoServiziService.findByServiziId(serviziId))).build();
    }

    protected ListinoServiziDto toDto(ListinoServizi entity) {
        if (entity == null) {
            return null;
        }
        ListinoServiziDto dto = new ListinoServiziDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        if (entity.getListino() != null) {
            dto.setListinoId(entity.getListino().getId());
        }
        if (entity.getServizi() != null) {
            List<Long> serviziIds = new java.util.ArrayList<>();
            for (Servizio relationEntity : entity.getServizi()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    serviziIds.add(relationEntity.getId());
                }
            }
            dto.setServiziIds(serviziIds);
        }
        return dto;
    }

    protected List<ListinoServiziDto> toDtoList(List<ListinoServizi> entities) {
        List<ListinoServiziDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (ListinoServizi entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected ListinoServizi toEntity(ListinoServiziDto dto) {
        if (dto == null) {
            return null;
        }
        ListinoServizi entity = new ListinoServizi();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        if (dto.getListinoId() != null) {
            Attivita relationEntity = new Attivita();
            relationEntity.setId(dto.getListinoId());
            entity.setListino(relationEntity);
        }
        if (dto.getServiziIds() != null) {
            List<Servizio> serviziEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getServiziIds()) {
                if (relationId != null) {
                    Servizio relationEntity = new Servizio();
                    relationEntity.setId(relationId);
                    serviziEntities.add(relationEntity);
                }
            }
            entity.setServizi(serviziEntities);
        }
        return entity;
    }
}
