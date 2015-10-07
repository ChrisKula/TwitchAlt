package com.caykah.android.twitchalt.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.caykah.android.twitchalt.adapters.GameStreamsAdapter;
import com.caykah.android.twitchalt.adapters.TopGamesAdapter;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchGameStreamPreviewTask extends AsyncTask<String, Void, Bitmap> {
    private GameStreamsAdapter adapter;
    private int index = 0;

    public FetchGameStreamPreviewTask(GameStreamsAdapter adapter, int index) {
        this.adapter = adapter;
        this.index = index;
    }

    /**
     * params[0] contains the image URL
     */
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap gameStreamPreview = Bitmap.createBitmap(40, 40, Bitmap.Config.ALPHA_8);
        InputStream in = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(httpURLConnection.getInputStream());
            gameStreamPreview = BitmapFactory.decodeStream(in);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return gameStreamPreview;
    }

    protected void onPostExecute(Bitmap preview) {
        adapter.getItem(index).setPreview(preview);
        adapter.notifyDataSetChanged();
    }
}
