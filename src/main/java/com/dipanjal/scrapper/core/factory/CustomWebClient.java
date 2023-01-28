package com.dipanjal.scrapper.core.factory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class CustomWebClient extends WebClient {

    private transient static CustomWebClient INSTANCE;

    private CustomWebClient() {
    }

    private CustomWebClient(BrowserVersion browserVersion) {
        super(browserVersion);
    }

    private CustomWebClient(BrowserVersion browserVersion, String proxyHost, int proxyPort) {
        super(browserVersion, proxyHost, proxyPort);
    }

    private CustomWebClient(BrowserVersion browserVersion, boolean javaScriptEngineEnabled, String proxyHost, int proxyPort) {
        super(browserVersion, javaScriptEngineEnabled, proxyHost, proxyPort);
    }

    private CustomWebClient(BrowserVersion browserVersion, boolean javaScriptEngineEnabled) {
        super(browserVersion, javaScriptEngineEnabled, null, 0);
    }

    public synchronized static CustomWebClient getInstance(){
        return INSTANCE == null ? new CustomWebClient() : INSTANCE;
    }

    public synchronized static CustomWebClient getInstance(BrowserVersion browserVersion){
        return INSTANCE == null ? new CustomWebClient(browserVersion) : INSTANCE;
    }

    public synchronized static CustomWebClient getInstance(BrowserVersion browserVersion, String proxyHost, int proxyPort){
        return INSTANCE == null ? new CustomWebClient(browserVersion, proxyHost, proxyPort) : INSTANCE;
    }

    public synchronized static CustomWebClient getInstance(BrowserVersion browserVersion, boolean javaScriptEngineEnabled, String proxyHost, int proxyPort){
        return INSTANCE == null ? new CustomWebClient(browserVersion, javaScriptEngineEnabled, proxyHost, proxyPort) : INSTANCE;
    }

    public synchronized static CustomWebClient getInstance(BrowserVersion browserVersion, boolean javaScriptEngineEnabled){
        return INSTANCE == null ? new CustomWebClient(browserVersion, javaScriptEngineEnabled) : INSTANCE;
    }

    /** returns new object everytime */
    public synchronized static CustomWebClient createInstance(BrowserVersion browserVersion, boolean javaScriptEngineEnabled){
        return new CustomWebClient(browserVersion, javaScriptEngineEnabled);
    }

    /** some setter getter anti-patterns */
    public CustomWebClient setCssEnabled(boolean cssEnabled){
        super.getOptions().setCssEnabled(cssEnabled); return this;
    }

    /** some setter getter anti-patterns */
    public CustomWebClient setConnectionTimeOut(int timeoutInMillis){
        super.getOptions().setTimeout(timeoutInMillis); return this;
    }

}
