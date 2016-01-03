package com.caykah.android.twitchalt.pojos;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameStreamObject {

    private long id = 0l;
    private String game = new String();
    private int viewersCount = 0;
    private Date createdAt = new Date();
    private int videoHeight = 0;
    private double averageFps = 0;
    private boolean isInAPlaylist = false;
    private Channel channel = new Channel();
    private String previewURL = new String();
    private Bitmap preview;

    private String streamSourceQualityURL;
    private String streamHighQualityURL;
    private String streamMediumQualityURL;
    private String streamLowQualityURL;
    private String streamMobileQualityURL;
    private String streamAudioOnlyURL;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getViewersCount() {
        return viewersCount;
    }

    public void setViewersCount(int viewersCount) {
        this.viewersCount = viewersCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public double getAverageFps() {
        return averageFps;
    }

    public void setAverageFps(double averageFps) {
        this.averageFps = averageFps;
    }

    public boolean isInAPlaylist() {
        return isInAPlaylist;
    }

    public void setIsInAPlaylist(boolean isInAPlaylist) {
        this.isInAPlaylist = isInAPlaylist;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public Bitmap getPreview() {
        return preview;
    }

    public void setPreview(Bitmap preview) {
        this.preview = preview;
    }

    public String getStreamSourceQualityURL() {
        if (this.streamSourceQualityURL == null) {
            return getStreamHighQualityURL();
        } else {
            return streamSourceQualityURL;
        }
    }

    public void setStreamSourceQualityURL(String streamSourceQualityURL) {
        this.streamSourceQualityURL = streamSourceQualityURL;
    }

    public String getStreamHighQualityURL() {
        if (this.streamHighQualityURL == null) {
            return getStreamMediumQualityURL();
        } else {
            return streamHighQualityURL;
        }
    }

    public void setStreamHighQualityURL(String streamHighQualityURL) {
        this.streamHighQualityURL = streamHighQualityURL;
    }

    public String getStreamMediumQualityURL() {
        if (this.streamMediumQualityURL == null) {
            return getStreamLowQualityURL();
        } else {
            return streamMediumQualityURL;
        }
    }

    public void setStreamMediumQualityURL(String streamMediumQualityURL) {
        this.streamMediumQualityURL = streamMediumQualityURL;
    }

    public String getStreamLowQualityURL() {
        if (this.streamLowQualityURL == null) {
            return getStreamMobileQualityURL();
        } else {
            return streamLowQualityURL;
        }
    }

    public void setStreamLowQualityURL(String streamLowQualityURL) {
        this.streamLowQualityURL = streamLowQualityURL;
    }

    public String getStreamMobileQualityURL() {
        if (this.streamMobileQualityURL == null) {
            return getStreamAudioOnlyURL();
        } else {
            return streamSourceQualityURL;
        }
    }

    public void setStreamMobileQualityURL(String streamMobileQualityURL) {
        this.streamMobileQualityURL = streamMobileQualityURL;
    }

    public String getStreamAudioOnlyURL() {
        return streamAudioOnlyURL;
    }

    public void setStreamAudioOnlyURL(String streamAudioOnlyURL) {
        this.streamAudioOnlyURL = streamAudioOnlyURL;
    }

    public List<String> getAllStreamURLs() {
        List<String> urls = new ArrayList<>();

        urls.add(this.streamSourceQualityURL);
        urls.add(this.streamHighQualityURL);
        urls.add(this.streamMediumQualityURL);
        urls.add(this.streamLowQualityURL);
        urls.add(this.streamMobileQualityURL);
        urls.add(this.streamAudioOnlyURL);

        return urls;
    }

    @Override
    public String toString() {
        return "GameStreamObject{" +
                "id=" + id +
                ", game='" + game + '\'' +
                ", viewersCount=" + viewersCount +
                ", createdAt=" + createdAt +
                ", videoHeight=" + videoHeight +
                ", averageFps=" + averageFps +
                ", isInAPlaylist=" + isInAPlaylist +
                ", channel=" + channel +
                ", previewURL='" + previewURL + '\'' +
                ", preview=" + preview +
                '}';
    }
}
