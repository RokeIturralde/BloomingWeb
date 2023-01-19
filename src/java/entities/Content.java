/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Roke
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllContents", query = "SELECT c FROM Content c"
    )
    ,
        @NamedQuery(
            name = "findContentByName", query = "SELECT c FROM Content c where c.name=:contentName"
    )
    ,
@NamedQuery(
            name = "findContentByAlbum", query = "SELECT c FROM Content c inner join c.albums a where a.id=:albumId"
    )
    ,
@NamedQuery(
            name = "findContentByDate", query = "SELECT c FROM Content c where c.uploadDate=:date"
    )
    ,
@NamedQuery(
            name = "findContentByLocation", query = "SELECT c FROM Content c where c.location=:contentLocation"
    )
})
@Entity
@Table(name = "content", schema = "bloomingdb")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * Relation containing the list albums that include the Content
     */
    @ManyToMany(mappedBy = "contents", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Album> albums;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date uploadDate;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getContentId() {
        return id;
    }

    public void setContentId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Content[ id=" + id + " ]";
    }
}
