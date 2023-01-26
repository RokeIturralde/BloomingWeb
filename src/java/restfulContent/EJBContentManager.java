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

    /**
     *
     * @param content
     * @throws CreateException
     */
    @Override
    public void createCustomText(CustomText content) throws CreateException {
        try {
            em.persist(content);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }

    }

    @Override
    public void createCustomImage(CustomImage content) throws CreateException {
        try {
            em.persist(content);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }

    }

    @Override
    public CustomImage findCustomImageById(Integer contentId) throws FindContentException {
        CustomImage customImage = null;
        try {
            customImage = em.find(CustomImage.class, contentId);
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
        return customImage;
    }

    @Override
    public List<Content> findCustomImageByName(String name, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomImageByName").setParameter("contentName", name).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomTextByName(String name, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByName").setParameter("contentName", name).setParameter("userLogin", userLogin).getResultList();

            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomImageByDate(Date uploadDate, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByDate").setParameter("date", uploadDate).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomTextByDate(Date uploadDate, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByDate").setParameter("date", uploadDate).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomImageByAlbum(Integer idAlbum, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByAlbum").setParameter("albumId", idAlbum).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomTextByAlbum(Integer idAlbum, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByAlbum").setParameter("albumId", idAlbum).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomTextByLocation(String location, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByLocation").setParameter("contentLocation", location).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    @Override
    public List<Content> findCustomImageByLocation(String location, String userLogin) throws FindContentException {
        List<Content> contents;
        try {
            contents = em.createNamedQuery("findCustomTextByLocation").setParameter("contentLocation", location).setParameter("userLogin", userLogin).getResultList();
            return contents;
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }
}
