package org.example;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ExchangeApplication.class})
public class ExchangeApplication {

  public static void main(String[] args) {
    MDC.put("appName", "exchange-service");
    SpringApplication.run(ExchangeApplication.class, args);
  }
}