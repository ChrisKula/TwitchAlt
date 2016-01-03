package com.caykah.android.twitchalt.tasks;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.VideoView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.pojos.GameStreamObject;
import com.iheartradio.m3u8.Encoding;
import com.iheartradio.m3u8.Format;
import com.iheartradio.m3u8.ParsingMode;
import com.iheartradio.m3u8.PlaylistParser;
import com.iheartradio.m3u8.data.TrackData;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchGameStreamPlaylistTask extends AsyncTask<String, Void, List<TrackData>> {
    private Activity activity;
    private Resources resources;

    public FetchGameStreamPlaylistTask(Activity activity) {
        this.activity = activity;
        this.resources = activity.getResources();
    }

    @Override
    protected List<TrackData> doInBackground(String... params) {
        List<TrackData> tracks = new ArrayList<>();
        InputStream in = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();


            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                String s = "Server returned HTTP " + httpURLConnection.getResponseCode()
                        + " " + httpURLConnection.getResponseMessage();
            }
            in = new BufferedInputStream(httpURLConnection.getInputStream());
            PlaylistParser parser = new PlaylistParser(in, Format.M3U, Encoding.UTF_8, ParsingMode.LENIENT);


//            Playlist playlist = parser.parse();
//            System.out.println(playlist.toString());


            tracks = parser.parse().getMediaPlaylist().getTracks();

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
        return tracks;
    }

    protected void onPostExecute(List<TrackData> tracks) {
        VideoView v = (VideoView) activity.findViewById(R.id.game_stream_video_player);
        GameStreamObject gameStream = new GameStreamObject();

        for (TrackData track : tracks) {
            String url = track.getLocation();
            if (url.contains("chunked")) {
                gameStream.setStreamSourceQualityURL(url);
            } else if (url.contains("high")) {
                gameStream.setStreamHighQualityURL(url);
            } else if (url.contains("medium")) {
                gameStream.setStreamMediumQualityURL(url);
            } else if (url.contains("low")) {
                gameStream.setStreamLowQualityURL(url);
            } else if (url.contains("mobile")) {
                gameStream.setStreamMobileQualityURL(url);
            } else if (url.contains("audio_only")) {
                gameStream.setStreamAudioOnlyURL(url);
            }
        }

        Uri uri = Uri.parse(gameStream.getStreamHighQualityURL());
        v.setVideoURI(uri);
        v.start();

        activity.findViewById(R.id.game_stream_video_player_progress_bar).setVisibility(View.INVISIBLE);
        v.setVisibility(View.VISIBLE);

    }
}
