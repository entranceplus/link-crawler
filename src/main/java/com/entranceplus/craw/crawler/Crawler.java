package com.entranceplus.craw.crawler;

/**
 *Crawler interface which contains methods for parsing of a
 * website...As of now it supports reddit
 */

public interface Crawler {
    /**
     * Get the top links from a subreddit
     * @param subreddit - The subreddit of the form Ex. soccer
     * @return JSON string of the form {'subreddit': [list of links corresponding to subreddit]}
     */
    public String getLinksFromReddit(String subreddit);
    public String getContent(String url);
    public String getHTML(String url);

    /**
     * Get the subreddits with corresponding followers and subreddit
     * @return JSON string of the form {'reddits':[{'followers':'344343','subreddit':"/r/politics/"}....]}
     */
    public String getSubreddits();
    public String parseJSON(String json, String keyword);
}