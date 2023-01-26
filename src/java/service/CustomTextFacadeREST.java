/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Content;
import entities.CustomText;
import exceptions.CreateException;
import exceptions.FindContentException;
import exceptions.UpdateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
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
@Path("entities.customtext")
public class CustomTextFacadeREST {

    @EJB
    private ContentInterface ejbC;
    private Logger LOGGER = Logger.getLogger(ContentFacadeREST.class.getName());

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(CustomText entity) {
        try {
            ejbC.updateCustomText(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @GET
    @Path("customTextFindId/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CustomText findCustomTextById(@PathParam("id") Integer contentId) {
        CustomText customText = null;
        try {
            customText = ejbC.findCustomTextById(contentId);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return customText;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(CustomText entity) {
        try {
            ejbC.createCustomText(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    //New methods
    @GET
    @Path("findByName/{name}/{userLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findCustomTextByName(@PathParam("name") String name, @PathParam("userLogin") String userLogin) {
        List<Content> contents = null;
        try {
            contents = ejbC.findCustomTextByName(name, userLogin);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return contents;
    }

    @GET
    @Path("date/{date}/{userLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findCustomTextByDate(@PathParam("date") String stringDate, @PathParam("userLogin") String userLogin) {
        List<Content> contents = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(stringDate);
            contents = ejbC.findCustomTextByDate(date, userLogin);
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
    @Path("/findByAlbum/{albumId}/{userLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findCustomTextByAlbum(@PathParam("albumId") Integer idAlbum, @PathParam("userLogin") String userLogin) throws FindContentException {
        try {
            return ejbC.findCustomTextByAlbum(idAlbum, userLogin);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("/findByLocation/{contentLocation}/{userLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findCustomTextByLocation(@PathParam("contentLocation") String contentLocation, @PathParam("userLogin") String userLogin) throws FindContentException {
        try {
            return ejbC.findCustomTextByLocation(contentLocation, userLogin);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
