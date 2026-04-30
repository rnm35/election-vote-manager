package main.java.electionvotemanager.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Account;
import repository.AccountRepository;

@Service
public class ElectionService {

    private final AccountRepository accountRepository;

    @Autowired
    public ElectionService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Integer id) {
        return accountRepository.findById(id);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }
}
