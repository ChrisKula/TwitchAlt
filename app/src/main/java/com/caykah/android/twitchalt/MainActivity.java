package com.caykah.android.twitchalt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.caykah.android.twitchalt.adapters.GameAdapter;
import com.caykah.android.twitchalt.pojos.Game;
import com.caykah.android.twitchalt.tasks.FetchTopGamesTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Game> GAMES = new ArrayList<>();

        GAMES.add(new Game("League of Legends"));
        GAMES.add(new Game("DotA2"));
        GAMES.add(new Game("Heroes of the Storm"));

        FetchTopGamesTask fetchTopGamesTask = new FetchTopGamesTask(getApplicationContext(),(ListView) findViewById(R.id.list_games));
        fetchTopGamesTask.execute();


//        ListView lv = (ListView) findViewById(R.id.list_games);
//        GameAdapter adapter = new GameAdapter(getApplicationContext(), GAMES);
//        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
