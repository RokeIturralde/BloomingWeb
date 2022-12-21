package service;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Privilege;
import entities.Status;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindUserException;
import exceptions.UpdateException;

import javax.ejb.Stateless;
import service.user.IUserManager;

/**
 * @author dani
 */

@Stateless
public class EJBUserManager implements IUserManager {

    /**
     * the entity manager is used to manage all the 
     */

    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;


    /**
     * writes a new user in the database
     * @param user is the user to be written.
     * 
     * TODO: does it have to be checked before?
     */

    @Override
    public void createUser(User user) throws CreateException {
        if (user == null)
            throw new CreateException("The user is null.");
        else
            if (user.getLogin().length() <= 4)
        em.persist(user);
    }


    @Override
    public void updateUser(User user) throws UpdateException {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void removeUser(User user) throws DeleteException {
        // TODO Auto-generated method stub
        
    }


    @Override
    public User findUserByLogin(String login) throws FindUserException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public User findUserByEmail(String email) throws FindUserException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Set<User> findUsersByName(String name) throws FindUserException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Set<User> findUsersByStatus(Status status) throws FindUserException {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Set<User> findUsersByPrivilege(Privilege privilege) throws FindUserException {
        // TODO Auto-generated method stub
        return null;
    }
    

}
