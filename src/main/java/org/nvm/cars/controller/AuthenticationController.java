package org.nvm.cars.controller;

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
import org.nvm.cars.model.Credenziali;
import org.nvm.cars.service.ClienteService;
import org.nvm.cars.service.CredenzialiService;
import org.nvm.cars.service.TitolareService;

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
          if(authDto.getRuolo().equals(Credenziali.TITOLARE)){
              boolean verificato = this.credenzialiService.checkPassword(authDto.getUsername(), authDto.getPassword());
              if(verificato) {
                  return Response.ok().build();
              }else {
                  return Response.status(Response.Status.UNAUTHORIZED).build();
              }
          }else{
              return Response.status(Response.Status.UNAUTHORIZED).build();
          }
       }
    }
}
