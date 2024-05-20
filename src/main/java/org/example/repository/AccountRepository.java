package org.example.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.example.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

  private final Map<String, Account> accounts = new HashMap<>();

  public void save(Account account) {
    accounts.put(account.getPesel(), account);
  }

  public Account getAccountByPesel(String pesel) {
    return accounts.get(pesel);
  }

  public Collection<Account> getAllAccount() {
    return accounts.values();
  }
}
