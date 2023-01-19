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

import java.text.SimpleDateFormat;
import java.util.List;

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

import java.util.logging.Logger;

/**
 * @author dani
 */
@Stateless
@Path("entities.member")
public class MemberFacadeREST {

    @EJB
    private IMemberManager ejb;

    private Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getTypeName());

    @POST
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
    @Path("findByPlan/{plan}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List <Member> findMembersByPlan(@PathParam("plan") Integer plan) {
        try {
            return ejb.findMembersByPlan(plan);
        } catch (FindMemberException fe) {
            LOGGER.severe(fe.getMessage());
            throw new InternalServerErrorException(fe.getMessage());
        }
    }

    @GET
    @Path("findByStartingDate/{memberStartingDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Member> findMembersByStartingDate(@PathParam("memberStartingDate") String startingDate) {
        try {
            return ejb.findMembersByStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse(startingDate));
        } catch (FindMemberException fme) {
           LOGGER.severe(fme.getMessage());
           throw new InternalServerErrorException(fme.getMessage());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    @GET
    @Path("findByEndingDate/{memberEndingDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Member> findMembersByEndingDate(@PathParam("memberEndingDate") String endingDate) {
        try {
            return ejb.findMembersByEndingDate(new SimpleDateFormat("yyyy-MM-dd").parse(endingDate));
        } catch (FindMemberException fme) {
           LOGGER.severe(fme.getMessage());
           throw new InternalServerErrorException(fme.getMessage());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    @GET
    @Path("findMemberByLogin/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Member findMemberByLogin(@PathParam("login") String login) {
        try {
            return ejb.findMemberByLogin(login);
        } catch (FindMemberException fme) {
           LOGGER.severe(fme.getMessage());
           throw new InternalServerErrorException(fme.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List <Member> getEveryUser() {
        try {
            return ejb.getEveryUser();
        } catch (FindMemberException fme) {
            LOGGER.severe(fme.getMessage());
            throw new InternalServerErrorException(fme.getMessage());
        }
    }
}
