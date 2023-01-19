package service;

import java.util.List;

import entities.Member;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindMemberException;
import exceptions.UpdateException;

public interface IMemberManager {
    
    public void createMember(Member member) throws CreateException;

    public void updateMember(Member member) throws UpdateException;

    public void removeMember(String login) throws DeleteException;

    public Member findMemberByLogin(String login) throws FindMemberException;

    public List <Member> findMembersByPlan(Integer plan) throws FindMemberException;

    public List <Member> getEveryMember() throws FindMemberException;

    public List <Member> getEveryUser() throws FindMemberException;
}