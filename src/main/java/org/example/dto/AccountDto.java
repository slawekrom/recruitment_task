package org.example.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

  private String pesel;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private Set<SubAccountDto> subAccounts;
}
