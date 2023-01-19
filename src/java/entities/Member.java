package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dani
 */
 @NamedQueries({
    @NamedQuery(
            name = "findMembersByPlan", query = "SELECT u FROM User u WHERE u.privilege='MEMBER' AND u.plan=:plan"
    )
    ,
    @NamedQuery(
            name = "getEveryUser", query = "SELECT u FROM User u"
    )
    ,
    @NamedQuery(
            name = "getEveryMember", query = "SELECT u FROM User u WHERE u.privilege='MEMBER'"
    )
    
    // query that returns every user as users
    
})
@Entity
@Table(name = "member", schema = "bloomingdb")
@XmlRootElement
public class Member extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Temporal(TemporalType.DATE)
    private Date memberEndingDate;
    @Temporal(TemporalType.DATE)
    private Date memberStartingDate;
    @ManyToOne
    private MembershipPlan plan;

    public Date getMemberEndingDate() {
        return memberEndingDate;
    }

    public void setMemberEndingDate(Date memberEndingDate) {
        this.memberEndingDate = memberEndingDate;
    }

    public Date getMemberStartingDate() {
        return memberStartingDate;
    }

    public void setMemberStartingDate(Date memberStartingDate) {
        this.memberStartingDate = memberStartingDate;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public void setPlan(MembershipPlan plan) {
        this.plan = plan;
    }
}
