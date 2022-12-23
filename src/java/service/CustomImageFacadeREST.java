/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.CustomImage;
import exceptions.CreateException;
import exceptions.FindContentException;
import exceptions.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@Path("entities.customimage")
public class CustomImageFacadeREST {

    @EJB
    private ContentInterface ejbC;

    private Logger LOGGER = Logger.getLogger(CustomImageFacadeREST.class.getName());

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(CustomImage entity) {
        try {
            ejbC.updateCustomImage(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    @GET
    @Path("customImageFindId/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CustomImage findCustomTextById(@PathParam("id") Integer contentId) {
        CustomImage customImage = null;
        try {
            customImage = ejbC.findCustomImageById(contentId);
        } catch (FindContentException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return customImage;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createCustomImage(CustomImage entity) {
        try {
            ejbC.createCustomImage(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
