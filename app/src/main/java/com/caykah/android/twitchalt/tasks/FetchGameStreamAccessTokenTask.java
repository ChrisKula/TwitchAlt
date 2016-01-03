package com.caykah.android.twitchalt.tasks;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.pojos.AccessToken;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchGameStreamAccessTokenTask extends AsyncTask<String, Void, AccessToken> {
    private Activity activity;
    private Resources resources;
    private String channelName = "";

    public FetchGameStreamAccessTokenTask(Activity activity, String channelName) {
        this.activity = activity;
        this.resources = activity.getResources();
        this.channelName = channelName;
    }

    @Override
    protected AccessToken doInBackground(String... params) {

        AccessToken accessToken = new AccessToken();
        InputStream in = null;
        try {
            URL url = new URL(resources.getString(R.string.twitch_api_url_get_access_token, channelName));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(httpURLConnection.getInputStream());
            String data = IOUtils.toString(in, "UTF-8");

            JSONObject jsonData = new JSONObject(data);

            accessToken.setChannelName(channelName);
            accessToken.setToken(jsonData.getString("token"));
            accessToken.setSig(jsonData.getString("sig"));


        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return accessToken;
    }

    protected void onPostExecute(AccessToken accessToken) {
        String m3u8URL = resources.getString(R.string.twitch_api_url_get_m3u8, accessToken.getChannelName(), accessToken.getToken(), accessToken.getSig(), accessToken.getRandomNumber());
//        System.out.println(m3u8URL);
        new FetchGameStreamPlaylistTask(activity).execute(m3u8URL);
    }
}
