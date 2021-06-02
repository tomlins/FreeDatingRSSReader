package com.ifreedating.freedatingrssreader.app;

import com.ifreedating.freedatingrssreader.rss.Feed;
import com.ifreedating.freedatingrssreader.rss.RSSFeedParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jason on 11/06/14.
 */
public class RSSDataService implements IDataService {

    private XmlPullParserFactory xmlFactoryObject;
    private Feed theFeed;

    @Override
    public Feed getData(String source) {

        try {
            // Set up the connection
            //
            URL url = new URL(source);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream stream = conn.getInputStream();

            // Get an XML Pull Parser object
            //
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlFactoryObject.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(stream, null);

            // Parse the feed with our bespoke parser
            //
            RSSFeedParser rssFeedParser = new RSSFeedParser();
            theFeed = rssFeedParser.parseXMLAndStoreIt(xmlPullParser);
            stream.close();
        }

        catch (Exception e) {
        }

        return theFeed;
    }
}
