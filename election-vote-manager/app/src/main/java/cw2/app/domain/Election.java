package main.java.electionvotemanager.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Election {
    private boolean isStarted;
    private boolean isEnded;
    private String results;
    private String winner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "election_id")
    private Integer id;

    @Column(name = "status")
    private String status;

    public void start() {
        this.isStarted = true;
    }

    public void end() {
        this.isEnded = true;
    }

    public String getResults() {
        return this.results;
    }

    public String getWinner() {
        return this.winner;
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public boolean isEnded() {
        return this.isEnded;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
