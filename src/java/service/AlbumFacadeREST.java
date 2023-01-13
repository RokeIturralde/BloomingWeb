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
@Path("entities.album")
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
        Album album;
        try {
            LOGGER.log(Level.INFO, "Deleting the album {0} id= ", id);
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
    @Path("/findAlbumByID/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Album findAlbumByID(@PathParam("id") Integer id) {
        Album album = null;
        try {
            LOGGER.log(Level.INFO, "Finding the album id= {0} ", id);
            album = ejb.findAlbumByID(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return album;
    }

    /**
     * GET method to get all user´s Albums data: it uses business method
     * findMyAllAlbums.
     *
     * @param userLogin An string with the login from the user
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Path("findMyAllAlbums/{userLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAllAlbums(@PathParam("userLogin") String userLogin) {
        ArrayList<Album> albums;
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums");
            albums = ejb.findMyAllAlbums(userLogin);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return albums;
    }

    /**
     * GET method to get all user´s Albums data by name: it uses business method
     * findMyAlbumsByName.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    /*@GET
    @Path("/GET/Album{userLogin}{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAlbumsByName(@PathParam("userLogin") String userLogin, @PathParam("name") String name) {
        Album album;
    try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums by name");
            return ejb.findMyAlbumsByName(userLogin, name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    return album;
    }*/
    /**
     * GET method to get all user´s Albums data by date: it uses business method
     * findMyAlbumsByDate.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    /*@GET
    @Path("/GET/Album{user}{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAlbumsByDate(@PathParam("userLogin") String userLogin, @PathParam("date") Date date) {
    ArrayList<Album> albums;    
    try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums by date");
            albums = ejb.findMyAlbumsByDate(userLogin, date);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    return albums;
    }*/
    /**
     * GET method to get all user´s shared Albums data: it uses business method
     * findMyAllSharedAlbums.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    /*@GET
    @Path("/GET/Album{user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAllSharedAlbums(@PathParam("userLogin") String userLogin) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums");
            return ejb.findMyAllSharedAlbums(userLogin);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }*/
    /**
     * GET method to get all user´s shared Albums data by name: it uses business
     * method findMySharedAlbumsByName.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    /*@GET
    @Path("/GET/Album{user}{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByName(@PathParam("userLogin") String userLogin, @PathParam("name") String name) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by name");
            return ejb.findMySharedAlbumsByName(userLogin, name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }*/
    /**
     * GET method to get all user´s shared Albums data by date: it uses business
     * method findMySharedAlbumsByDate.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param date A Date that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    /*@GET
    @Path("/GET/Album{userLogin}{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByDate(@PathParam("userLogin") String userLogin, @PathParam("date") Date date) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by date");
            return ejb.findMySharedAlbumsByDate(userLogin, date);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }*/
    /**
     * GET method to get all user´s shared Albums data by album creator: it uses
     * business method findMySharedAlbumsByCreator.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param creatorLogin A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    /*@GET
    @Path("/GET/Album{user}{creatorLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByCreator(@PathParam("userLogin") String userLogin, @PathParam("creatorLogin") String creatorLogin) {
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by album creator");
            return ejb.findMySharedAlbumsByCreator(userLogin, creatorLogin);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }*/
    /**
     * DELETE method to remove a shared Album from user's shareds: uses
     * removeAlbum business logic method.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param album The Entity Album to be removed.
     */
    /*@DELETE
    @Path("removeShared{userLogin}{album}")
    public void removeFromSharedsAnAlbum(@PathParam("userLogin") String userLogin, @PathParam("album") Album album) {
        try {
            LOGGER.log(Level.INFO, "Deleting from shareds the album {0} id= ", album.getId());
            ejb.removeFromSharedsAnAlbum(userLogin, album);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }*/
}
