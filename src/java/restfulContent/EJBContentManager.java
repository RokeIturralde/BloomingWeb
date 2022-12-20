/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulContent;

import entities.Content;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Roke
 */
@Stateless
public class EJBContentManager implements ContentInterface {

    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    @Override
    public void createContent(Content content) {
        em.persist(content);
    }

    @Override
    public void updateContent(Content content) {
        if (!em.contains(content)) {
            em.merge(content);
        }
        em.flush();
    }

    @Override
    public void removeContent(Content content) {
        em.remove(em.merge(content));
    }

    @Override
    public List<Content> findContentByName(String name) {
        List<Content> contents;
        contents = em.createNamedQuery("findContentByName").setParameter("contentName", name).getResultList();

        return contents;
    }

    @Override
    public List<Content> findAllContents() {
        List<Content> contents;
        contents = em.createNamedQuery("findAllContents").getResultList();
        return contents;
    }

    @Override
    public List<Content> findContentByDate(Date uploadDate) {
        List<Content> contents;
        contents = em.createNamedQuery("findContentByDate").setParameter("date", uploadDate).getResultList();
        return contents;
    }

    /* @Override
    public List<Content> findContentByAlbum(Integer idAlbum) {
        List<Content> contents;
        contents = em.createNamedQuery("findContentByAlbum").setParameter("albumId", idAlbum).getResultList();
        return contents;
    }*/
}
