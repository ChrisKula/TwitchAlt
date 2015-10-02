package com.caykah.android.twitchalt.tasks;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.GridView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.adapters.GameAdapter;
import com.caykah.android.twitchalt.adapters.GameStreamAdapter;
import com.caykah.android.twitchalt.pojos.Channel;
import com.caykah.android.twitchalt.pojos.Game;
import com.caykah.android.twitchalt.pojos.GameStreamObject;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FetchGameStreamsTask extends AsyncTask<String, Void, ArrayList<GameStreamObject>> {
    private Activity activity;
    private Resources resources;
    private String game;
    private GridView gv;


    public FetchGameStreamsTask(Activity activity, GridView gv, String game) {
        this.activity = activity;
        this.resources = activity.getResources();
        this.game = game;
        this.gv = gv;
    }

    @Override
    protected ArrayList<GameStreamObject> doInBackground(String... params) {
        ArrayList<GameStreamObject> GAME_STREAMS = new ArrayList<>();
        InputStream in = null;
        try {
            URL url = new URL(resources.getString(R.string.twitch_api_url_game_streams, game).replaceAll(" ", "%20"));
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            in = new BufferedInputStream(httpsURLConnection.getInputStream());
            String jsonData = IOUtils.toString(in, "UTF-8");

            JSONObject info = new JSONObject(jsonData);

            JSONArray gameStreams = info.getJSONArray("streams");

            for (int i = 0; i < gameStreams.length(); i++) {
                GameStreamObject gameStreamObject = new GameStreamObject();
                Channel channel = new Channel();
                JSONObject jsonStream = gameStreams.getJSONObject(i);
                JSONObject jsonChannel = jsonStream.getJSONObject("channel");

                channel.setBackground(jsonChannel.getString("background"));
                channel.setBanner(jsonChannel.getString("banner"));
                channel.setBroadcasterLanguage(jsonChannel.getString("broadcaster_language"));
                channel.setDisplayName(jsonChannel.getString("display_name"));
                channel.setGame(game);
                channel.setLogoLink(jsonChannel.getString("logo"));
                channel.setIsMature(Boolean.valueOf(jsonChannel.getString("mature")));
                channel.setStatus(jsonChannel.getString("status"));
                channel.setIsPartner(Boolean.valueOf(jsonChannel.getString("partner")));
                channel.setUrl(jsonChannel.getString("url"));
                channel.setVideoBannerLink(jsonChannel.getString("video_banner"));
                channel.setId(jsonChannel.getLong("_id"));
                channel.setName(jsonChannel.getString("name"));
//                channel.setCreatedAt(Date.valueOf(jsonChannel.getString("created_at")));
//                channel.setUpdatedAt(Date.valueOf(jsonChannel.getString("updated_at")));
//                channel.setDelay(Integer.valueOf(jsonChannel.getString("delay")));
                channel.setFollowersCount(jsonChannel.getLong("followers"));
                channel.setProfileBanner(jsonChannel.getString("profile_banner"));
                channel.setProfileBannerBackgroundColor(jsonChannel.getString("profile_banner_background_color"));
                channel.setViewsCount(jsonChannel.getLong("views"));
                channel.setLanguage(jsonChannel.getString("language"));

                gameStreamObject.setAverageFps(jsonStream.getDouble("average_fps"));
                gameStreamObject.setViewersCount(jsonStream.getInt("viewers"));
//                gameStreamObject.setCreatedAt(Date.valueOf(jsonStream.getString("created_at")));
                gameStreamObject.setId(jsonStream.getLong("_id"));
                gameStreamObject.setIsInAPlaylist(Boolean.valueOf(jsonStream.getString("is_playlist")));
                gameStreamObject.setVideoHeight(jsonStream.getInt("video_height"));
                gameStreamObject.setGame(game);
                gameStreamObject.setPreviewURL(jsonStream.getJSONObject("preview").getString("large"));
                gameStreamObject.setChannel(channel);


                GAME_STREAMS.add(gameStreamObject);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

        return GAME_STREAMS;
    }


    protected void onPostExecute(ArrayList<GameStreamObject> gameStreamObjects) {
        GameStreamAdapter adapter = new GameStreamAdapter(activity.getApplicationContext(), gameStreamObjects);
        gv.setAdapter(adapter);

//        for (int i = 0; i < adapter.getCount(); i++) {
//            new FetchTopGameLogoTask(adapter, i).execute(adapter.getItem(i).getLogoURL());
//        }
    }
}
