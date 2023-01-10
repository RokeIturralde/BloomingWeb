package service.user;

import java.util.List;


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

    public void removeUser(String userLogin) throws DeleteException;

    public User findUserByLogin(String login) throws FindUserException;

    public User findUserByEmail(String email) throws FindUserException;

    public List <User> findUsersByName(String name) throws FindUserException;

    public List <User> findUsersByStatus(Status status) throws FindUserException;

    public List <User> findUsersByPrivilege(Privilege privilege) throws FindUserException;

}
