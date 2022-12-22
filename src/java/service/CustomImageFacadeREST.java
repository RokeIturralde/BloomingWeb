/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.CustomImage;
import exceptions.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
}
