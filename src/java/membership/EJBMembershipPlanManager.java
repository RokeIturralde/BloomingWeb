/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import entities.MembershipPlan;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindPlanException;
import exceptions.FindUserException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author minyb
 */
@Stateless
public class EJBMembershipPlanManager implements MembershipPlanInterface {

    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    @Override
    public void createPlan(MembershipPlan plan) throws CreateException {
        try {
            em.persist(plan);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }

    }

    @Override
    public void updatePlan(MembershipPlan plan) throws UpdateException {
        try {
            if (!em.contains(plan)) {
                em.merge(plan);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }

    }

    @Override
    public void removePlan(int id) throws DeleteException {
        try {
            em.remove(em.merge(em.find(MembershipPlan.class, id)));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public List<MembershipPlan> findPlanByName(String name) throws FindPlanException {
        List<MembershipPlan> plans;
        try {
            plans = em.createNamedQuery("findPlanByName").setParameter("planName", name).getResultList();
            return plans;
        } catch (Exception e) {
            throw new FindPlanException(e.getMessage());
        }
    }

    @Override
    public List<MembershipPlan> findAllPlans() throws FindPlanException {
        List<MembershipPlan> plans;
        try {
            plans = em.createNamedQuery("findAllPlans").getResultList();
            return plans;
        } catch (Exception e) {
            throw new FindPlanException(e.getMessage());
        }

    }

    @Override
    public List<MembershipPlan> findPlanByDuration(Integer duration) throws FindPlanException {
        List<MembershipPlan> plans;
        try {
            plans = em.createNamedQuery("findPlanByDuration").setParameter("planDuration", duration).getResultList();
            return plans;
        } catch (Exception e) {
            throw new FindPlanException(e.getMessage());
        }
    }

    @Override
    public List<MembershipPlan> findPlanByPrice(float price) throws FindPlanException {
        List<MembershipPlan> plans;
        try {
            plans = em.createNamedQuery("findPlanByPrice").setParameter("planPrice", price).getResultList();
            return plans;
        } catch (Exception e) {
            throw new FindPlanException(e.getMessage());
        }

    }

    @Override
    public MembershipPlan findPlanById(int id) throws FindPlanException {
        MembershipPlan plan;
        try {
            plan = em.find(MembershipPlan.class, id);
            return plan;
        } catch (Exception e) {
            throw new FindPlanException(e.getMessage());
        }

    }

}
