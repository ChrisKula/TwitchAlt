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
import com.caykah.android.twitchalt.pojos.GameStreamObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GameStreamsAdapter extends ArrayAdapter<GameStreamObject> {

   private Bitmap previewPlacholeder;
    private Context context;
    private int resource;
    private ArrayList<GameStreamObject> data;

    public GameStreamsAdapter(Context context, ArrayList<GameStreamObject> data) {
        super(context, R.layout.grid_item_game_stream, data);
        this.context = context;
        this.resource = R.layout.grid_item_game_stream;
        this.data = data;

       this.previewPlacholeder =  BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_game_stream_preview);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // assign the view we are converting to a local variable
        View v = convertView;
        // if it's not recycled, initialize some attributes

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, null);
        }

        GameStreamObject gameStreamObject = data.get(position);

        if (gameStreamObject != null) {
            DecimalFormat viewersCountFormatter = new DecimalFormat("###,###,###");

            ((TextView) v.findViewById(R.id.game_stream_user_name)).setText(gameStreamObject.getChannel().getDisplayName());
            ((TextView) v.findViewById(R.id.game_stream_description)).setText(gameStreamObject.getChannel().getStatus());
            ((TextView) v.findViewById(R.id.game_stream_viewers_count)).setText(context.getResources().getString(R.string.viewers, viewersCountFormatter.format(gameStreamObject.getViewersCount())));

            if (gameStreamObject.getPreview() == null) {
                ((ImageView) v.findViewById(R.id.game_stream_preview)).setImageBitmap(previewPlacholeder);
            } else {
                ((ImageView) v.findViewById(R.id.game_stream_preview)).setImageBitmap(gameStreamObject.getPreview());
            }
        }

        // the view must be returned to our activity
        return v;
    }

    public GameStreamObject getItem(int position) {
        return data.get(position);
    }
}
