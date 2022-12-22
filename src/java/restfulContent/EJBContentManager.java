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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Roke
 */
@Stateless
public class EJBContentManager implements ContentInterface {

    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    /**
     * Creates a new Content
     *
     * @param content
     * @throws CreateException
     */
    @Override
    public void createContent(Content content) throws CreateException {
        try {
            em.persist(content);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }

    }

    /**
     * Updates a Content
     *
     * @param content
     * @throws UpdateException
     */
    @Override
    public void updateContent(Content content) throws UpdateException {
        try {
            if (!em.contains(content)) {
                em.merge(content);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }

    }

    /**
     * Delete a Content by their ID
     *
     * @param contentId
     * @throws DeleteException
     */
    @Override
    public void removeContent(Integer contentId) throws DeleteException {
        try {
            em.remove(em.merge(em.find(Content.class, contentId)));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Finds Content by the given name
     *
     * @param name the String you want to find
     * @return a List of Contents that matched
     * @throws FindContentException
     */
    @Override
    public List<Content> findContentByName(String name) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findContentByName").setParameter("contentName", name).getResultList();

            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }

    }

    /**
     * Finds all the Content (Just for testing)
     *
     * @return all the Content
     * @throws FindAllException
     */
    @Override
    public List<Content> findAllContents() throws FindAllException {
        List<Content> contents;
        try {

        } catch (Exception e) {
            throw new FindAllException(e.getMessage());
        }
        contents = em.createNamedQuery("findAllContents").getResultList();
        return contents;
    }

    /**
     * Finds Content by Date
     *
     * @param uploadDate
     * @return a list of matched Contents
     * @throws FindContentException
     */
    @Override
    public List<Content> findContentByDate(Date uploadDate) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findContentByDate").setParameter("date", uploadDate).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }

    }

    @Override
    public List<Content> findContentByAlbum(Integer idAlbum) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findContentByAlbum").setParameter("albumId", idAlbum).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }

    }

    /**
     * Finds Content by their Id
     *
     * @param contentId
     * @return the Content with that ID
     * @throws FindContentException
     */
    @Override
    public Content findContentById(Integer contentId) throws FindContentException {
        Content content = null;
        try {
            content = em.find(Content.class, contentId);
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
        return content;
    }

    /**
     * Updates a CustomImage
     *
     * @param customImage
     * @throws UpdateException
     */
    @Override
    public void updateCustomImage(CustomImage customImage) throws UpdateException {
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
     * Updates a CustomText
     *
     * @param customText
     * @throws UpdateException
     */
    @Override
    public void updateCustomText(CustomText customText) throws UpdateException {
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
     * Finds a CustomText by ID
     *
     * @param contentId
     * @return
     * @throws FindContentException
     */
    @Override
    public CustomText findCustomTextById(Integer contentId) throws FindContentException {
        CustomText customText = null;
        try {
            customText = em.find(CustomText.class, contentId);
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
        return customText;
    }
}
