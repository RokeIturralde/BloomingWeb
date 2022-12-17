/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulContent;

import entities.CustomImage;
import entities.CustomText;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Roke
 */
public class EJBContentManager implements ContentManagerLocal {

    /**
     * EntityManager for DataModelExamplePU persistence unit.
     */
    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    /**
     * This method introduces a new image in the database.
     *
     * @param customImage The CustomImage entity object containing new
     * customImage data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void introduceImage(CustomImage customImage) throws CreateException {
        try {
            em.persist(customImage);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method introduces a new text in the database.
     *
     * @param customText The CustomText entity object containing new customText
     * data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void introduceText(CustomText customText) throws CreateException {
        try {
            em.persist(customText);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method updates an customImage data in the database.
     *
     * @param customImage The CustomImage entity object containing modified
     * customImage data.
     * @throws UpdateException Thrown when any error or exception occurs during
     * update.
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateImage(@PathParam("id") Integer id, CustomImage customImage) throws UpdateException {
        try {
            if (!em.contains(customImage)) {
                em.merge(customImage);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * This method updates an customText data in the database.
     *
     * @param customText The CustomText entity object containing modified
     * customText data.
     * @throws UpdateException Thrown when any error or exception occurs during
     * update.
     */
    @Override
    public void updateText(CustomText customText) throws UpdateException {
        try {
            if (!em.contains(customText)) {
                em.merge(customText);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * This method removes an customImage from the database.
     *
     * @param customImage The CustomImage entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during
     * deletion.
     */
    @Override
    public void removeImage(CustomImage customImage) throws DeleteException {
        try {
            em.remove(em.merge(customImage));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * This method removes an customText from the database.
     *
     * @param customText The CustomText entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during
     * deletion.
     */
    @Override
    public void removeText(CustomText customText) throws DeleteException {
        try {
            em.remove(em.merge(customText));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

}
