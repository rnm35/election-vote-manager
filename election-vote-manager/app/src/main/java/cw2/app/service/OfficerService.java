package main.java.electionvotemanager.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.officer;
import repository.OfficerRepository;

@Service
public class OfficerService {
    
    private final OfficerRepository officerRepository;

    @Autowired
    public OfficerService(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    public Officer getOfficerByUsername(String username) {
        return officerRepository.findByUsername(username);
    }
}
