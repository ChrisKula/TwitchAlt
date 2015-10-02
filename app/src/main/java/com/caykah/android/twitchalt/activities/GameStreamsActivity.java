package com.caykah.android.twitchalt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.tasks.FetchGameStreamsTask;

public class GameStreamsActivity extends AppCompatActivity {
    private String clickedGame = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clickedGame = getIntent().getStringExtra(MainActivity.TAG_CLICKED_GAME);
        setContentView(R.layout.activity_game_streams);
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshStreams(clickedGame);
    }

    private void refreshStreams(String clickedGame) {
        GridView gv = (GridView) findViewById(R.id.grid_game_streams);

        new FetchGameStreamsTask(this, gv, clickedGame).execute();
    }
}
