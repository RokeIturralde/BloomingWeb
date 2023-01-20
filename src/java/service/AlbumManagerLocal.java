/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Album;
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
     * The method finds an album which id is equals the id the User introduce
     * for a new album.
     *
     * @param id An Integer that contains the id the user introduce.
     * @return The Album entity object to be found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */
    public Album findAlbumByID(Integer id) throws ReadException;

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
    public ArrayList<Album> findMyAllAlbums(String userLogin) throws ReadException;

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
    public ArrayList<Album> findMyAlbumsByName(String userLogin, String name) throws ReadException;

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
    public ArrayList<Album> findMyAlbumsByDate(String userLogin, Date date) throws ReadException;

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
    public ArrayList<Album> findMyAllSharedAlbums(String userLogin) throws ReadException;

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
    public ArrayList<Album> findMySharedAlbumsByName(String userLogin, String name) throws ReadException;

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
    public ArrayList<Album> findMySharedAlbumsByDate(String userLogin, Date date) throws ReadException;

    /**
     * This method finds all the shared albums that the login contains the words
     * the user introduce.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @@param creatorLogin An String with the login of the creator of the album.
     * @return An ArrayList of Albums that contains the albums that the method
     * found.
     * @throws ReadException Thrown when any error or exception occurs during
     * reading.
     */

    public ArrayList<Album> findMySharedAlbumsByCreator(String userLogin, String creatorLogin) throws ReadException;
}
