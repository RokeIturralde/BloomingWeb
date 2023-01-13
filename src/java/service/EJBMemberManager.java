package service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Member;
import entities.MembershipPlan;
import entities.Privilege;
import entities.Status;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindMemberException;
import exceptions.FindUserException;
import exceptions.UpdateException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author dani
 */
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
      public void removeMember(String login) throws DeleteException {
            try {
                  if (em.contains(findMemberByLogin(login)))
                        em.remove(em.find(Member.class, login));
            } catch (Exception e) {
                  throw new DeleteException(e.getMessage());
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
      public List<Member> findMembersByPlan(MembershipPlan membershipPlan) throws FindMemberException {
            try {
                  return em.createNamedQuery("findMembersByPlan")
                        .setParameter("plan", membershipPlan)
                              .getResultList();
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      }

      @Override
      public List<Member> findMembersByEndingDate(Date endingDate) throws FindMemberException {
            try {
                  return em.createNamedQuery("findMembersByEndingDate")
                        .setParameter("memberEndingDate", endingDate)
                              .getResultList();
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      }

      @Override
      public List<Member> findMembersByStartingDate(Date startingDate) throws FindMemberException {
            try {
                  return em.createNamedQuery("findMembersByStartingDate")
                        .setParameter("memberStartingDate", startingDate)
                              .getResultList();
            } catch (Exception e) {
                  throw new FindMemberException(e.getMessage());
            }
      }

     

}
