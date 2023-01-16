/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Album;
import exceptions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private AlbumManagerLocal ejbA;

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
            ejbA.createAlbum(album);
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
            ejbA.updateAlbum(album);
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
            album = ejbA.findAlbumByID(id);
            ejbA.removeAlbum(album);
        } catch (ReadException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to share an existing album from the data store: uses
     * updateAlbum business logic method..
     *
     * @param albumId The id of the album to be shared.
     * @param userLogin A string with the login of the user to share with.
     */
    @PUT
    @Path("shareAnAlbum/{albumId}/{userLogin}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void shareAnAlbum(@PathParam("albumId") Integer albumId, @PathParam("userLogin") String userLogin) {
        try {
            LOGGER.log(Level.INFO, "Updating the album {0} id= ", albumId);
            Album album = ejbA.findAlbumByID(albumId);
            ejbA.shareAnAlbum(album, userLogin);
        } catch (ReadException | SharingException ex) {
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
    public Album findAlbumByID(@PathParam("id") Integer id
    ) {
        Album album;
        try {
            LOGGER.log(Level.INFO, "Finding the album id= {0} ", id);
            album = ejbA.findAlbumByID(id);
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
    public ArrayList<Album> findMyAllAlbums(@PathParam("userLogin") String userLogin
    ) {
        ArrayList<Album> albums;
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums");
            albums = ejbA.findMyAllAlbums(userLogin);
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
    @GET
    @Path("findMyAlbumsByName/{userLogin}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAlbumsByName(@PathParam("userLogin") String userLogin,
            @PathParam("name") String name
    ) {
        ArrayList<Album> albums;
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums by name");
            albums = ejbA.findMyAlbumsByName(userLogin, name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return albums;
    }

    /**
     * GET method to get all user´s Albums data by date: it uses business method
     * findMyAlbumsByDate.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param stringDate A String that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Path("findMyAlbumsByDate/{userLogin}/{stringDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAlbumsByDate(@PathParam("userLogin") String userLogin,
            @PathParam("stringDate") String stringDate
    ) {
        ArrayList<Album> albums = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's Albums by date");
            Date date = format.parse(stringDate);
            albums = ejbA.findMyAlbumsByDate(userLogin, date);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(AlbumFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albums;
    }

    /**
     * GET method to get all user´s shared Albums data: it uses business method
     * findMyAllSharedAlbums.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Path("findMyAllSharedAlbums/{userLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMyAllSharedAlbums(@PathParam("userLogin") String userLogin
    ) {
        ArrayList<Album> albums;
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums");
            albums = ejbA.findMyAllSharedAlbums(userLogin);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return albums;
    }

    /**
     * GET method to get all user´s shared Albums data by name: it uses business
     * method findMySharedAlbumsByName.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Path("findMySharedAlbumsByName/{userLogin}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByName(@PathParam("userLogin") String userLogin,
            @PathParam("name") String name
    ) {
        ArrayList<Album> albums;
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by name");
            albums = ejbA.findMySharedAlbumsByName(userLogin, name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return albums;
    }

    /**
     * GET method to get all user´s shared Albums data by date: it uses business
     * method findMySharedAlbumsByDate.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param stringDate A String that contains the date the User introduce.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Path("findMySharedAlbumsByDate/{userLogin}/{stringDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByDate(@PathParam("userLogin") String userLogin,
            @PathParam("stringDate") String stringDate
    ) {
        ArrayList<Album> albums = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by date");
            Date date = format.parse(stringDate);
            albums = ejbA.findMySharedAlbumsByDate(userLogin, date);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(AlbumFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albums;
    }

    /**
     * GET method to get all user´s shared Albums data by album creator: it uses
     * business method findMySharedAlbumsByCreator.
     *
     * @param userLogin a string with the login from the user who is logged to
     * de app.
     * @param creatorLogin A String that contains the words the user introduced.
     * @return An ArrayList of Albums that contains the albums the method found.
     */
    @GET
    @Path("findMySharedAlbumsByCreator/{userLogin}/{creatorLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Album> findMySharedAlbumsByCreator(@PathParam("userLogin") String userLogin,
            @PathParam("creatorLogin") String creatorLogin
    ) {
        ArrayList<Album> albums = null;
        try {
            LOGGER.log(Level.INFO, "Reading data for all user's shared Albums by album creator");
            albums = ejbA.findMySharedAlbumsByCreator(userLogin, creatorLogin);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return albums;
    }
}
