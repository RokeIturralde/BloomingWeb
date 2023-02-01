package user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Privilege;
import entities.Status;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindUserException;
import exceptions.PasswordRecoveryException;
import exceptions.UpdateException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import recovery.MailService;

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
     * writes a new user to the database
     *
     * @param user
     * @throws CreateException
     */
    @Override
    public void createUser(User user) throws CreateException {
        try {
            em.persist(user);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * updates a user
     *
     * @param user
     * @throws UpdateException
     */
    @Override
    public void updateUser(User user) throws UpdateException {
        try {
            if (!em.contains(user)) {
                em.merge(user);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * deletes a user by their login (ID)
     *
     * @param userId
     */
    @Override
    public void removeUser(String login) throws DeleteException {
        try {
            if (em.contains(findUserByLogin(login))) {
                em.remove(em.find(User.class, login));
            }
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public User findUserByLogin(String login) throws FindUserException {
        try {
            return em.find(User.class, login);
        } catch (Exception e) {
            throw new FindUserException(e.getMessage());
        }
    }

    @Override
    public User findUserByEmail(String email) throws FindUserException {
        try {
            return User.class.cast(
                    em.createNamedQuery("findUserByEmail")
                            .setParameter("userEmail", email)
                            .getSingleResult());
        } catch (Exception e) {
            throw new FindUserException(e.getMessage());
        }
    }

    @Override
    public List<User> findUsersByName(String name) throws FindUserException {
        try {
            return em.createNamedQuery("findUserByName")
                    .setParameter("userName", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new FindUserException(e.getMessage());
        }
    }

    @Override
    public List<User> findUsersByStatus(Status status) throws FindUserException {
        try {
            return em.createNamedQuery("findUserByStatus")
                    .setParameter("userStatus", status)
                    .getResultList();
        } catch (Exception e) {
            throw new FindUserException(e.getMessage());
        }
    }

    @Override
    public List<User> findUsersByPrivilege(Privilege privilege) throws FindUserException {
        try {
            return em.createNamedQuery("findUserByPrivilege")
                    .setParameter("userPrivilege", privilege)
                    .getResultList();
        } catch (Exception e) {
            throw new FindUserException(e.getMessage());
        }
    }

    @Override
    public void passwordRecovery(String userLogin) throws PasswordRecoveryException, UpdateException, FindUserException {
        User user = findUserByLogin(userLogin);
        if (user != null) {
            MailService mailService = new MailService(user.getEmail());
            String newPassword = mailService.getRecuperacion();
            String hashedPass = mailService.hashPassword(newPassword);
            user.setPassword(hashedPass);
            user.setLastPasswordChange(Date.valueOf(LocalDate.now()));
            updateUser(user);
        }
    }

}
