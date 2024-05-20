package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ExchangeDto;
import org.example.service.ExchangeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "exchange")
@RequiredArgsConstructor
public class ExchangeController {

  private final ExchangeService exchangeService;

  @PostMapping(path = "plnToUsd")
  public void exchangePlnToUsd(@RequestBody ExchangeDto exchangeDto) {
    exchangeService.exchangePlnToUsd(exchangeDto);
  }

  @PostMapping(path = "usdToPln")
  public void exchangeUsdToPln(@RequestBody ExchangeDto exchangeDto) {
    exchangeService.exchangeUsdToPln(exchangeDto);
  }
}
