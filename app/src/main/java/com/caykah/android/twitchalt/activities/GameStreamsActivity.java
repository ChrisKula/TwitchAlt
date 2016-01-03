package com.caykah.android.twitchalt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.pojos.GameStreamObject;
import com.caykah.android.twitchalt.tasks.FetchGameStreamsTask;

public class GameStreamsActivity extends AppCompatActivity {
    private String clickedGame = new String();
    public final static String TAG_CLICKED_STREAM = "CLICKED_STREAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickedGame = getIntent().getStringExtra(MainActivity.TAG_CLICKED_GAME);

        if (clickedGame != null) {
            setTitle(clickedGame);
        }

        setContentView(R.layout.activity_game_streams);
    }


    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.game_streams_progress_bar).setVisibility(View.VISIBLE);
        refreshStreams(clickedGame);
    }

    private void refreshStreams(String clickedGame) {
        GridView gv = (GridView) findViewById(R.id.grid_game_streams);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channelName = ((GameStreamObject) parent.getAdapter().getItem(position)).getChannel().getDisplayName().replaceAll(" ", "");
                Intent intent = new Intent(getApplicationContext(), StreamPlayerActivity.class);
                intent.putExtra(TAG_CLICKED_STREAM, channelName);
                startActivity(intent);
            }
        });

        new FetchGameStreamsTask(this, gv, clickedGame).execute();
    }
}
