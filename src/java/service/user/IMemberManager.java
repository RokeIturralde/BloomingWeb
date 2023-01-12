package service.user;

import java.sql.Date;
import java.util.List;

import entities.Member;
import entities.MembershipPlan;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindMemberException;
import exceptions.UpdateException;

public interface IMemberManager {
    
    public void createMember(Member member) throws CreateException;

    public void updateMember(Member member) throws UpdateException;

    public void removeUser(Member member) throws DeleteException;

    public List <Member> findMembersByPlan(MembershipPlan membershipPlan) throws FindMemberException;

    public List <Member> findMembersByEndingDate(Date endingDate) throws FindMemberException;

    public List <Member> findMembersByStartingDate(Date startingDate) throws FindMemberException;
}
