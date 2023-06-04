package com.example.insta.model;

import java.util.List;

public class Photo {
    private long id;
    private String album;
    private String originalName;
    private String url;
    private String lastChange;
    private List<Tag> tags;
    private List<HistoryItem> history;
    private String address;

    public Photo() {
    }

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", album='" + album + '\'' +
                ", originalName='" + originalName + '\'' +
                ", url='" + url + '\'' +
                ", lastChange='" + lastChange + '\'' +
                ", tags=" + tags +
                ", history=" + history +
                ", address='" + address + '\'' +
                '}';
    }
}
