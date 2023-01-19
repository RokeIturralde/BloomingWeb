/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
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
