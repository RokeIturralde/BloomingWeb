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
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    @PersistenceContext(unitName = "DataModelExamplePU")
    private EntityManager em;

    /**
     * This method creates a new Album in the data base.
     *
     * @param album The Album entity object containing new Album data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    @Override
    public void createAlbum(Album album) throws CreateException {
        try {
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
    public void shareAlbum(Album album, String login) throws SharingException {
        
    }

    @Override
    public ArrayList<Album> findMyAllAlbums(Album album, User user) throws ReadException {
        return null;
    }
    
    /**
     * This method finds all the albums that the name contains the words the
     * user introduce and the user is the creator.
     *
     * @param album An Album entity object containing Album data.
     * @param user A User entity object containing User data.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException
     */
    @Override
    public ArrayList<Album> findAlbumsByName(Album album, User user, String name) throws ReadException {
       return null;
    }

    @Override
    public ArrayList<Album> findAlbumsByDate(Album album, User user, Date date) throws ReadException {
        return null;
    }

    @Override
    public ArrayList<Album> findMyAllSharedAlbums(Album album, User user) throws ReadException {
        return null;
    }

    @Override
    public ArrayList<Album> findSharedAlbumsByName(Album album, User user, String name) {
        return null;
    }

    @Override
    public ArrayList<Album> findSharedAlbumsByDate(Album album, User user, Date date) {
        return null;
    }

    @Override
    public ArrayList<Album> findSharedAlbumsByCreator(Album album, User user) throws ReadException {
        return null;
    } 
    
    /* public ArrayList<Album> myAlbums() {
        ArrayList<Album> myAlbums = null;
        try {

            myAlbums = new ArrayList<>(em.createNamedQuery("myAlbums").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  myAlbums() {0}", e.getMessage());
        }
        return myAlbums;
    }

    public ArrayList<Album> sharedAlbums() {
        ArrayList<Album> sharedAlbums = null;
        try {
            sharedAlbums = new ArrayList<>(em.createNamedQuery("sharedAlbums").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AlbumEJB ->  sharedAlbums() {0}", e.getMessage());
        }
        return sharedAlbums;
    }*/
}
