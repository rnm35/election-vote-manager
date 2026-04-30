package main.java.electionvotemanager.app.controller;

import main.java.electionvotemanager.app.domain.Officer;
import main.java.electionvotemanager.app.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import domain.Account;
import domain.Election;
import domain.Officer;
import service.AccountService;
import service.OfficerService;

@RestController
public class OfficerController {

    private final OfficerRepository officerRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OfficerService officerService;
    
    @Autowired
    public OfficerController(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }
    private Officer officer;

    @PostMapping("/startElection")
    public String startElection(@RequestParam String password) {
        Officer officer = officerService.findByUsername("election@shangrila.gov.sr");
        if (new BCryptPasswordEncoder().matches(password, officer.getPassword())) {
            officer.getElection().start();
            return "Election started";
        } else {
            return "Invalid password";
        }
    }

    @PostMapping("/endElection")
    public String endElection(@RequestParam String password) {
        Officer officer = officerService.findByUsername("election@shangrila.gov.sr");
        if (new BCryptPasswordEncoder().matches(password, officer.getPassword())) {
            officer.getElection().end();
            return "Election ended";
        } else {
            return "Invalid password";
        }
    }

    @GetMapping("/electionResults")
    public String getElectionResults(@RequestParam String password) {
        Officer officer = officerService.findByUsername("election@shangrila.gov.sr");
        if (new BCryptPasswordEncoder().matches(password, officer.getPassword())) {
            List<Account> accounts = accountService.findAll();
            StringBuilder electionResults = new StringBuilder();
            for (Account account : accounts) {
                electionResults.append("Candidate: ").append(account.getCandidateName())
                    .append(", Vote Count: ").append(account.getVoteCount()).append("\n");
            }
            return electionResults.toString();
        } else {
            return "Invalid password";
        }
    }

    @PostMapping("/announceWinner")
    public String announceWinner(@RequestParam String password) {
        Officer officer = officerService.findByUsername("election@shangrila.gov.sr");
        if (new BCryptPasswordEncoder().matches(password, officer.getPassword())) {
            List<Account> accounts = accountService.findAll();
            Map<String, Integer> partyVotes = new HashMap<>();
            for (Account account : accounts) {
                partyVotes.put(account.getPartyName(), partyVotes.getOrDefault(account.getPartyName(), 0) + account.getVoteCount());
            }
            String winnerParty = Collections.max(partyVotes.entrySet(), Map.Entry.comparingByValue()).getKey();
            int totalSeats = accounts.size();
            if (partyVotes.get(winnerParty) > totalSeats / 2) {
                return "Winner: " + winnerParty;
            } else {
                return "Hung Parliament";
            }
        } else {
            return "Invalid password";
        }
    }

    @PostMapping("/declareHungParliament")
    public String declareHungParliament(@RequestParam String password) {
        if (new BCryptPasswordEncoder().matches(password, officer.getPassword())) {
            Officer officer = officerService.findByUsername("election@shangrila.gov.sr");
            List<Account> accounts = accountService.findAll();
            Map<String, Integer> partyVotes = new HashMap<>();
            for (Account account : accounts) {
                partyVotes.put(account.getPartyName(), partyVotes.getOrDefault(account.getPartyName(), 0) + account.getVoteCount());
            }
            String winnerParty = Collections.max(partyVotes.entrySet(), Map.Entry.comparingByValue()).getKey();
            int totalSeats = accounts.size();
            if (partyVotes.get(winnerParty) <= totalSeats / 2) {
                officer.getElection().setHungParliament(true);
            }
            return "Hung parliament";
        } else {
            return "Invalid password";
        }
    }


}
