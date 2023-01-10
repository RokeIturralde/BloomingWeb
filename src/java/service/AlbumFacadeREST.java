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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nerea
 */
@Stateless
@Path("album")
public class AlbumFacadeREST {

    @EJB
    private AlbumManagerLocal ejb;
    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger("package service");

    /**
     * POST method to create a new Album: uses createAlbum business logic
     * method.
     *
     * @param album The Album entity object containing new Album data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createAlbum(Album album) {
        try {
            LOGGER.log(Level.INFO, "Creating a new Album id= {0}", album.getId());
            ejb.createAlbum(album);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to modify an Album: uses updateAlbum business logic method.
     *
     * @param album The Album entity object containing new Album data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateAlbum(Album album) {
        try {
            LOGGER.log(Level.INFO, "Updating the album {0} id= ", album.getId());
            ejb.updateAlbum(album);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * DELETE method to remove an Album: uses removeAlbum business logic method.
     *
     * @param id The id for the album to be deleted.
     */
    @DELETE
    @Path("{id}")
    public void removeAlbum(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Deleting the album {0} id= ", id);
            Album album;
            album = ejb.findAlbumByID(id);
            ejb.removeAlbum(album);
        } catch (ReadException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to get an Album data by id: it uses business method
     * findAlbumByID.
     *
     * @param id The id for the album to be found.
     * @return An Album that contains the album the method found.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Album findAlbumByID(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Finding the album {0} id= ", id);
            return ejb.findAlbumByID(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to get all user´s Albums data: it uses business method
     * findMyAllAlbums.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAllAlbums(User user) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums");
            return ejb.findMyAllAlbums(user);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to get all user´s Albums data by name: it uses business method
     * findMyAlbumsByName.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAlbumsByName(User user, String name) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums by name");
            return ejb.findMyAlbumsByName(user, name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to get all user´s Albums data by date: it uses business method
     * findMyAlbumsByDate.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAlbumsByDate(User user, Date date) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums by date");
            return ejb.findMyAlbumsByDate(user, date);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to get all user´s shared Albums data: it uses business method
     * findMyAllSharedAlbums.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAllSharedAlbums(User user) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums");
            return ejb.findMyAllSharedAlbums(user);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * GET method to get all user´s shared Albums data by name: it uses business method
     * findMySharedAlbumsByName.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByName(User user, String name) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by name");
            return ejb.findMySharedAlbumsByName(user, name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * GET method to get all user´s shared Albums data by date: it uses business method
     * findMySharedAlbumsByDate.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByDate(User user, Date date) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by date");
            return ejb.findMySharedAlbumsByDate(user, date);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
     /**
     * GET method to get all user´s shared Albums data by album creator: it uses business method
     * findMySharedAlbumsByCreator.
     *
     * @param user The User Entity Object containing User data from the user who
     * is logged to de app.
     * @param creatorLogin  A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByCreator(User user, String creatorLogin) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by album creator");
            return ejb.findMySharedAlbumsByCreator(user, creatorLogin);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }   
}
