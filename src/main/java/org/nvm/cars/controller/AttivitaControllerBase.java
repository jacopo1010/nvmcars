
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
import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Titolare;
import org.nvm.cars.model.Indirizzo;
import org.nvm.cars.model.ListinoServizi;
import org.nvm.cars.model.Disponiblita;
import org.nvm.cars.dto.AttivitaDto;
import org.nvm.cars.service.AttivitaService;

public abstract class AttivitaControllerBase {

    @Inject
    protected AttivitaService attivitaService;

    @GET
    public Response getAllAttivitas() {
        List<Attivita> attivitas = this.attivitaService.findAll();
        return Response.ok(this.toDtoList(attivitas)).build();
    }

    @GET
    @Path("/count")
    public Response countAttivitas() {
        return Response.ok(this.attivitaService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsAttivita(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.attivitaService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.attivitaService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getAttivita(@PathParam("id") Long id) {
        Optional<Attivita> attivita = this.attivitaService.findById(id);
        if (!attivita.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(attivita.get())).build();
    }

    @POST
    public Response createAttivita(AttivitaDto attivita) {
        try {
            Attivita created = this.attivitaService.save(this.toEntity(attivita));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/myAttivita/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAttivita(@PathParam("id") Long id, AttivitaDto attivita) {
        if (attivita == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            attivita.setId(id);
            Attivita entity = this.toEntity(attivita);
            boolean updated = this.attivitaService.update(entity);
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
        this.attivitaService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.attivitaService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/by-titolare/{titolareId}")
    public Response findByTitolareId(@PathParam("titolareId") Long titolareId) {
        if (titolareId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.attivitaService.findByTitolareId(titolareId))).build();
    }

    @GET
    @Path("/by-disponibilita/{disponibilitaId}")
    public Response findByDisponibilitaId(@PathParam("disponibilitaId") Long disponibilitaId) {
        if (disponibilitaId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.attivitaService.findByDisponibilitaId(disponibilitaId))).build();
    }

    protected AttivitaDto toDto(Attivita entity) {
        if (entity == null) {
            return null;
        }
        AttivitaDto dto = new AttivitaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescrizione(entity.getDescrizione());
        dto.setNumero(entity.getNumero());
        dto.setEmail(entity.getEmail());
        if (entity.getTitolare() != null) {
            dto.setTitolareId(entity.getTitolare().getId());
        }
        if (entity.getIndirizzo() != null) {
            dto.setIndirizzoId(entity.getIndirizzo().getId());
        }
        if (entity.getListino() != null) {
            dto.setListinoId(entity.getListino().getId());
        }
        if (entity.getDisponibilita() != null) {
            List<Long> disponibilitaIds = new java.util.ArrayList<>();
            for (Disponiblita relationEntity : entity.getDisponibilita()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    disponibilitaIds.add(relationEntity.getId());
                }
            }
            dto.setDisponibilitaIds(disponibilitaIds);
        }
        return dto;
    }

    protected List<AttivitaDto> toDtoList(List<Attivita> entities) {
        List<AttivitaDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Attivita entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Attivita toEntity(AttivitaDto dto) {
        if (dto == null) {
            return null;
        }
        Attivita entity = new Attivita();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDescrizione(dto.getDescrizione());
        entity.setNumero(dto.getNumero());
        entity.setEmail(dto.getEmail());
        if (dto.getTitolareId() != null) {
            Titolare relationEntity = new Titolare();
            relationEntity.setId(dto.getTitolareId());
            entity.setTitolare(relationEntity);
        }
        if (dto.getIndirizzoId() != null) {
            Indirizzo relationEntity = new Indirizzo();
            relationEntity.setId(dto.getIndirizzoId());
            entity.setIndirizzo(relationEntity);
        }
        if (dto.getListinoId() != null) {
            ListinoServizi relationEntity = new ListinoServizi();
            relationEntity.setId(dto.getListinoId());
            entity.setListino(relationEntity);
        }
        if (dto.getDisponibilitaIds() != null) {
            List<Disponiblita> disponibilitaEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getDisponibilitaIds()) {
                if (relationId != null) {
                    Disponiblita relationEntity = new Disponiblita();
                    relationEntity.setId(relationId);
                    disponibilitaEntities.add(relationEntity);
                }
            }
            entity.setDisponibilita(disponibilitaEntities);
        }
        return entity;
    }
}
