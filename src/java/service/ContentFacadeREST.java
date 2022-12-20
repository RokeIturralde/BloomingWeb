/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Content;
import exceptions.FindAllException;
import exceptions.FindContentException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("entities.content")
public class ContentFacadeREST {

    @EJB
    private ContentInterface ejbC;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Content entity) {
        ejbC.createContent(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Content entity) {
        ejbC.updateContent(entity);
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void remove(Content content) {
        ejbC.removeContent(content);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findAllContents() throws FindAllException {
        List<Content> contents;
        try {
            contents = ejbC.findAllContents();
        } catch (Exception e) {
            throw new FindAllException(e.getMessage());

        }
        return contents;
    }

    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByName(@PathParam("name") String name) throws FindContentException {
        try {
            return ejbC.findContentByName(name);
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());

        }
    }

    @GET
    @Path("/findByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByDate(@PathParam("date") Date date) throws FindContentException {
        try {
            return ejbC.findContentByDate(date);
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }

    /*@GET
    @Path("{albumId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Content> findContentByAlbum(@PathParam("albumId") Integer idAlbum) throws FindContentException {
        try {
            return ejbC.findContentByAlbum(idAlbum);
        } catch (Exception e) {
            throw new FindContentException(e.getMessage());
        }
    }*/
}
