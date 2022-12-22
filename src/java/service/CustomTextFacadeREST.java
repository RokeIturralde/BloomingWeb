/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.CustomText;
import exceptions.FindContentException;
import exceptions.UpdateException;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
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
}
