/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nerea
 */
@NamedQueries({
    //Query to find an album using the id.
    @NamedQuery(
            name = "findAlbumByID", query = "SELECT a "
            + "FROM Album a "
            + "WHERE a.id = :albumId"
    )
    ,
    //Query to find an album using the name.
    @NamedQuery(
            name = "findAlbumByName", query = "SELECT a "
            + "FROM Album a "
            + "WHERE a.creator = :creator "
            + "AND a.name = :name"
    )
    ,
    //Query to find all the albums from a creator.
    @NamedQuery(
            name = "findMyAllAlbums", query = "SELECT a "
            + "FROM Album a "
            + "WHERE a.creator = :creator"
    )
    ,
    //Query to find all the albums from a creator 
    //and the name contains the words the user introduced.
    @NamedQuery(
            name = "findMyAlbumsByName", query = "SELECT a "
            + "FROM Album a "
            + "WHERE a.creator = :creator "
            + "AND a.name LIKE '%:name%'"
    )
    ,
    //Query to find all the albums from a creator 
    //and has an specific creation date.
    @NamedQuery(
            name = "findMyAlbumsByDate", query = "SELECT a "
            + "FROM Album a "
            + "WHERE a.creator = :creator "
            + "AND a.creationDate = :date"
    )
    ,
    @NamedQuery(
            name = "findMyAllSharedAlbums", query = "SELECT a "
            + "FROM Album a INNER JOIN a.users us "
            + "WHERE a.creator != :user "
            + "AND us.login = :userLogin"
    )
    ,
    @NamedQuery(
            name = "findMySharedAlbumsByName", query = "SELECT a "
            + "FROM Album a INNER JOIN a.users us "
            + "WHERE a.creator != :user "
            + "AND us.login = :userLogin "
            + "AND a.name LIKE '%:name%'"
    )
    ,
    @NamedQuery(
            name = "findMySharedAlbumsByDate", query = "SELECT a "
            + "FROM Album a INNER JOIN a.users us "
            + "WHERE a.creator != :user "
            + "AND us.login = :userLogin "
            + "AND a.creationDate = :date"
    )
    ,
    @NamedQuery(
            name = "findMySharedAlbumsByCreator", query = "SELECT a "
            + "FROM Album a, INNER JOIN a.users us, INNER JOIN a.creator c "
            + "WHERE c.login LIKE '%:creatorLogin%'"
            + "AND us.login = :userLogin"
    )
    ,//EN PROCESO; NO INNER EN DELETE, NS COMO borrar usuario de la lista de acessibilidad al album
    @NamedQuery(
            name = "deleteFromSharedsAnAlbum", query = "DELETE "
            + "FROM Album a, INNER JOIN a.users us "
            + "WHERE us.login = :userLogin "
            + "AND a.id = :idAlbum"
    )
})

@Entity
@Table(name = "album", schema = "bloomingdb")
@XmlRootElement
public class Album implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    /**
     * Relation containing the user creator of the Album
     */
    @ManyToOne
    private User creator;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    /**
     * Relation containing the list of users that were shared the Album
     */
    @ManyToMany(mappedBy = "sharedAlbums")
    private List<User> users;
    /**
     * Relational field containing the list contents in the Album
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contents_albums", schema = "bloomingdb", joinColumns = {
        @JoinColumn(name = "album_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "content_id", referencedColumnName = "id")})
    private List<Content> contents;

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
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
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album[" + id + "]-> Name: " + name + ", Creator: " + creator + ", CreationDate: " + creationDate + ", ...";
    }

}
