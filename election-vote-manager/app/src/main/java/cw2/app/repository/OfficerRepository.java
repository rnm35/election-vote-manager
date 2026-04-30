package main.java.electionvotemanager.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.java.electionvotemanager.app.domain.Officer;

@Repository
public interface OfficerRepository extends CrudRepository<Officer, Long> {
    Officer findByUsername(String username);
}