package org.example.service;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.SneakyThrows;
import org.example.dto.ExchangeRatesSeriesDto;
import org.example.dto.RateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NpbService {

  private static final String URL = "https://api.nbp.pl/api/exchangerates/rates/A/usd?format=json";

  @SneakyThrows
  public BigDecimal getUsdRate() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<ExchangeRatesSeriesDto> responseEntity = restTemplate.getForEntity(URL,
        ExchangeRatesSeriesDto.class);

    var response = responseEntity.getBody();

    return Optional.ofNullable(response)
        .map(ExchangeRatesSeriesDto::getRates)
        .orElseThrow()
        .stream()
        .findFirst()
        .map(RateDto::getMid)
        .orElseThrow();
  }
}
