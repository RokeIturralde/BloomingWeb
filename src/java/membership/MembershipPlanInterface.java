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

/**
 *
 * @author minyb
 */
public interface MembershipPlanInterface {

    public void createPlan(MembershipPlan plan) throws CreateException;

    public void updatePlan(MembershipPlan plan) throws UpdateException;

    public void removePlan(int id) throws DeleteException;

    public List<MembershipPlan> findPlanByName(String name) throws FindPlanException;

    public List<MembershipPlan> findAllPlans() throws FindPlanException;

    public List<MembershipPlan> findPlanByDuration(Integer duration) throws FindPlanException;

    public List<MembershipPlan> findPlanByPrice(float price) throws FindPlanException;

    public MembershipPlan findPlanById(int id) throws FindPlanException;

    public List<User> listMembersByPlan(int id) throws FindUserException;
}
