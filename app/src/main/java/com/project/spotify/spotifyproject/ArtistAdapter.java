package com.project.spotify.spotifyproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Image;

/**
 * Created by Velvel on 6/16/2015.
 * <p/>
 * This is the updated Adapter that we are using to
 * dis
 */
public class ArtistAdapter extends ArrayAdapter{

    Context context = getContext();
    List objects;
    public ArtistAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.artist_search_result, null);
        }

        // Get the items from the objects array and set up each view and return it
        ArtistListEntry entry = (ArtistListEntry) objects.get(position);
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(entry.getName());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        String url;
        Image[] imgs;
        if (entry.getImg().length == 1) {
            imgs = entry.getImg();
            url = imgs[0].url;
            Picasso.with(context).load(url).into(imageView);
        } else if (entry.getImg().length > 1) {
            imgs = entry.getImg();
            url = imgs[imgs.length - 2].url;
            Picasso.with(context).load(url).into(imageView);
        } else {
            Picasso.with(context).load(R.drawable.default_album_art_big_card).resize(160, 160).into(imageView);
        }
        return convertView;
    }
}
