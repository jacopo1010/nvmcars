package org.nvm.cars.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.nvm.cars.model.Credenziali;

@ApplicationScoped
public class CredenzialiService extends CredenzialiServiceBase {

    public Credenziali findByUsername(String username)
    {
        return this.repository.findByUsername(username);
    }

    public boolean checkPassword(String username,String password)
    {
        Credenziali credenziali = this.repository.findByUsername(username);
        if(credenziali==null) {
            return false;
        }
        return  BCrypt.checkpw(password,credenziali.getPassword());
    }

    @Override
    @Transactional
    public Credenziali save(Credenziali entity) {
        String psw = entity.getPassword();
        entity.setPassword(BCrypt.hashpw(psw, BCrypt.gensalt()));
        return super.save(entity);
    }
}
