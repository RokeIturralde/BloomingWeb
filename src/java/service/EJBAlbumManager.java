/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import org.hibernate.jpa.internal.EntityManagerImpl;

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
    @PersistenceContext(unitName = "DataModelExamplePU")
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
        try {
            User creator = album.getCreator();
            em.createNamedQuery("findAlbumByName").setParameter("creator", creator).setParameter("name", album.getName()).getSingleResult();

            throw new CreateException("Ya esta el nombre en uso");

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
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * The method shares an existing album from the data store.
     *
     * @param album The Album entity object to be shared.
     * @param login The user's login you want to share the album to
     * @throws SharingException Thrown when any error or exception occurs during
     * sharing.
     */
    @Override
    public void shareAnAlbum(Album album, String login) throws SharingException {
        try {
            if (!em.contains(album)) {
                em.merge(album);
            }
            em.flush();
        } catch (Exception e) {
            throw new SharingException(e.getMessage());
        }
    }

    /**
     * The method finds an album which name is equals the name the User
     * introduce for a new album.
     *
     * @param name
     * @return The Album entity object to be found.
     * @throws ReadException
     */
    @Override
    public Album findAlbumByName(String name) throws ReadException {
        Album album = null;
        try {
            album = (Album) em.createNamedQuery("findAlbumByName").getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findAlbumByName(String name) {0}", e.getMessage());
        }
        return album;
    }

    /**
     * The method finds all the albums where the User is the creator
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAllAlbums(User user) throws ReadException {
        ArrayList<Album> myAlbums = null;
        try {

            myAlbums = new ArrayList<>(em.createNamedQuery("findMyAllAlbums").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAllAlbums(User user) {0}", e.getMessage());
        }
        return myAlbums;
    }

    /**
     * This method finds all the albums created by user that the name contains
     * the words the user introduced.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAlbumsByName(User user, String name) throws ReadException {
        ArrayList<Album> myAlbums = null;
        try {

            myAlbums = new ArrayList<>(em.createNamedQuery("findMyAlbumsByName").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAlbumsByName(User user, String name) {0}", e.getMessage());
        }
        return myAlbums;
    }

    /**
     * This method finds all the albums created by user that the date of
     * creation is equals the date the user introduced.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAlbumsByDate(User user, Date date) throws ReadException {
        ArrayList<Album> myAlbums = null;
        try {

            myAlbums = new ArrayList<>(em.createNamedQuery("findMyAlbumsByDate").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAlbumsByDate(User user, Date date) {0}", e.getMessage());
        }
        return myAlbums;
    }

    /**
     * The method finds all the albums that where shared to user
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMyAllSharedAlbums(User user) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        try {
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMyAllSharedAlbums").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMyAllSharedAlbums(User user) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    /**
     * This method finds all the shared albums that the name contains the words
     * the user introduce.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMySharedAlbumsByName(User user, String name) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        try {
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMySharedAlbumsByName").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMySharedAlbumsByName(User user, String name) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    /**
     * This method finds all the shared albums that the date of creation is
     * equals the date the user introduced.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMySharedAlbumsByDate(User user, Date date) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        try {
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMySharedAlbumsByDate").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMySharedAlbumsByDate(User user, Date date) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    /**
     * This method finds all the shared albums that the login contains the words
     * the user introduce.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param login A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    @Override
    public ArrayList<Album> findMySharedAlbumsByCreator(User user, String login) throws ReadException {
        ArrayList<Album> sharedAlbums = null;
        try {
            sharedAlbums = new ArrayList<>(em.createNamedQuery("findMySharedAlbumsByCreator").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  findMySharedAlbumsByCreator(User user, String login) {0}", e.getMessage());
        }
        return sharedAlbums;
    }

    @Override
    public Album findAlbumByID(Integer id) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
