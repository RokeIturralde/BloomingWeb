package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Member;
import entities.MembershipPlan;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindMemberException;
import exceptions.UpdateException;
import javax.ejb.Stateless;

/**
 * @author dani
 */
@Stateless
public class EJBMemberManager implements IMemberManager {
      /**
       * the entity manager is used to manage all the
       */

      @PersistenceContext(unitName = "BloomingWebPU")
      private EntityManager em;

      @Override
      public void createMember(Member member) throws CreateException {
            try {
                  em.persist(member);
            } catch (Exception e) {
                 throw new CreateException(e.getMessage());
            }
            
      }

      @Override
      public void updateMember(Member member) throws UpdateException {
            try {
                  if (!em.contains(member))
                        em.merge(member);
            } catch (Exception e) {
                 throw new UpdateException(e.getMessage());
            }            
      }

      @Override
      public Member findMemberByLogin(String login) throws FindMemberException {
            try {
                  return em.find(Member.class, login);
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      }

      @Override
      public List<Member> findMembersByPlan(Integer planID) throws FindMemberException {
            try {
                MembershipPlan plan = em.find(MembershipPlan.class, planID);
                  return em.createNamedQuery("findMembersByPlan")
                        .setParameter("plan", plan)
                              .getResultList();
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      } 

      @Override
      public List <Member> getEveryUser() throws FindMemberException {
            try {
                  return em.createNamedQuery("getEveryUser").getResultList();
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      }

      @Override
      public List <Member> getEveryMember() throws FindMemberException {
            try {
                  return em.createNamedQuery("getEveryMember").getResultList();
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      }

    @Override
    public void removeMember(String login) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
