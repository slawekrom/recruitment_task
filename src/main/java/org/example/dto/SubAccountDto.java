package org.example.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.Currency;

@Getter
@Setter
public class SubAccountDto {

  private Currency currency;
  private BigDecimal balance;
}
