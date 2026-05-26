package com.tasneem.currencyrate.controller;

import com.tasneem.currencyrate.dto.ConversionRequest;
import com.tasneem.currencyrate.dto.ConversionResponse;
import com.tasneem.currencyrate.service.CurrencyConversionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conversions")
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @PostMapping
    public ConversionResponse convert(@Valid @RequestBody ConversionRequest request) {
        return currencyConversionService.convert(request);
    }
}
