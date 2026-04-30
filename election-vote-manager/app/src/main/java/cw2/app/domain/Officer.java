package main.java.electionvotemanager.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    public Officer() {
        this.username = "election@shangrila.gov.sr";
        this.password = new BCryptPasswordEncoder().encode("shangrila2024$");
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    
    }
}
