package org.example.controller;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.dto.CreateAccountDto;
import org.example.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping
  public void createAccount(@RequestBody CreateAccountDto createAccountDto) {
    accountService.save(createAccountDto);
  }

  @GetMapping(path = "{pesel}")
  public AccountDto getAccount(@PathVariable("pesel") String pesel) {
    return accountService.findByPesel(pesel);
  }

  @GetMapping(path = "all")
  public Set<AccountDto> getAllAccounts() {
    return accountService.findAll();
  }
}
