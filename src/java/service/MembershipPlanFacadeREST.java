/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.MembershipPlan;
import entities.User;
import java.util.List;
import javax.ejb.EJB;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindPlanException;
import exceptions.FindUserException;
import exceptions.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import membership.EJBMembershipPlanManager;
import membership.MembershipPlanInterface;

/**
 *
 * @author 2dam
 */
@Path("entities.membershipplan")
public class MembershipPlanFacadeREST {

    @EJB
    private MembershipPlanInterface ejbM;

    /**
     * Create plan
     *
     * @param entity
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(MembershipPlan entity) {
        try {
            ejbM.createPlan(entity);
        } catch (CreateException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Update plan
     *
     * @param id
     * @param entity
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, MembershipPlan entity) {
        try {
            ejbM.updatePlan(entity);
        } catch (UpdateException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete plan
     *
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejbM.removePlan(id);
        } catch (DeleteException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Find plan by id
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MembershipPlan find(@PathParam("id") Integer id) {
        MembershipPlan plan = null;
        try {
            plan = ejbM.findPlanById(id);
        } catch (FindPlanException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plan;
    }

    /**
     * Find all plans
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MembershipPlan> findAll() {
        List<MembershipPlan> plans = null;
        try {
            plans = ejbM.findAllPlans();
        } catch (FindPlanException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    /**
     * Find plans by name
     *
     * @param name
     * @return
     */
    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MembershipPlan> findContentByName(@PathParam("name") String name) {
        List<MembershipPlan> plans = null;
        try {
            plans = ejbM.findPlanByName(name);
        } catch (FindPlanException ex) {
            Logger.getLogger(ContentFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    /**
     * Find plans by duration
     *
     * @param duration
     * @return
     */
    @GET
    @Path("duration/{duration}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MembershipPlan> findPlanByDuration(@PathParam("duration") Integer duration) {
        List<MembershipPlan> plans = null;
        try {
            plans = ejbM.findPlanByDuration(duration);
        } catch (FindPlanException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    /**
     * Find plans by price
     *
     * @param price
     * @return
     */
    @GET
    @Path("price/{price}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MembershipPlan> findPlanByPrice(@PathParam("price") Float price) {
        List<MembershipPlan> plans = null;
        try {
            plans = ejbM.findPlanByPrice(price);
        } catch (FindPlanException ex) {
            Logger.getLogger(MembershipPlanFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    @GET
    @Path("findByPlan/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> listMembersByPlan(@PathParam("id") int id) {
        List<User> users = null;
        try {
            users = ejbM.listMembersByPlan(id);
        } catch (FindUserException ex) {
            Logger.getLogger(ContentFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

}
