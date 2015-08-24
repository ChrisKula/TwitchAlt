package com.caykah.android.twitchalt.pojos;

import android.graphics.Bitmap;

public class Game {
    private String name = "";
    private int id = 0;
    private int giantbombId = 0;
    private int viewersCount = 0;
    private int channelsCount = 0;
    private Bitmap logo;

    public Game(){

    }
    public Game(String name) {
        this.name = name;
    }

    public Game(String name, int id, int giantbombId) {
        this.name = name;
        this.id = id;
        this.giantbombId = giantbombId;
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
}
