/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulContent;

import entities.Content;
import entities.CustomImage;
import entities.CustomText;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindAllException;
import exceptions.FindContentException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Roke
 */
public interface ContentInterface {

    public void removeContent(Integer contentId) throws DeleteException;

    public List<Content> findCustomImageByName(String name) throws FindContentException;

    public List<Content> findCustomTextByName(String name) throws FindContentException;

    public Content findContentById(Integer contentId) throws FindContentException;

    public List<Content> findCustomImageByDate(Date uploadDate) throws FindContentException;

    public List<Content> findCustomTextByDate(Date uploadDate) throws FindContentException;

    public List<Content> findCustomImageByAlbum(Integer idAlbum) throws FindContentException;

    public List<Content> findCustomTextByAlbum(Integer idAlbum) throws FindContentException;

    public void updateCustomImage(CustomImage customImage) throws UpdateException;

    public void updateCustomText(CustomText customText) throws UpdateException;

    public CustomText findCustomTextById(Integer contentId) throws FindContentException;

    public void createCustomText(CustomText content) throws CreateException;

    public void createCustomImage(CustomImage content) throws CreateException;

    public CustomImage findCustomImageById(Integer contentId) throws FindContentException;

    public List<Content> findCustomTextByLocation(String location) throws FindContentException;

    public List<Content> findCustomImageByLocation(String location) throws FindContentException;
}
