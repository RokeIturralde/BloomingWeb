package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * @author dani
 */

 @NamedQueries({
    @NamedQuery(
            name = "findUserByName", query = "SELECT u FROM user u where u.name=:userName"
    )
    ,
    @NamedQuery(
            name = "findUserByEmail", query = "SELECT u FROM user u u.name=:userEmail"
    )
    ,
    @NamedQuery(
            name = "findUserByStatus", query = "SELECT u FROM user u where u.status=:userStatus"
    )
})
@Entity
@Table(name = "user", schema = "bloomingdb")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    private String email;
    private String fullName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Privilege privilege;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChange;
    /**
     * Relational field containing the albums created by the User
     */
    @OneToMany(mappedBy = "creator")
    private List<Album> createdAlbums;

    @XmlTransient
    public List<Album> getCreatedAlbums() {
        return createdAlbums;
    }

    public void setCreatedAlbums(List<Album> createdAlbums) {
        this.createdAlbums = createdAlbums;
    }
    /**
     * Relational field containing the list of albums shared with the User
     */
    @ManyToMany
    @JoinTable(name = "user_sharedAlbums", schema = "bloomingdb", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    private List<Album> sharedAlbums;

    @XmlTransient
    public List<Album> getSharedAlbums() {
        return sharedAlbums;
    }

    public void setSharedAlbums(List<Album> sharedAlbums) {
        this.sharedAlbums = sharedAlbums;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + login + " ]";
    }

}
