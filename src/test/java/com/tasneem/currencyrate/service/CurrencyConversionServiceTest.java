package com.tasneem.currencyrate.service;

import com.tasneem.currencyrate.dto.ConversionRequest;
import com.tasneem.currencyrate.dto.ConversionResponse;
import com.tasneem.currencyrate.model.ExchangeRate;
import com.tasneem.currencyrate.repository.ConversionRecordRepository;
import com.tasneem.currencyrate.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class CurrencyConversionServiceTest {
    @Test
    void convertsCurrencyUsingLatestRate() {
        ExchangeRateRepository exchangeRateRepository = Mockito.mock(ExchangeRateRepository.class);
        ConversionRecordRepository conversionRecordRepository = Mockito.mock(ConversionRecordRepository.class);

        Mockito.when(exchangeRateRepository.findFirstByBaseCurrencyAndTargetCurrencyAndRateDateOrderByCreatedAtDesc(
            "EUR", "USD", LocalDate.now()
        )).thenReturn(Optional.of(new ExchangeRate("EUR", "USD", new BigDecimal("1.10"), LocalDate.now())));

        ExchangeRateService exchangeRateService = new ExchangeRateService(exchangeRateRepository);
        CurrencyConversionService service = new CurrencyConversionService(exchangeRateService, conversionRecordRepository);

        ConversionResponse response = service.convert(new ConversionRequest("EUR", "USD", new BigDecimal("100")));

        assertThat(response.convertedAmount()).isEqualByComparingTo("110.00");
        Mockito.verify(conversionRecordRepository).save(any());
    }
}
