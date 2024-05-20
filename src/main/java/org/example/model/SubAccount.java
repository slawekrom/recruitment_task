package org.example.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.Currency;

@Getter
@Setter
@Builder
public class SubAccount {

  private Currency currency;
  private BigDecimal balance;
}
