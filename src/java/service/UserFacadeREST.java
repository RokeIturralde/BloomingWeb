/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Privilege;
import entities.Status;
import entities.User;
import exceptions.CreateException;
import exceptions.FindUserException;
import exceptions.UpdateException;
import service.user.IUserManager;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import org.glassfish.security.services.common.PrivilededLookup;


/**
 *
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
        } catch (UpdateException ejb) {
            throw new InternalServerErrorException(ejb.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("login") String login) {
        try {
            ejb.removeUser(login);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("login") String login) {
        try {
            return ejb.findUserByLogin(login);
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }
    /*
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        try {
            ejb.
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    */

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserByEmail(@PathParam("email") String email) {
        try {
            return ejb.findUserByEmail(email);
        } catch (FindUserException fue) {
            throw new InternalServerErrorException(fue.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByName(@PathParam("name") String name) {
        try {
            return ejb.findUsersByName(name);
        } catch (FindUserException fue) {
            throw new InternalServerErrorException(fue);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByStatus(@PathParam("status") Status status) {
        try {
            return ejb.findUsersByStatus(status);
        } catch (FindUserException fue) {
            throw new FindUserException(fue.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByPrivilege(@PathParam("privilege") Privilege privilege) {
        try {
            return ejb.findUsersByPrivilege(privilege);
        } catch (FindUserException fue) {
            throw new InternalServerErrorException(fue.getMessage(););
        }
    }


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return null;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return null;
    }

    protected EntityManager getEntityManager() {
        return null;
    }
    
}
