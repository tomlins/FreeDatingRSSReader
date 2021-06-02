/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * The follow code is copyright of the author, Jason Tomlins
 */
package com.ifreedating.freedatingrssreader.rss;

/**
 *
 * @author jason
 */
/*
 * Represents one RSS message
 */
public class FeedItem {

    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + getTitle() + ", description=" + getDescription()
                + ", link=" + getLink() + ", author=" + getAuthor() + ", guid=" + getGuid()
                + "]";
    }

}
