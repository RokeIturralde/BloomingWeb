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
import exceptions.ReadException;
import exceptions.UpdateException;

/**
 *
 * @author Roke
 */
public interface ContentManagerLocal {

    /**
     * This method introduces a new image in the database.
     *
     * @param customImage The CustomImage entity object containing new
     * customImage data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    public void introduceImage(CustomImage customImage) throws CreateException;

    /**
     * This method introduces a new text in the database.
     *
     * @param customText The CustomText entity object containing new customText
     * data.
     * @throws CreateException Thrown when any error or exception occurs during
     * creation.
     */
    public void introduceText(CustomText customText) throws CreateException;

    /**
     * This method updates an customImage data in the database.
     *
     * @param customImage The CustomImage entity object containing modified
     * customImage data.
     * @throws UpdateException Thrown when any error or exception occurs during
     * update.
     */
    public void updateImage(CustomImage customImage) throws UpdateException;

    /**
     * This method updates an customText data in the database.
     *
     * @param customText The CustomText entity object containing modified
     * customText data.
     * @throws UpdateException Thrown when any error or exception occurs during
     * update.
     */
    public void updateText(CustomText customText) throws UpdateException;

    /**
     * This method removes an customImage from the database.
     *
     * @param customImage The CustomImage entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during
     * deletion.
     */
    public void removeImage(CustomImage customImage) throws DeleteException;

    /**
     * This method removes an customText from the database.
     *
     * @param customText The CustomText entity object to be removed.
     * @throws DeleteException Thrown when any error or exception occurs during
     * deletion.
     */
    public void removeText(CustomText customText) throws DeleteException;

}
