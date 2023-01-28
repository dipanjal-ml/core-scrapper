package com.dipanjal.scrapper.core;

import com.dipanjal.scrapper.core.scrapper.CurrencyExchangeScrapperImpl;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyExchangeScrapperImplTest {
    @Autowired
    private CurrencyExchangeScrapperImpl<BigDecimal> currencyExchangeScrapper;

    @Test
    void scrapUSD() throws IOException {
        BigDecimal rate = currencyExchangeScrapper.scrap(
            "https://www.google.com/finance/quote/USD-BDT", BigDecimal::new
        );
        Assertions.assertNotNull(rate);
    }
}
