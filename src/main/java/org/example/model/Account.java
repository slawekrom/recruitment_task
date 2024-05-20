package org.example.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

  private String pesel;
  private String firstName;
  private String lastName;
  private Set<SubAccount> subAccounts;
  private LocalDate dateOfBirth;

}
