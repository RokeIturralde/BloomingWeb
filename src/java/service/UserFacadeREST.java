package service;

import entities.Privilege;
import entities.Status;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindUserException;
import exceptions.UpdateException;
import service.user.IUserManager;

import java.util.List;
import java.util.logging.Logger;
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
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("login") String login, User entity) {
        try {
            ejb.updateUser(entity);
        } catch (UpdateException ue) {
            LOGGER.severe(ue.getMessage());
            throw new InternalServerErrorException(ue.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("login") String login) {
        try {
            ejb.removeUser(login);
        } catch (DeleteException re) {
            LOGGER.severe(re.getMessage());
            throw new InternalServerErrorException(re.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("login") String login) {
        try {
            return ejb.findUserByLogin(login);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue.getMessage());
        }
    }

    @GET
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByName(@PathParam("name") String name) {
        try {
            return ejb.findUsersByName(name);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue);
        }
    }

    @GET
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByPrivilege(@PathParam("privilege") Privilege privilege) {
        try {
            return ejb.findUsersByPrivilege(privilege);
        } catch (FindUserException fue) {
            LOGGER.severe(fue.getMessage());
            throw new InternalServerErrorException(fue.getMessage());
        }
    }    
}