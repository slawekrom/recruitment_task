package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDto {

  @NotBlank
  private String pesel;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotBlank
  private BigDecimal balance;
  @NotBlank
  private LocalDate dateOfBirth;
}
