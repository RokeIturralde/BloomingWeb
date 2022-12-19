/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Content;
import exceptions.FindAllException;
import exceptions.FindContentException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restfulContent.EJBContentManager;

/**
 *
 * @author 2dam
 */
@Path("entities.content")
public class ContentFacadeREST extends EJBContentManager {

    @PersistenceContext(unitName = "BloomingWebPU")
    private EntityManager em;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Content entity) {
        em.persist(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Content entity) {
        em.merge(entity);
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void remove(@PathParam("id") Integer id) {
        em.remove(em.find(Content.class, id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Content find(@PathParam("id") Integer id) {
        return em.find(Content.class, id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findAll() throws FindAllException {
        List<Content> contents = null;
        contents = findAllContent();
        return contents;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findAllContent() throws FindAllException {
        List<Content> contents = new ArrayList<>();
        try {
            contents = em.createNamedQuery("findAllContent").getResultList();
        } catch (Exception e) {
            throw new FindAllException();

        }
        return contents;
    }

    public List<Content> findContentByUploadDate(Date uploadDate) throws FindAllException {
        List<Content> contents = new ArrayList<>();
        try {
            contents = em.createNamedQuery("findContentByDate").setParameter("date", uploadDate).getResultList();
        } catch (Exception e) {
            throw new FindAllException();

        }
        return contents;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByName(String name) throws FindContentException {
        List<Content> contents = new ArrayList<>();
        try {
            contents = em.createNamedQuery("findContentByName").setParameter("contentName", name).getResultList();
        } catch (Exception e) {
            throw new FindContentException();

        }
        return contents;
    }

    /* public List<Content> findContentByAlbum(Integer albumId) throws FindContentException {
        List<Content> contents = new ArrayList<>();
        try {
            contents = em.createNamedQuery("findContentByName").setParameter("albumId", albumId).getResultList();
        } catch (Exception e) {
            throw new FindContentException();

        }
        return contents;
    }*/
    public List<Content> findContentByDate(Date uploadDate) throws FindContentException {
        List<Content> contents = new ArrayList<>();
        try {
            contents = em.createNamedQuery("findContentByDate").setParameter("date", uploadDate).getResultList();
        } catch (Exception e) {
            throw new FindContentException();

        }
        return contents;
    }
}
