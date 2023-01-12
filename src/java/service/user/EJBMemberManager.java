package service.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EJBMemberManager {
     /**
     * the entity manager is used to manage all the 
     */

     @PersistenceContext(unitName = "BloomingWebPU")
     private EntityManager em;
 
     /**
      * writes a new user to the database
      * @param user
      * @throws CreateException
      */
}
