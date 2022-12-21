package service.user;

import java.util.Set;

import org.apache.tools.ant.taskdefs.Delete;

import entities.Privilege;
import entities.Status;
import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindUserException;
import exceptions.UpdateException;

public interface IUserManager {


    public void createUser(User user) throws CreateException;

    public void updateUser(User user) throws UpdateException;

    public void removeUser(User user) throws DeleteException;

    public User findUserByLogin(String login) throws FindUserException;

    public User findUserByEmail(String email) throws FindUserException;

    public Set <User> findUsersByName(String name) throws FindUserException;

    public Set <User> findUsersByStatus(Status status) throws FindUserException;

    public Set <User> findUsersByPrivilege(Privilege privilege) throws FindUserException;

}
