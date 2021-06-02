package com.ifreedating.freedatingrssreader.rss;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

public class RSSFeedParser {

    public static final String ITEM = "item";
    public static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String DESCRIPTION = "description";
    private static final String AUTHOR = "author";
    private static final String GUID = "guid";

    private Feed theFeed;

    public RSSFeedParser() {
        theFeed = new Feed();
    }

    public Feed parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String payload=null;
        FeedItem feedMessage = null;

        try {
            event = myParser.getEventType();
            boolean inItem = false;

            while (event != XmlPullParser.END_DOCUMENT) {

                String name=myParser.getName();
                
                switch (event) {

                    case XmlPullParser.START_TAG:
                        if(name.equals("item")) {
                            feedMessage = new FeedItem();
                            inItem = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        payload = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(inItem) {
                            if (name.equals(TITLE)) {
                                feedMessage.setTitle(payload);
                            } else if (name.equals(LINK)) {
                                feedMessage.setLink(payload);
                            } else if (name.equals(DESCRIPTION)) {
                                feedMessage.setDescription(payload);
                            } else if (name.equals(AUTHOR)) {
                                feedMessage.setAuthor(payload);
                            } else if (name.equals(GUID)) {
                                Log.d("PARSER", "got image name '" + payload + "'");
                                feedMessage.setGuid(payload);
                            } else if (name.equals(ITEM)) {
                                theFeed.getItems().add(feedMessage);
                                inItem = false;
                            }
                            break;
                        }
                }
                event = myParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return theFeed;
    }

}