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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "album", schema = "bloomingdb")
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    /**
     * Relational field containing the list contents in the Album
     */
    @ManyToMany
    @JoinTable(name = "contents_albums", schema = "bloomingdb")
    private List<Content> contents;

    public void setId(Integer idAlbum) {
        this.idAlbum = idAlbum;
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
