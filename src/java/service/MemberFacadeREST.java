/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Member;
import entities.MembershipPlan;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindMemberException;
import exceptions.UpdateException;

import java.lang.module.FindException;
import java.util.List;

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

import java.util.List;
import java.util.logging.Logger;


/**
 * @author dani
 */
@Stateless
@Path("entities.member")
public class MemberFacadeREST extends AbstractFacade<Member> {

    @EJB
    private IMemberManager ejb;

    private Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getTypeName());

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Member entity) {
        try {
            ejb.createMember(entity);
        } catch (CreateException ce) {
            LOGGER.severe(ce.getMessage());
            throw new InternalServerErrorException(ce.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Member entity) {
        try {
            ejb.updateMember(entity);
        } catch (UpdateException ue) {
            LOGGER.severe(ue.getMessage());
            throw new InternalServerErrorException(ue.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        try {
            ejb.removeMember(id);
        } catch (DeleteException de) {
            LOGGER.severe(de.getMessage());
            throw new InternalServerErrorException(de.getMessage());
        }
    }

    @GET
    @Path("{plan_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List <Member> findMembersByPlan(@PathParam("plan_id") Integer planId) {
        try {
            return ejb.findMembersByPlan(planId);
        } catch (FindMemberException fe) {
            LOGGER.severe(fe.getMessage());
            throw new InternalServerErrorException(fe.getMessage());
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Member> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Member> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
