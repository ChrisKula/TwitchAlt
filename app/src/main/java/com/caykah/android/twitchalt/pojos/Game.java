package com.caykah.android.twitchalt.pojos;

import android.graphics.Bitmap;

public class Game {
    private String name = "";
    private int id = 0;
    private int giantbombId = 0;
    private int viewersCount = 0;
    private int channelsCount = 0;
    private String logoURL = "";
    private Bitmap logo;

    public Game() {
    }

    public Game(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiantbombId() {
        return giantbombId;
    }

    public void setGiantbombId(int giantbombId) {
        this.giantbombId = giantbombId;
    }

    public int getViewersCount() {
        return viewersCount;
    }

    public void setViewersCount(int viewersCount) {
        this.viewersCount = viewersCount;
    }

    public int getChannelsCount() {
        return channelsCount;
    }

    public void setChannelsCount(int channelsCount) {
        this.channelsCount = channelsCount;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", giantbombId=" + giantbombId +
                ", viewersCount=" + viewersCount +
                ", channelsCount=" + channelsCount +
                ", logoURL='" + logoURL + '\'' +
                ", logo=" + logo +
                '}';
    }
}
