package org.nvm.cars.service;


import jakarta.enterprise.context.ApplicationScoped;
import org.mindrot.jbcrypt.BCrypt;
import org.nvm.cars.model.Credenziali;

@ApplicationScoped
public class CredenzialiService extends CredenzialiServiceBase {

    public Credenziali findByUsername(String username)
    {
        return this.repository.findByUsername(username);
    }

    public boolean SavePassword(String email, String password)
    {
      //da implementare
      return true;
    }

    public boolean checkPassword(String username,String password)
    {
        Credenziali credenziali = this.repository.findByUsername(username);
        if(credenziali==null) {
            return false;
        }
        return  BCrypt.checkpw(password,credenziali.getPassword());
    }

}
