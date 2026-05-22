package org.nvm.cars.controller;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nvm.cars.dto.AuthDto;
import org.nvm.cars.dto.ClienteDto;
import org.nvm.cars.dto.TitolareDto;
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
       if(authDto == null) {
           return Response.status(Response.Status.BAD_REQUEST)
                   .entity("Il body della richiesta è obbligatorio")
                   .build();
       }else{
           boolean verificato = this.credenzialiService.checkPassword(authDto.getUsername(), authDto.getPassword());
           if(verificato) {
               Credenziali c =  credenzialiService.findByUsername(authDto.getUsername());
               if(c == null) {
                   return  Response.status(Response.Status.UNAUTHORIZED).build();
               }
               String token = Jwt.issuer("nvmcars")
                       .subject(c.getUsername())
                       .groups(Set.of(c.getRuolo()))
                       .expiresAt(Instant.now().plusSeconds(3600))
                       .sign();
               return Response.ok(token).build();
           }else{
               return  Response.status(Response.Status.UNAUTHORIZED).build();
           }
       }
    }

    @Path("/register/titolare")
    @POST
    public Response registerTitolare(TitolareDto titolareDto) {
        if(titolareDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            Titolare t = new Titolare();

        }
    }

    @Path("/register/cliente")
    @POST
    public Response registerCliente(ClienteDto clienteDtoDto) {
        return   Response.status(Response.Status.BAD_REQUEST).build();
    }

}
