package com.ifreedating.freedatingrssreader.app;

/**
 * Created by jason on 11/06/14.
 */
public class DataServiceFactory {

    public static IDataService getRSSDataService() {
        return new RSSDataService();
    }

    //todo: Other methods returning specific services
}
