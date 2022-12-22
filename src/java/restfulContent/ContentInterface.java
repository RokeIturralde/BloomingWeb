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

    public void createContent(Content content) throws CreateException;

    public void updateContent(Content content) throws UpdateException;

    public void removeContent(Integer contentId) throws DeleteException;

    public List<Content> findContentByName(String name) throws FindContentException;

    public List<Content> findAllContents() throws FindAllException;

    public Content findContentById(Integer contentId) throws FindContentException;

    public List<Content> findContentByDate(Date uploadDate) throws FindContentException;

    public List<Content> findContentByAlbum(Integer idAlbum) throws FindContentException;
    
    public void updateCustomImage(CustomImage customImage) throws UpdateException;
    
    public void updateCustomText(CustomText customText) throws UpdateException;

    public CustomText findCustomTextById(Integer contentId) throws FindContentException;
}
