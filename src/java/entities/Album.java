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
    @NamedQuery(
            name = "myAlbums", query = "SELECT a "
            + "FROM Album a, User u "
            + "WHERE u.id = :creator"
    )
    ,
    /*@NamedQuery(
            name = "sharedAlbums", query = "SELECT a "
            + "FROM Album a, User u, users us, sharedAlbums sa "
            + "WHERE  us.id = :u.id"
            + "AND sa.id = :id"
    )*///
    @NamedQuery(
            name = "sharedAlbums", query = "SELECT a "
            + "FROM Album a, User u "
            + "WHERE  a.users = :u.id"
            + "AND u.sharedAlbums = :id"
    )
    , @NamedQuery(
            name = "myAlbumsByDate", query = "SELECT a.* "
                    + "FROM Album a "
                    + "WHERE "
                    + "ORDERBY"
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
        return "Album["+id+"]-> Name: "+name+", Creator: "+creator+", CreationDate: "+creationDate+", ...";
    }
    
}
