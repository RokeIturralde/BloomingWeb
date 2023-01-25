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
 */
@NamedQueries({
    @NamedQuery(
            name = "findCustomImageByName", query = "SELECT c FROM customImage c where c.name=:contentName"
    )
    ,
@NamedQuery(
            name = "findCustomImageByAlbum", query = "SELECT c FROM customImage c inner join c.albums a where a.id=:albumId")
    ,
@NamedQuery(
            name = "findCustomImageByDate", query = "SELECT c FROM customImage c where c.uploadDate=:date"
    )
    ,
@NamedQuery(
            name = "findCustomImageByLocation", query = "SELECT c FROM customImage c where c.location=:contentLocation"
    )
})
@Entity
@Table(name = "customImage", schema = "bloomingdb")
@XmlRootElement
public class CustomImage extends Content {

    private static final long serialVersionUID = 1L;
    private Byte[] bytes;

    public Byte[] getBytes() {
        return bytes;
    }

    public void setBytes(Byte[] bytes) {
        this.bytes = bytes;
    }

}
