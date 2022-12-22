package service;

import java.util.Set;
import java.util.SortedSet;

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
     * functions and operations over the database.
     */

    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    /**
     * writes a new user in the database
     * 
     * @param user is the user to be written.
     * @throws CreateException
     */

    @Override
    public void createUser(User user) throws CreateException {
        try {
            if (!em.contains(user))
                em.persist(user);
        } catch (Exception e) {
            throw new CreateException();
        }
    }

    /**
     * @param user is the user to be updated
     */

    @Override
    public void updateUser(User user) throws UpdateException {
        try {
            if (em.contains(user))
                em.merge(user);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Override
    public void removeUser(User user) throws DeleteException {
        try {
            if (em.contains(user))
                em.remove(user);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public User findUserByLogin(String login) throws FindUserException {
        User u;
        try {
            u = em.find(User.class, login);
        } catch (Exception e) {
            throw new FindUserException();
        }

        return u;
    }

    @Override
    public User findUserByEmail(String email) throws FindUserException {
        User u;
        try {
            u = (User) em
                .createNamedQuery("findUserByEmail")
                    .setParameter("email", email)
                        .getSingleResult();
        } catch (Exception e) {
            throw new FindUserException();
        }
        return u;
    }

    @Override
    public Set<User> findUsersByName(String name) throws FindUserException {
        Set <User> users = new SortedSet <User> ();
        try {
        } catch (Exception e) {
            throw new FindUserException();
        }
        return users;
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
