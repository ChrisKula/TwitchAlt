package com.caykah.android.twitchalt.tasks;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.GridView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.adapters.GameAdapter;
import com.caykah.android.twitchalt.pojos.Game;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class FetchTopGamesTask extends AsyncTask<String, Void, ArrayList<Game>> {
    private Activity activity;
    private Resources resources;
    private GridView gv;

    public FetchTopGamesTask(Activity activity, GridView gv) {
        this.activity = activity;
        this.resources = activity.getResources();
        this.gv = gv;
    }


    @Override
    protected ArrayList<Game> doInBackground(String... params) {
        ArrayList<Game> GAMES = new ArrayList<Game>();
        InputStream in = null;
        try {
            URL url = new URL(resources.getString(R.string.twitch_api_url_top_games));
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            in = new BufferedInputStream(httpsURLConnection.getInputStream());
            String jsonData = IOUtils.toString(in, "UTF-8");
            in.close();

            JSONObject info = new JSONObject(jsonData);
            JSONArray topGames = info.getJSONArray("top");

            for (int i = 0; i < topGames.length() && i < 9; i++) {
                Game game = new Game();
                JSONObject topGame = topGames.getJSONObject(i);

                game.setChannelsCount(topGame.getInt("channels"));
                game.setViewersCount(topGame.getInt("viewers"));

                topGame = topGame.getJSONObject("game");

                game.setLogoURL(topGame.getJSONObject("box").getString("large"));

                game.setName(topGame.getString("name"));
                game.setId(topGame.getInt("_id"));
                game.setGiantbombId(topGame.getInt("giantbomb_id"));

                GAMES.add(game);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return GAMES;
    }

    protected void onPostExecute(ArrayList<Game> games) {
        GameAdapter adapter = new GameAdapter(activity.getApplicationContext(), games);
        gv.setAdapter(adapter);

        for (int i = 0; i < adapter.getCount(); i++) {
            new FetchTopGameLogoTask(adapter, i).execute(adapter.getItem(i).getLogoURL());
        }
    }
}
