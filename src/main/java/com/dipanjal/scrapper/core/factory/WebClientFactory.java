package com.dipanjal.scrapper.core.factory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;

public class WebClientFactory {
    public static final BrowserVersion DEFAULT_BROWSER_VERSION;
    public static final boolean DEFAULT_JAVASCRIPT_ENABLED;
    public static final boolean DEFAULT_CSS_ENABLED;
    public static final int DEFAULT_CONNECTION_TIMEOUT;
    public static final int DEFAULT_AJAX_CONTROLLER_DELAY;

    static {
        DEFAULT_BROWSER_VERSION = BrowserVersion.CHROME;
        DEFAULT_JAVASCRIPT_ENABLED = true;
        DEFAULT_CSS_ENABLED = false;
        DEFAULT_CONNECTION_TIMEOUT = 40000;
        DEFAULT_AJAX_CONTROLLER_DELAY = 5000;
    }

    public static CustomWebClient getDefaultWebClient(){
        return CustomWebClient
                .getInstance(DEFAULT_BROWSER_VERSION, DEFAULT_JAVASCRIPT_ENABLED)
                .setCssEnabled(DEFAULT_CSS_ENABLED)
                .setConnectionTimeOut(DEFAULT_CONNECTION_TIMEOUT);
    }

    public static CustomWebClient getAjaxWebClient(long delay){
        CustomWebClient webClient = getDefaultWebClient();
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScriptStartingBefore(delay);
        return webClient;
    }

    public static CustomWebClient getDefaultAjaxWebClient(){
        return getAjaxWebClient(DEFAULT_AJAX_CONTROLLER_DELAY);
    }
}
