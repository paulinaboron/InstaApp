package com.example.insta.model;

import com.example.insta.helpers.HistoryItem;
import com.example.insta.helpers.Tag;

import java.util.List;

public class Photo {
    private int id;
    private String album;
    private String originalName;
    private String url;
    private String lastChange;
    private List<Tag> tags;
    private List<HistoryItem> history;

    public Photo() {
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
                '}';
    }
}
