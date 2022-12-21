/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Album;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the AlbumManagerLocal interface for
 * AlbumManager web service application.
 * @author nerea
 */
@Stateless
public class EJBAlbumManager implements AlbumManagerLocal{
    private static final Logger LOGGER = Logger.getLogger("package service");
    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "DataModelExamplePU")
    private EntityManager em;
    

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
