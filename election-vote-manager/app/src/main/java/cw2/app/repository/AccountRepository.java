package main.java.electionvotemanager.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.java.electionvotemanager.app.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
	
    List<Account> findAll();
    Account findById(Integer id);
}
