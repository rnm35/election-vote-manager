package main.java.electionvotemanager.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import domain.Account;
import domain.Election;
import domain.Officer;
import service.AccountService;
import service.OfficerService;

@Controller
@RequestMapping("/election")
public class ElectionController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Account getAccountById(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Account saveAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody void deleteAccount(@RequestBody Account account) {
        accountService.deleteAccount(account);
    }

    @RequestMapping(value = "/verifyUVC", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> verifyUVC(@RequestBody String uvc) {
        try {
            boolean isValid = accountService.verifyUVC(uvc);
            if (isValid) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("UVC not found.", HttpStatus.BAD_REQUEST);
            }
        } catch (UVCAlreadyUsedException e) {
            return new ResponseEntity<>("UVC already in use.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/verifyEmail", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> verifyEmail(@RequestBody String email) {
        try {
            boolean isValid = accountService.verifyEmail(email);
            if (isValid) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Email not found.", HttpStatus.BAD_REQUEST);
            }
        } catch (EmailAlreadyUsedException e) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/verifyUsernamePassword", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> verifyUsernamePassword(@RequestBody Account account) {
        try {
            boolean isValid = accountService.verifyUsernamePassword(account.getUsername(), account.getPassword());
            if (isValid) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Username or password are incorrect.", HttpStatus.BAD_REQUEST);
            }
        } catch (UsernamePasswordAlreadyUsedException e) {
            return new ResponseEntity<>("Username already in use.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}