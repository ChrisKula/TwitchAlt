package com.caykah.android.twitchalt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.pojos.Game;
import com.caykah.android.twitchalt.tasks.FetchTopGamesTask;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_CLICKED_GAME = "CLICKED_GAMED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.top_games_progress_bar).setVisibility(View.VISIBLE);
        refreshTopGames();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_refresh:
                refreshTopGames();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void refreshTopGames() {
        GridView gv = (GridView) findViewById(R.id.grid_top_games);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game clickedGame = (Game) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), GameStreamsActivity.class);
                intent.putExtra(TAG_CLICKED_GAME, clickedGame.getName());
                startActivity(intent);

            }
        });

        new FetchTopGamesTask(this, gv).execute();
    }
}
