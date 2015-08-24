package com.caykah.android.twitchalt.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.caykah.android.twitchalt.R;
import com.caykah.android.twitchalt.pojos.Game;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<Game> {

    private Context context;
    private int resource;
    private ArrayList<Game> data;

    public GameAdapter(Context context, ArrayList<Game> data) {
        super(context, R.layout.list_item_game, data);
        this.context = context;
        this.resource = R.layout.list_item_game;
        this.data = data;
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
            ((TextView) v.findViewById(R.id.game_name)).setText(game.getName());

            DecimalFormat myFormatter = new DecimalFormat("###,###,###");


            ((TextView) v.findViewById(R.id.viewers_count)).setText(myFormatter.format(game.getViewersCount()) + " " + context.getResources().getString(R.string.viewers));
        }

        // the view must be returned to our activity
        return v;
    }

}
