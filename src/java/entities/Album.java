/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "album", schema = "bloomingdb")
@XmlRootElement
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAlbum;

    private Date creationDate;
    /**
     * Relation containing the user creator of the Album
     */
    @ManyToOne
    private User creator;

    public User getUser() {
        return creator;
    }

    public void setUser(User creator) {
        this.creator = creator;
    }
    private String description;
    private String name;
    /**
     * Relation containing the list of users that were shared the Album
     */
    @ManyToMany(mappedBy = "sharedAlbums")
    private List<User> users;

    public Integer getId() {
        return idAlbum;
    }

    @XmlTransient
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    /**
     * Relational field containing the list contents in the Album
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contents_albums", schema = "bloomingdb", joinColumns = {@JoinColumn(name = "album_idAlbum", referencedColumnName = "idAlbum")},
            inverseJoinColumns = {@JoinColumn(name = "content_contentId", referencedColumnName = "contentId")})
    private List<Content> contents;

    public void setId(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlbum != null ? idAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.idAlbum == null && other.idAlbum != null) || (this.idAlbum != null && !this.idAlbum.equals(other.idAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Album[ id=" + idAlbum + " ]";
    }

}
