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
    public void shareAnAlbum(Album album, String login) throws SharingException;

    /**
     * The method finds an album which id is equals the id the User introduce
     * for a new album.
     *
     * @param id An Integer that contains the id the user introduce
     * @return The Album entity object to be found.
     * @throws ReadException
     */
    public Album findAlbumByID(Integer id) throws ReadException;

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
    public ArrayList<Album> findMyAllAlbums(User user) throws ReadException;

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
    public ArrayList<Album> findMyAlbumsByName(User user, String name) throws ReadException;

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
    public ArrayList<Album> findMyAlbumsByDate(User user, Date date) throws ReadException;

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
    public ArrayList<Album> findMyAllSharedAlbums(User user) throws ReadException;

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
    public ArrayList<Album> findMySharedAlbumsByName(User user, String name) throws ReadException;

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
    public ArrayList<Album> findMySharedAlbumsByDate(User user, Date date) throws ReadException;

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
    public ArrayList<Album> findMySharedAlbumsByCreator(User user, String login) throws ReadException;

    /**
     * This method delete an album that someone shared you, it only will be
     * deleted from your shared table, you can´t delete it literally
     *
     * @param user the user that want to delete the album
     * @param album the shared album you want to delete
     */
    public void deleteFromSharedsAnAlbum(User user, Album album) throws DeleteException;
}
