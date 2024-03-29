package service;

import user.IUserManager;
import entities.Status;
import entities.User;
import exceptions.UpdateException;

import java.util.List;
import java.util.logging.Logger;

import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindUserException;
import exceptions.LoginDoesNotExistException;
import exceptions.NotThePasswordException;
import exceptions.PasswordRecoveryException;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author dani
 */
@Stateless
@Path("entities.user")
public class UserFacadeREST {

    @EJB
    private IUserManager ejb;

    private Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        try {
            ejb.createUser(entity);
        } catch (CreateException ce) {
            LOGGER.severe(ce.getMessage());
            throw new InternalServerErrorException(ce.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(User entity) {
        try {
            ejb.updateUser(entity);
        } catch (UpdateException ue) {
            LOGGER.severe(ue.getMessage());
            throw new InternalServerErrorException(ue.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        try {
            ejb.removeUser(id);
        } catch (DeleteException de) {
            LOGGER.severe(de.getMessage());
            throw new InternalServerErrorException(de.getMessage());
        }
    }

    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByName(@PathParam("name") String name) {
        try {
            return ejb.findUsersByName(name);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue.getMessage());
        }
    }

    @GET
    @Path("findByEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserByEmail(@PathParam("email") String email) {
        try {
            return ejb.findUserByEmail(email);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue.getMessage());
        }
    }

    @GET
    @Path("findByStatus/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByStatus(@PathParam("status") Status status) {
        try {
            return ejb.findUsersByStatus(status);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue.getMessage());
        }
    }

    @GET
    @Path("findUseraByLogin/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserByLogin(@PathParam("login") String login) {
        try {
            return ejb.findUserByLogin(login);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue.getMessage());
        }
    }

    /**
     * GET method to can enter to the application: it uses business method
     * signIn.
     *
     *
     * @param loginUser An String with the login of the user who wants to enter
     * @param password Sn String with the password of the user who wants to
     * enter
     * @return
     */
    @GET
    @Path("signIn/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User signIn(@PathParam("login") String loginUser, @PathParam("password") String password) {
        User user = new User();
        try {
            user = ejb.signIn(loginUser, password);
        } catch (LoginDoesNotExistException | NotThePasswordException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
        return user;
        }
    
    @GET
    @Path("recoverPassword/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void recoverPassword(@PathParam("login") String login) {
        try {
            ejb.passwordRecovery(login);
        } catch (UpdateException | PasswordRecoveryException | FindUserException ue) {
            LOGGER.severe(ue.getMessage());
            throw new InternalServerErrorException(ue.getMessage());
        }
    }
}
