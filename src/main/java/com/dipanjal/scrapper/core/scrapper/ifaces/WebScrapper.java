package com.dipanjal.scrapper.core.scrapper.ifaces;

import java.io.IOException;
import java.util.function.Function;

public interface WebScrapper<T, R> {
    void scrap(String url) throws IOException;
    R scrap(String url, Function<T, R> mapFunction) throws IOException;
}
