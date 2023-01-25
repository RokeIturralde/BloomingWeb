/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roke
 */@NamedQueries({
    @NamedQuery(
            name = "findCustomTextByName", query = "SELECT c FROM customText c where c.name=:contentName"
    )
    ,
@NamedQuery(
            name = "findCustomTextByAlbum", query = "SELECT c FROM customText c inner join c.albums a where a.id=:albumId")
    ,
@NamedQuery(
            name = "findCustomTextByDate", query = "SELECT c FROM customText c where c.uploadDate=:date"
    )
    ,
@NamedQuery(
            name = "findCustomTextByLocation", query = "SELECT c FROM customText c where c.location=:contentLocation"
    )
})
@Entity
@Table(name = "customText", schema = "bloomingdb")
@XmlRootElement
public class CustomText extends Content {

    private static final long serialVersionUID = 1L;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
