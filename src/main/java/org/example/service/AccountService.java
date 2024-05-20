package org.example.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.dto.CreateAccountDto;
import org.example.exception.AccountException;
import org.example.mapper.AccountMapper;
import org.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public void save(CreateAccountDto createAccountDto) {
    if (isAdult(createAccountDto.getDateOfBirth())) {
      if (Objects.nonNull(accountRepository.getAccountByPesel(createAccountDto.getPesel()))) {
        throw new AccountException(
            "Cannot create account. Account with pesel " + createAccountDto.getPesel()
                + " already exist");
      }
      var account = AccountMapper.INSTANCE.toAccount(createAccountDto);
      accountRepository.save(account);
    } else {
      throw new AccountException("Cannot create account. User must be of legal age.");
    }
  }

  public AccountDto findByPesel(String pesel) {
    var account = accountRepository.getAccountByPesel(pesel);
    return AccountMapper.INSTANCE.toAccountDto(account);
  }

  public Set<AccountDto> findAll() {
    return accountRepository.getAllAccount()
        .stream()
        .map(AccountMapper.INSTANCE::toAccountDto)
        .collect(Collectors.toSet());
  }

  private static boolean isAdult(LocalDate dateOfBirth) {
    return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
  }
}
