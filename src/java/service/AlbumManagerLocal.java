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
import javax.ejb.Local;

/**
 * Local interface for AlbumManager EJB.
 *
 * @author nerea
 */
@Local
public interface AlbumManagerLocal {

    /**
     * This method creates a new Album in the data base.
     *
     * @param album The Album entity object containing new Album data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    public void createAlbum(Album album) throws CreateException;

    /**
     * This method updates a movement data in the data store.
     *
     * @param album The Album entity object containing modified Album data.
     * @throws UpdateException Thrown when any error or exception occurs during
     * update.
     */
    public void updateAlbum(Album album) throws UpdateException;

    /**
     * This method removes an account from the data store.
     *
     * @param album The Album entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during
     * deletion.
     */
    public void removeAlbum(Album album) throws DeleteException;

    /**
     * The method shares an existing album from the data store.
     *
     * @param album The Album entity object to be shared.
     * @param login The user's login you want to share the album to
     * @throws SharingException Thrown when any error or exception occurs during
     * sharing.
     */
    public void shareAlbum(Album album, String login) throws SharingException;

    /**
     *
     * @param album An Album entity object containing Album data.
     * @param user
     * @return
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findMyAllAlbums(Album album, User user) throws ReadException;

    /**
     * This method finds all the albums that the name contains the words the
     * user introduce and the user is the creator.
     *
     * @param album An Album entity object containing Album data.
     * @param user A User entity object containing User data.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findAlbumsByName(Album album, User user, String name) throws ReadException;

    /**
     * This method finds all the albums that the date of creation is the date
     * that the user introduce
     *
     * @param album An Album entity object containing Album data
     * @param user
     * @param date
     * @return
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findAlbumsByDate(Album album, User user, Date date) throws ReadException;

    /**
     * This method finds all the albums that the name contains the words the
     * user introduce and someone shared to the user
     *
     * @param album An Album entity object containing Album data
     * @param user
     * @return
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findMyAllSharedAlbums(Album album, User user) throws ReadException;

    /**
     *
     * @param album An Album entity object containing Album data
     * @param user
     * @param name
     * @return
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findSharedAlbumsByName(Album album, User user, String name) throws ReadException;

    /**
     *
     * @param album An Album entity object containing Album data
     * @param user
     * @param date
     * @return
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findSharedAlbumsByDate(Album album, User user, Date date) throws ReadException;

    /**
     *
     * @param album An Album entity object containing Album data
     * @param user
     * @return
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public ArrayList<Album> findSharedAlbumsByCreator(Album album, User user) throws ReadException;

}
