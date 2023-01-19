/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.NotTheCreatorException;
import entities.Album;
import entities.User;
import exceptions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the AlbumManagerLocal interface for
 * AlbumManager web service application.
 *
 * @author nerea
 */
@Stateless
public class EJBAlbumManager implements AlbumManagerLocal {

    private static final Logger LOGGER = Logger.getLogger("package service");
    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    /**
     * This method creates a new Album in the data base but first search in the
     * data base if the name of the album exist in other album that the user
     * created.
     *
     * @param album The Album entity object containing new Album data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    @Override
    public void createAlbum(Album album) throws CreateException {
        Album bdAlbum;
        try {
            User creator = album.getCreator();
            em.createNamedQuery("findAlbumByName").setParameter("creator", creator).setParameter("name", album.getName()).getSingleResult();

            throw new CreateException("You already have an album with that name");

        } catch (NoResultException n) {
            em.persist(album);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method updates a movement data in the data store.
     *
     * @param album The Album entity object containing modified Album data.
     * @throws UpdateException Thrown when any error or exception occurs during
     * update.
     */
    @Override
    public void updateAlbum(Album album) throws UpdateException {
        try {
            if (!em.contains(album)) {
                em.merge(album);
            }
            em.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  updateAlbum(Album album) {0}", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * This method removes an account from the data store.
     *
     * @param album The Album entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during
     * deletion.
     */
    @Override
    public void removeAlbum(Album album) throws DeleteException {
        try {
            em.remove(em.merge(album));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  removeAlbum(Album album) {0}", e.getMessage());
            throw new DeleteException(e.getMessage());

        }
    }

    /**
     * The method finds an album which id is equals the id the User introduce
     * for a new album.
     *
     * @param id An Integer that contains the id the user introduce.
     * @return The Album entity object to be found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public Album findAlbumByID(Integer id) throws ReadException {
        Album album = null;
        try {
            album = em.find(Album.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findAlbumByID(Integer id) {0}", e.getMessage());
        }
        return album;
    }

    /**
     * The method finds all the albums where the User is the creator
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAllAlbums(String userLogin) throws ReadException {
        ArrayList<Album> myAlbums = null;
        User user;
        try {
            user = em.find(User.class, userLogin);
            myAlbums = new ArrayList<>(em.createNamedQuery("findMyAllAlbums").setParameter("creator", user).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAllAlbums(User user) {0}", e.getMessage());
        }
        return myAlbums;
    }

    /**
     * This method finds all the albums created by user that the name contains
     * the words the user introduced.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAlbumsByName(String userLogin, String name) throws ReadException {
        ArrayList<Album> myAlbums = null;
        User user;
        try {
            user = em.find(User.class, userLogin);
            myAlbums = new ArrayList<>(em.createNamedQuery("findMyAlbumsByName").setParameter("creator", user).setParameter("name", "%" + name + "%").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAlbumsByName(User user, String name) {0}", e.getMessage());
        }
        return myAlbums;
    }

    /**
     * This method finds all the albums created by user that the date of
     * creation is equals the date the user introduced.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAlbumsByDate(String userLogin, Date date) throws ReadException {
        ArrayList<Album> myAlbums = null;
        User user;
        try {
            user = em.find(User.class, userLogin);
            myAlbums = new ArrayList<>(em.createNamedQuery("findMyAlbumsByDate").setParameter("creator", user).setParameter("date", date).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAlbumsByDate(User user, Date date) {0}", e.getMessage());
        }
        return myAlbums;
    }

    /**
     * The method finds all the albums that where shared to user
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAllSharedAlbums(String userLogin) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        User user;
        try {
            user = em.find(User.class, userLogin);
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMyAllSharedAlbums").setParameter("user", user).setParameter("userLogin", user.getLogin()).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAllSharedAlbums(User user) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    /**
     * This method finds all the shared albums that the name contains the words
     * the user introduce.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMySharedAlbumsByName(String userLogin, String name) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        User user;
        try {
            user = em.find(User.class, userLogin);
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMySharedAlbumsByName").setParameter("user", user).setParameter("userLogin", user.getLogin()).setParameter("name", "%" + name + "%").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMySharedAlbumsByName(User user, String name) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    /**
     * This method finds all the shared albums that the date of creation is
     * equals the date the user introduced.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMySharedAlbumsByDate(String userLogin, Date date) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        User user;
        try {
            user = em.find(User.class, userLogin);
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMySharedAlbumsByDate").setParameter("user", user).setParameter("userLogin", user.getLogin()).setParameter("date", date).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMySharedAlbumsByDate(User user, Date date) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    /**
     * This method finds all the shared albums that the login contains the words
     * the user introduce.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param creatorLogin An String with the login of the creator of the album.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMySharedAlbumsByCreator(String userLogin, String creatorLogin) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        User user;
        try {
            user = em.find(User.class, creatorLogin);
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMySharedAlbumsByCreator").setParameter("creator", user).setParameter("userLogin", userLogin).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMySharedAlbumsByCreator(User user, String login) {0}", e.getMessage());
        }
        return sharedAlbums;
    }
}
