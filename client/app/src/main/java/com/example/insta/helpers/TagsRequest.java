package com.example.insta.helpers;

import com.example.insta.model.Tag;

import java.util.List;

public class TagsRequest {
    private String id;
    private List<Tag> tags;

    public TagsRequest(String id, List<Tag> tags) {
        this.id = id;
        this.tags = tags;
    }
}
