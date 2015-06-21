package com.project.spotify.spotifyproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistTopTracksFragment extends Fragment {

    static TopTracksAdapter mTopTracksAdapter;

    public ArtistTopTracksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_artist_top_tracks, container, false);

        // Get ListView and set adapter to it
        ListView listView = (ListView) rootview.findViewById(R.id.listView2);
        mTopTracksAdapter = new TopTracksAdapter(getActivity(), R.layout.top_track_entry, new ArrayList());
        listView.setAdapter(mTopTracksAdapter);

        return rootview;
    }
}
