package com.example.demo.dto;

public class SearchJobRequest {

    private String keywords ;
    private String location;

    public SearchJobRequest(String keywords, String location){
        this.keywords= keywords;
        this.location= location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
