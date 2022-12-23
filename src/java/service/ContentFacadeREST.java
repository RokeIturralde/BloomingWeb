/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Content;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindAllException;
import exceptions.FindContentException;
import exceptions.UpdateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
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
import restfulContent.ContentInterface;

/**
 *
 * @author Roke
 */
@Path("entities.content")
public class ContentFacadeREST {

    @EJB
    private ContentInterface ejbC;

    private Logger LOGGER = Logger.getLogger(ContentFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Content entity) {
        try {
            ejbC.createContent(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Content entity) {
        try {
            ejbC.updateContent(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejbC.removeContent(id);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getLocalizedMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Content findContentById(@PathParam("id") Integer contentId) {
        Content content = null;
        try {
            content = ejbC.findContentById(contentId);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return content;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findAllContents() {
        List<Content> contents = null;
        try {
            contents = ejbC.findAllContents();
        } catch (FindAllException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return contents;
    }

    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByName(@PathParam("name") String name) {
        List<Content> contents = null;
        try {
            contents = ejbC.findContentByName(name);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return contents;
    }

    @GET
    @Path("date/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByDate(@PathParam("date") String stringDate) {
        List<Content> contents = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(stringDate);
            contents = ejbC.findContentByDate(date);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (ParseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new BadRequestException(ex.getMessage());
        }
        return contents;
    }

    @GET
    @Path("/findByAlbum/{albumId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByAlbum(@PathParam("albumId") Integer idAlbum) throws FindContentException {
        try {
            return ejbC.findContentByAlbum(idAlbum);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
