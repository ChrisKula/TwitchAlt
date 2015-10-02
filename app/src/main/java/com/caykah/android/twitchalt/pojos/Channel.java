package com.caykah.android.twitchalt.pojos;

import java.net.URL;
import java.util.Date;

/**
 * Created by Chris on 02/10/2015.
 */
public class Channel {

    private String apiLink = new String();
    private String background = new String();
    private String banner = new String();
    private String broadcasterLanguage = new String();
    private String displayName = new String();

    private String game = new String();
    private String logoLink = new String();
    private boolean isMature = false;
    private String status = new String();
    private boolean isPartner = false;
    private String url = new String();
    private String videoBannerLink = new String();
    private long id = 0l;
    private String name = new String();
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private int delay = 0;
    private long followersCount = 0l;
    private String profileBanner = new String();
    private String profileBannerBackgroundColor = new String();
    private long viewsCount = 0l;
    private String language = new String();

    public String getApiLink() {
        return apiLink;
    }

    public void setApiLink(String apiLink) {
        this.apiLink = apiLink;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBroadcasterLanguage() {
        return broadcasterLanguage;
    }

    public void setBroadcasterLanguage(String broadcasterLanguage) {
        this.broadcasterLanguage = broadcasterLanguage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public boolean isMature() {
        return isMature;
    }

    public void setIsMature(boolean isMature) {
        this.isMature = isMature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPartner() {
        return isPartner;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoBannerLink() {
        return videoBannerLink;
    }

    public void setVideoBannerLink(String videoBannerLink) {
        this.videoBannerLink = videoBannerLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public String getProfileBannerBackgroundColor() {
        return profileBannerBackgroundColor;
    }

    public void setProfileBannerBackgroundColor(String profileBannerBackgroundColor) {
        this.profileBannerBackgroundColor = profileBannerBackgroundColor;
    }

    public long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "apiLink='" + apiLink + '\'' +
                ", background='" + background + '\'' +
                ", banner='" + banner + '\'' +
                ", broadcasterLanguage='" + broadcasterLanguage + '\'' +
                ", displayName='" + displayName + '\'' +
                ", game=" + game +
                ", logoLink='" + logoLink + '\'' +
                ", isMature=" + isMature +
                ", status='" + status + '\'' +
                ", isPartner=" + isPartner +
                ", url='" + url + '\'' +
                ", videoBannerLink='" + videoBannerLink + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", delay=" + delay +
                ", followersCount=" + followersCount +
                ", profileBanner='" + profileBanner + '\'' +
                ", profileBannerBackgroundColor='" + profileBannerBackgroundColor + '\'' +
                ", viewsCount=" + viewsCount +
                ", language='" + language + '\'' +
                '}';
    }
}
