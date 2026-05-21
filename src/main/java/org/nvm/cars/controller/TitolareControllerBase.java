
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
import org.nvm.cars.model.Titolare;
import org.nvm.cars.model.Attivita;
import org.nvm.cars.model.Credenziali;
import org.nvm.cars.dto.TitolareDto;
import org.nvm.cars.service.TitolareService;

public abstract class TitolareControllerBase {

    @Inject
    protected TitolareService titolareService;

    @GET
    public Response getAllTitolares() {
        List<Titolare> titolares = this.titolareService.findAll();
        return Response.ok(this.toDtoList(titolares)).build();
    }

    @GET
    @Path("/count")
    public Response countTitolares() {
        return Response.ok(this.titolareService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsTitolare(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.titolareService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.titolareService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getTitolare(@PathParam("id") Long id) {
        Optional<Titolare> titolare = this.titolareService.findById(id);
        if (!titolare.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(titolare.get())).build();
    }

    @POST
    public Response createTitolare(TitolareDto titolare) {
        try {
            Titolare created = this.titolareService.save(this.toEntity(titolare));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/titolare/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateTitolare(@PathParam("id") Long id, TitolareDto titolare) {
        if (titolare == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            titolare.setId(id);
            Titolare entity = this.toEntity(titolare);
            boolean updated = this.titolareService.update(entity);
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
        this.titolareService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.titolareService.delete(id);
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
        return Response.ok(this.toDtoList(this.titolareService.findByAttivitaId(attivitaId))).build();
    }

    protected TitolareDto toDto(Titolare entity) {
        if (entity == null) {
            return null;
        }
        TitolareDto dto = new TitolareDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setDataDiNascita(entity.getDataDiNascita());
        dto.setNumero(entity.getNumero());
        dto.setRegioneSociale(entity.getRegioneSociale());
        dto.setPartitaIva(entity.getPartitaIva());
        dto.setCodiceFiscale(entity.getCodiceFiscale());
        if (entity.getAttivita() != null) {
            List<Long> attivitaIds = new java.util.ArrayList<>();
            for (Attivita relationEntity : entity.getAttivita()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    attivitaIds.add(relationEntity.getId());
                }
            }
            dto.setAttivitaIds(attivitaIds);
        }
        if (entity.getPossiede() != null) {
            dto.setPossiedeId(entity.getPossiede().getId());
        }
        return dto;
    }

    protected List<TitolareDto> toDtoList(List<Titolare> entities) {
        List<TitolareDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Titolare entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Titolare toEntity(TitolareDto dto) {
        if (dto == null) {
            return null;
        }
        Titolare entity = new Titolare();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCognome(dto.getCognome());
        entity.setDataDiNascita(dto.getDataDiNascita());
        entity.setNumero(dto.getNumero());
        entity.setRegioneSociale(dto.getRegioneSociale());
        entity.setPartitaIva(dto.getPartitaIva());
        entity.setCodiceFiscale(dto.getCodiceFiscale());
        if (dto.getAttivitaIds() != null) {
            List<Attivita> attivitaEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getAttivitaIds()) {
                if (relationId != null) {
                    Attivita relationEntity = new Attivita();
                    relationEntity.setId(relationId);
                    attivitaEntities.add(relationEntity);
                }
            }
            entity.setAttivita(attivitaEntities);
        }
        if (dto.getPossiedeId() != null) {
            Credenziali relationEntity = new Credenziali();
            relationEntity.setId(dto.getPossiedeId());
            entity.setPossiede(relationEntity);
        }
        return entity;
    }
}
