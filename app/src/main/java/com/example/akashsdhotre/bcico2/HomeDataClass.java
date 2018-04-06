package com.example.akashsdhotre.bcico2;

/**
 * Created by user on 3/4/18.
 */

public class HomeDataClass {

    private String profileImgUrl;
    private String userName;
    private String postDate;
    private String postText;
    private String imageUrl;


    public HomeDataClass(String profileImgUrl, String userName, String postDate, String postText, String imageUrl) {
        this.profileImgUrl = profileImgUrl;
        this.userName = userName;
        this.postDate = postDate;
        this.postText = postText;
        this.imageUrl = imageUrl;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
