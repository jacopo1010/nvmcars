package org.nvm.cars.controller;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nvm.cars.dto.AuthDto;
import org.nvm.cars.dto.ClienteDto;
import org.nvm.cars.dto.RegisterClienteDto;
import org.nvm.cars.dto.RegisterTitolareDto;
import org.nvm.cars.dto.TitolareDto;
import org.nvm.cars.model.Cliente;
import org.nvm.cars.model.Credenziali;
import org.nvm.cars.model.Titolare;
import org.nvm.cars.service.ClienteService;
import org.nvm.cars.service.CredenzialiService;
import org.nvm.cars.service.TitolareService;

import java.time.Instant;
import java.util.Set;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@PermitAll
public class AuthenticationController {

    @Inject
    CredenzialiService credenzialiService;
    @Inject
    ClienteService clienteService;
    @Inject
    TitolareService titolareService;

    @Path("/login")
    @POST
    public Response login(AuthDto authDto) {
        if (authDto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Il body della richiesta è obbligatorio")
                    .build();
        } else {
            boolean verificato = this.credenzialiService.checkPassword(authDto.getUsername(), authDto.getPassword());
            if (verificato) {
                Credenziali c = credenzialiService.findByUsername(authDto.getUsername());
                if (c == null) {
                    return Response.status(Response.Status.UNAUTHORIZED).build();
                }
                String token = Jwt.issuer("nvmcars")
                        .subject(c.getUsername())
                        .groups(Set.of(c.getRuolo()))
                        .expiresAt(Instant.now().plusSeconds(3600))
                        .sign();
                return Response.ok(token).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
    }

    @Path("/register/titolare")
    @POST
    @Transactional
    public Response registerTitolare(RegisterTitolareDto titolareDto) {
        if (titolareDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Credenziali credenziali = new Credenziali();
            this.setCredenziali(credenziali, titolareDto);
            Credenziali salvate = this.credenzialiService.save(credenziali);
            if (salvate == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Titolare titolare = new Titolare();
            this.setTitolare(titolare, titolareDto, salvate);
            Titolare creato = this.titolareService.save(titolare);
            if (creato == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            return Response.status(Response.Status.CREATED)
                    .entity(this.toTitolareDto(creato))
                    .build();
        }
    }

    private void setCredenziali(Credenziali credenziali, RegisterTitolareDto titolareDto) {
        credenziali.setUsername(titolareDto.getUsername());
        credenziali.setPassword(titolareDto.getPassword());
        credenziali.setEmail(titolareDto.getEmail());
        credenziali.setRuolo(Credenziali.TITOLARE);
    }

    private void setTitolare(Titolare titolare, RegisterTitolareDto titolareDto, Credenziali credenziali) {
        titolare.setNome(titolareDto.getNome());
        titolare.setCognome(titolareDto.getCognome());
        titolare.setNumero(titolareDto.getNumero());
        titolare.setDataDiNascita(titolareDto.getDataDiNascita());
        titolare.setCodiceFiscale(titolareDto.getCodiceFiscale());
        titolare.setPartitaIva(titolareDto.getPartitaIva());
        titolare.setRegioneSociale(titolareDto.getRegioneSociale());
        titolare.setPossiede(credenziali);
    }

    @Path("/register/cliente")
    @POST
    @Transactional
    public Response registerCliente(RegisterClienteDto clienteDto) {
        if (clienteDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Credenziali credenziali = new Credenziali();
            this.setCredenziali(credenziali, clienteDto);
            Credenziali salvate = this.credenzialiService.save(credenziali);
            if (salvate == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Cliente cliente = new Cliente();
            this.setCliente(cliente, clienteDto, salvate);
            Cliente creato = this.clienteService.save(cliente);
            if (creato == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            return Response.status(Response.Status.CREATED)
                    .entity(this.toClienteDto(creato))
                    .build();
        }
    }

    private void setCredenziali(Credenziali credenziali, RegisterClienteDto clienteDto) {
        credenziali.setUsername(clienteDto.getUsername());
        credenziali.setPassword(clienteDto.getPassword());
        credenziali.setEmail(clienteDto.getEmail());
        credenziali.setRuolo(Credenziali.UTENTE);
    }

    private void setCliente(Cliente cliente, RegisterClienteDto clienteDto, Credenziali credenziali) {
        cliente.setNome(clienteDto.getNome());
        cliente.setCognome(clienteDto.getCognome());
        cliente.setNumero(clienteDto.getNumero());
        cliente.setDataDiNascita(clienteDto.getDataDiNascita());
        cliente.setPossiede(credenziali);
    }

    private TitolareDto toTitolareDto(Titolare titolare) {
        TitolareDto dto = new TitolareDto();
        dto.setId(titolare.getId());
        dto.setNome(titolare.getNome());
        dto.setCognome(titolare.getCognome());
        dto.setDataDiNascita(titolare.getDataDiNascita());
        dto.setNumero(titolare.getNumero());
        dto.setRegioneSociale(titolare.getRegioneSociale());
        dto.setPartitaIva(titolare.getPartitaIva());
        dto.setCodiceFiscale(titolare.getCodiceFiscale());
        if (titolare.getPossiede() != null) {
            dto.setPossiedeId(titolare.getPossiede().getId());
        }
        return dto;
    }

    private ClienteDto toClienteDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCognome(cliente.getCognome());
        dto.setDataDiNascita(cliente.getDataDiNascita());
        dto.setNumero(cliente.getNumero());
        if (cliente.getPossiede() != null) {
            dto.setPossiedeId(cliente.getPossiede().getId());
        }
        return dto;
    }
}
