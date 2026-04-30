package main.java.electionvotemanager.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "canid")
    private Integer id;

    @Column(name = "candidate")
    private String candidateName;

    @Column(name = "party_id")
    private Integer partyId;

    @Column(name = "constituency_id")
    private Integer constituencyId;

    @Column(name = "vote_count")
    private Integer voteCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(Integer constituencyId) {
        this.constituencyId = constituencyId;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

}

@Entity
@Table(name = "constituency")
public class Constituency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constituency_id")
    private Integer id;

    @Column(name = "constituency_name")
    private String name;

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

}

@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Integer id;

    @Column(name = "party")
    private String partyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}

@Entity
@Table(name = "uvc_code")
public class UvcCode {
    @Id
    @Column(name = "UVC")
    private String uvc;

    @Column(name = "used")
    private Integer used;

    public String getUvc() {
        return uvc;
    }

    public void setUvc(String uvc) {
        this.uvc = uvc;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}

@Entity
@Table(name = "voter")
public class Voter {
    @Id
    @Column(name = "voter_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "password")
    private String password;

    @Column(name = "UVC")
    private String uvc;

    @Column(name = "constituency_id")
    private Integer constituencyId;

    public String getUvc() {
        return uvc;
    }

    public void setUvc(String uvc) {
        this.uvc = uvc;
    }

    public Integer getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(Integer constituencyId) {
        this.constituencyId = constituencyId;
    }
}
