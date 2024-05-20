package org.example.service;

import java.math.RoundingMode;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.example.dto.ExchangeDto;
import org.example.enums.Currency;
import org.example.exception.AccountException;
import org.example.exception.ExchangeException;
import org.example.model.SubAccount;
import org.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {

  private final AccountRepository accountRepository;
  private final NpbService npbService;

  public void exchangePlnToUsd(ExchangeDto exchangeDto) {
    var pesel = exchangeDto.getPesel();
    var amount = exchangeDto.getAmount();
    var account = accountRepository.getAccountByPesel(pesel);
    if (Objects.isNull(account)) {
      throw new AccountException("Account not found");
    }

    var usdRate = npbService.getUsdRate();

    SubAccount plnSubAccount = account.getSubAccounts().stream()
        .filter(subAccount -> subAccount.getCurrency() == Currency.PLN)
        .findFirst()
        .orElseThrow(() -> new AccountException("SubAccount not found"));

    if (plnSubAccount.getBalance().compareTo(amount) < 0) {
      throw new ExchangeException("Insufficient account balance");
    }

    plnSubAccount.setBalance(plnSubAccount.getBalance().subtract(amount));

    account.getSubAccounts().stream()
        .filter(subAccount -> subAccount.getCurrency() == Currency.USD)
        .findFirst()
        .ifPresent(subAccount -> subAccount.setBalance(
            subAccount.getBalance().add(amount.divide(usdRate, 2, RoundingMode.CEILING))));
  }

  public void exchangeUsdToPln(ExchangeDto exchangeDto) {
    var pesel = exchangeDto.getPesel();
    var amount = exchangeDto.getAmount();
    var account = accountRepository.getAccountByPesel(pesel);
    if (Objects.isNull(account)) {
      throw new AccountException("Account not found");
    }

    var usdRate = npbService.getUsdRate();

    SubAccount usdSubAccount = account.getSubAccounts().stream()
        .filter(subAccount -> subAccount.getCurrency() == Currency.USD)
        .findFirst()
        .orElseThrow(() -> new AccountException("SubAccount not found"));

    if (usdSubAccount.getBalance().compareTo(amount) < 0) {
      throw new ExchangeException("Insufficient account balance");
    }

    usdSubAccount.setBalance(usdSubAccount.getBalance().subtract(amount));

    account.getSubAccounts().stream()
        .filter(subAccount -> subAccount.getCurrency() == Currency.PLN)
        .findFirst()
        .ifPresent(subAccount -> subAccount.setBalance(
            subAccount.getBalance().add(amount.multiply(usdRate))
                .setScale(2, RoundingMode.CEILING)));
  }
}
