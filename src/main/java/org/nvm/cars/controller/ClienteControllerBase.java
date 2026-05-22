
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
import org.nvm.cars.model.Cliente;
import org.nvm.cars.model.Automobile;
import org.nvm.cars.model.Prenotazione;
import org.nvm.cars.model.Credenziali;
import org.nvm.cars.dto.ClienteDto;
import org.nvm.cars.service.ClienteService;

public abstract class ClienteControllerBase {

    @Inject
    protected ClienteService clienteService;

    @GET
    public Response getAllClientes() {
        List<Cliente> clientes = this.clienteService.findAll();
        return Response.ok(this.toDtoList(clientes)).build();
    }

    @GET
    @Path("/count")
    public Response countClientes() {
        return Response.ok(this.clienteService.count()).build();
    }

    @GET
    @Path("/exists/{id}")
    public Response existsCliente(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.clienteService.existsById(id)).build();
    }

    @GET
    @Path("/search")
    public Response findByKeyword(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.clienteService.findByKeyword(keyword))).build();
    }

    @GET
    @Path("/{id}")
    public Response getCliente(@PathParam("id") Long id) {
        Optional<Cliente> cliente = this.clienteService.findById(id);
        if (!cliente.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(this.toDto(cliente.get())).build();
    }

    @POST
    public Response createCliente(ClienteDto cliente) {
        try {
            Cliente created = this.clienteService.save(this.toEntity(cliente));
            if (created.getId() == null) {
                return Response.serverError().build();
            }
            return Response.created(URI.create("/api/clienti/" + created.getId())).entity(this.toDto(created)).build();
        } catch (IllegalArgumentException exception) {
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") Long id, ClienteDto cliente) {
        if (cliente == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            cliente.setId(id);
            Cliente entity = this.toEntity(cliente);
            boolean updated = this.clienteService.update(entity);
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
        this.clienteService.deleteAll();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        boolean deleted = this.clienteService.delete(id);
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
        return Response.ok(this.toDtoList(this.clienteService.findByAutomobiliId(automobiliId))).build();
    }

    @GET
    @Path("/by-prenotazioni/{prenotazioniId}")
    public Response findByPrenotazioniId(@PathParam("prenotazioniId") Long prenotazioniId) {
        if (prenotazioniId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(this.toDtoList(this.clienteService.findByPrenotazioniId(prenotazioniId))).build();
    }

    protected ClienteDto toDto(Cliente entity) {
        if (entity == null) {
            return null;
        }
        ClienteDto dto = new ClienteDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setDataDiNascita(entity.getDataDiNascita());
        dto.setNumero(entity.getNumero());
        if (entity.getAutomobili() != null) {
            List<Long> automobiliIds = new java.util.ArrayList<>();
            for (Automobile relationEntity : entity.getAutomobili()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    automobiliIds.add(relationEntity.getId());
                }
            }
            dto.setAutomobiliIds(automobiliIds);
        }
        if (entity.getPrenotazioni() != null) {
            List<Long> prenotazioniIds = new java.util.ArrayList<>();
            for (Prenotazione relationEntity : entity.getPrenotazioni()) {
                if (relationEntity != null && relationEntity.getId() != null) {
                    prenotazioniIds.add(relationEntity.getId());
                }
            }
            dto.setPrenotazioniIds(prenotazioniIds);
        }
        if (entity.getPossiede() != null) {
            dto.setPossiedeId(entity.getPossiede().getId());
        }
        return dto;
    }

    protected List<ClienteDto> toDtoList(List<Cliente> entities) {
        List<ClienteDto> result = new java.util.ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (Cliente entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }

    protected Cliente toEntity(ClienteDto dto) {
        if (dto == null) {
            return null;
        }
        Cliente entity = new Cliente();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCognome(dto.getCognome());
        entity.setDataDiNascita(dto.getDataDiNascita());
        entity.setNumero(dto.getNumero());
        if (dto.getAutomobiliIds() != null) {
            List<Automobile> automobiliEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getAutomobiliIds()) {
                if (relationId != null) {
                    Automobile relationEntity = new Automobile();
                    relationEntity.setId(relationId);
                    automobiliEntities.add(relationEntity);
                }
            }
            entity.setAutomobili(automobiliEntities);
        }
        if (dto.getPrenotazioniIds() != null) {
            List<Prenotazione> prenotazioniEntities = new java.util.ArrayList<>();
            for (Long relationId : dto.getPrenotazioniIds()) {
                if (relationId != null) {
                    Prenotazione relationEntity = new Prenotazione();
                    relationEntity.setId(relationId);
                    prenotazioniEntities.add(relationEntity);
                }
            }
            entity.setPrenotazioni(prenotazioniEntities);
        }
        if (dto.getPossiedeId() != null) {
            Credenziali relationEntity = new Credenziali();
            relationEntity.setId(dto.getPossiedeId());
            entity.setPossiede(relationEntity);
        }
        return entity;
    }
}
