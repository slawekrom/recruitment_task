package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeDto {

  @NotBlank
  private String pesel;
  @NotBlank
  private BigDecimal amount;

}
