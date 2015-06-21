package com.project.spotify.spotifyproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Velvel on 6/17/2015.
 * <p/>
 * This is the updated Adapter that we are using to
 * dis
 */
public class TopTracksAdapter extends ArrayAdapter {

    private final String LOG_TAG = getClass().getSimpleName();

    List objects;
    Context mContext;


    public TopTracksAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Make sure convertView is inflated
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.top_track_entry, null);
        }

        // Get the data from the objects list and fill the convert view with them
        if (!objects.isEmpty()) {
            Log.d(LOG_TAG, objects.get(0).toString());
        }
        return convertView;
    }
}
