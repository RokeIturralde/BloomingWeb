/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roke
 */
@Entity
@Table(name = "customImage", schema = "bloomingdb")
@XmlRootElement
public class CustomImage extends Content implements Serializable {

    private static final long serialVersionUID = 1L;
    private Byte[] bytes;

    public Byte[] getBytes() {
        return bytes;
    }

    public void setBytes(Byte[] bytes) {
        this.bytes = bytes;
    }

}
