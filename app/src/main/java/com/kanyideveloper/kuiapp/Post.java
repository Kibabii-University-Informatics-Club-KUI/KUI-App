package com.kanyideveloper.kuiapp;

public class Post {
    private String postTitle;
    private String postDescription;
    private String ownerName;

    public Post() {
    }

    public Post(String postTitle, String postDescription, String ownerName) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.ownerName = ownerName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
