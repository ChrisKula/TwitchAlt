package com.caykah.android.twitchalt.pojos;

public class AccessToken {
    private String channelName = new String();
    private String token = new String();
    private String sig = new String();
    private final int randomNumber = (int) (Math.random() * 100000);


    public AccessToken() {
    }

    public AccessToken(String token, String sig) {
        this.token = token;
        this.sig = sig;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName.toLowerCase();
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "channelName='" + channelName + '\'' +
                ", token='" + token + '\'' +
                ", sig='" + sig + '\'' +
                ", randomNumber=" + randomNumber +
                '}';
    }
}
