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
            name = "findCustomImageByName", query = "SELECT c FROM CustomImage c JOIN c.albums a JOIN a.users u WHERE c.name=:name AND u.login=:userLogin"
    )
    ,
@NamedQuery(
            name = "findCustomImageByAlbum", query = "SELECT c FROM CustomImage c JOIN c.albums a JOIN a.users u WHERE a.id=:albumId AND u.login=:userLogin")
    ,
@NamedQuery(
            name = "findCustomImageByDate", query = "SELECT c FROM CustomImage c JOIN c.albums a JOIN a.users u WHERE c.uploadDate=:date AND u.login=:userLogin"
    )
    ,
@NamedQuery(
            name = "findCustomImageByLocation", query = "SELECT c FROM CustomImage c JOIN c.albums a JOIN a.users u WHERE c.location=:contentLocation AND u.login=:userLogin"
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
