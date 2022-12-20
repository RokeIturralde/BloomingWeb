/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulContent;

import entities.Content;
import exceptions.FindAllException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rpke
 */
public interface ContentInterface {

    public void createContent(Content content);

    public void updateContent(Content content);

    public void removeContent(Content content);

    public List<Content> findContentByName(String name);

    public List<Content> findAllContents();

    public List<Content> findContentByDate(Date uploadDate);
    //public List<Content> findContentByAlbum(Integer idAlbum);
}
