package com.example.guest.posterpal.models;

import java.security.Timestamp;
import java.util.Locale;

/**
 * Created by Guest on 12/6/16.
 */
public class Post {
    private String content;
    private String user;
    private String timestamp;
    private String imageUrl;

    public Post(String content, String user, String timestamp, String imageUrl) {
        this.content = content;
        this.user = user;
        this.timestamp = timestamp;
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return this.content;
    }

    public String getUser() {
        return this.user;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
