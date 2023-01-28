package com.dipanjal.scrapper.core.scrapper;


import com.dipanjal.scrapper.core.scrapper.ifaces.CurrencyExchangeScrapper;
import java.io.IOException;
import java.util.function.Function;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * @author dipanjal
 * @since 5/17/2021
 */
@Slf4j
@Service("currencyExchangeScrapper")
public class CurrencyExchangeScrapperImpl<R> implements CurrencyExchangeScrapper<R> {

    @Override
    public void scrap(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        Document document = connection.get();
        Elements elements = document.select("div");
        elements.stream()
                .filter(e -> StringUtils.isNotBlank(e.text()))
                .filter(e -> Pattern.matches("\\d+\\.?\\d{4}", e.text()))
                .findFirst()
                .map(Element::text);
    }

    @Override
    public R scrap(String url, Function<String, R> mapFunction) throws IOException {
        Connection connection = Jsoup.connect(url);
        Document document = connection.get();
        Elements elements = document.select("div");
        String value = elements.stream()
                .filter(e -> StringUtils.isNotBlank(e.text()))
                .filter(e -> Pattern.matches("\\d+\\.?\\d{4}", e.text()))
                .findFirst()
                .map(Element::text)
                .orElse("0.0000");
        return mapFunction.apply(value);
    }
}
