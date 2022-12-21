/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Album;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 * Local interface for AlbumManager EJB.
 * @author nerea
 */
@Local
public interface AlbumManagerLocal {
    /**
     * This method creates a new Album in the data base.
     * @param album The Album entity object containing new Album data.
     * @throws
     */
    public void createAlbum(Album album);
     /**
    * 
    * @param album
    * @param user
    * @return 
    * @throws
    */
    public ArrayList<Album> findMyAllAlbums(Album album, User user);
    
    /**
     * This method finds all the albums that the name contains
     * the words the user introduce and the user is the creator
     * @param album An Album entity object containing Album data
     * @param user
     * @param name
     * @return 
     * @throws
     */
    public ArrayList<Album> findAlbumsByName(Album album, User user, String name);
    
    /**
     * This method finds all the albums that the date 
     * of creation is the date that the user introduce 
     * @param album An Album entity object containing Album data
     * @param user
     * @param date
     * @return 
     * @throws
     */
    public ArrayList<Album> findAlbumByDate(Album album, User user, Date date);
    
    /**
     * This method finds all the albums that the name contains
     * the words the user introduce and someone shared to the user
     * @param album
     * @param user
     * @return 
     * @throws
     */
    public ArrayList<Album> findMyAllSharedAlbums(Album album, User user);
    
    /**
     * 
     * @param album
     * @param user
     * @param name
     * @return 
     */
    public ArrayList<Album> findSharedAlbumName(Album album, User user, String name);
    
    public ArrayList<Album> findSharedAlbumDate(Album album, User user, Date date);
    
}
