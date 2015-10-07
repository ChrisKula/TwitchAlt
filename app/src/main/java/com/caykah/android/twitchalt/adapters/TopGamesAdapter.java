package com.caykah.android.twitchalt.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.pojos.Game;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TopGamesAdapter extends ArrayAdapter<Game> {

    private Context context;
    private int resource;
    private ArrayList<Game> data;
    private Bitmap topGameLogoPlacholder;

    public TopGamesAdapter(Context context, ArrayList<Game> data) {
        super(context, R.layout.grid_item_game, data);
        this.context = context;
        this.resource = R.layout.grid_item_game;
        this.data = data;
        this.topGameLogoPlacholder = BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_game_box);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, null);
        }
        Game game = data.get(position);

        if (game != null) {
            DecimalFormat viewersCountFormatter = new DecimalFormat("###,###,###");

            ((TextView) v.findViewById(R.id.top_game_name)).setText(game.getName());
            ((TextView) v.findViewById(R.id.top_game_viewers_count)).setText(context.getResources().getString(R.string.viewers, viewersCountFormatter.format(game.getViewersCount())));

            if (game.getLogo() == null) {
                ((ImageView) v.findViewById(R.id.top_game_logo)).setImageBitmap(topGameLogoPlacholder);
            } else {
                ((ImageView) v.findViewById(R.id.top_game_logo)).setImageBitmap(game.getLogo());
            }
        }
        // the view must be returned to our activity
        return v;
    }

    public Game getItem(int position) {
        return data.get(position);
    }
}
