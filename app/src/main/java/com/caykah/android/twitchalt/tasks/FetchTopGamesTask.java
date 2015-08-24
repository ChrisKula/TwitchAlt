package com.caykah.android.twitchalt.tasks;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.adapters.GameAdapter;
import com.caykah.android.twitchalt.pojos.Game;
import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Chris on 23/08/2015.
 */
public class FetchTopGamesTask extends AsyncTask<String, Void, ArrayList<Game>> {
    private Context context;
    private Resources resources;
    private ListView lv;

    public FetchTopGamesTask(Context context, ListView lv) {
        this.context = context;
        this.resources = context.getResources();
        this.lv = lv;
    }


    @Override
    protected ArrayList<Game> doInBackground(String... params) {
        ArrayList<Game> GAMES = new ArrayList<Game>();
        try {
            URL url = new URL(resources.getString(R.string.twitch_api_url_top_games));
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
            String jsonData = IOUtils.toString(in, "UTF-8");
            in.close();

            JSONObject info = new JSONObject(jsonData);
            JSONArray topGames = info.getJSONArray("top");

            for (int i = 0; i < topGames.length(); i++) {
                Game game = new Game();

                JSONObject topGame = topGames.getJSONObject(i);

                game.setChannelsCount(topGame.getInt("channels"));
                game.setViewersCount(topGame.getInt("viewers"));

                topGame = topGame.getJSONObject("game");

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
            e.printStackTrace();
        }
        return GAMES;
    }

    protected void onPostExecute(ArrayList<Game> games) {
        GameAdapter adapter = new GameAdapter(context, games);
        lv.setAdapter(adapter);
    }
}
