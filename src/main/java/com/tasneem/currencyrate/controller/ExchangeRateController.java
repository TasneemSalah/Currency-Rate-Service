package com.tasneem.currencyrate.controller;

import com.tasneem.currencyrate.dto.ExchangeRateResponse;
import com.tasneem.currencyrate.service.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rates")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/{baseCurrency}/{targetCurrency}")
    public ExchangeRateResponse getLatestRate(@PathVariable String baseCurrency, @PathVariable String targetCurrency) {
        return exchangeRateService.getLatestRate(baseCurrency, targetCurrency);
    }
}
