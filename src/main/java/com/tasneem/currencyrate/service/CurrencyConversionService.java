package com.tasneem.currencyrate.service;

import com.tasneem.currencyrate.dto.ConversionRequest;
import com.tasneem.currencyrate.dto.ConversionResponse;
import com.tasneem.currencyrate.dto.ExchangeRateResponse;
import com.tasneem.currencyrate.model.ConversionRecord;
import com.tasneem.currencyrate.repository.ConversionRecordRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyConversionService {
    private final ExchangeRateService exchangeRateService;
    private final ConversionRecordRepository conversionRecordRepository;

    public CurrencyConversionService(ExchangeRateService exchangeRateService,
                                     ConversionRecordRepository conversionRecordRepository) {
        this.exchangeRateService = exchangeRateService;
        this.conversionRecordRepository = conversionRecordRepository;
    }

    public ConversionResponse convert(ConversionRequest request) {
        ExchangeRateResponse rate = exchangeRateService.getLatestRate(request.sourceCurrency(), request.targetCurrency());

        BigDecimal convertedAmount = request.amount()
            .multiply(rate.rate())
            .setScale(2, RoundingMode.HALF_UP);

        conversionRecordRepository.save(new ConversionRecord(
            rate.baseCurrency(), rate.targetCurrency(), request.amount(), convertedAmount, rate.rate()));

        return new ConversionResponse(
            rate.baseCurrency(), rate.targetCurrency(), request.amount(), convertedAmount, rate.rate(), rate.rateDate());
    }
}
