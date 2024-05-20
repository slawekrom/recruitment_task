package org.example.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRatesSeriesDto {

  private String currency;
  private String code;
  private Set<RateDto> rates;
}
