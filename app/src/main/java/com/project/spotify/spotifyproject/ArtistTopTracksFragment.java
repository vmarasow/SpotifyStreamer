package com.project.spotify.spotifyproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;

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

        // Get intent and retrieve data, the data is the artist id needed to query spotify
        Intent intent = getActivity().getIntent();
        String artistID = "";
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            artistID = intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        // Get ListView and set adapter to it
        ListView listView = (ListView) rootview.findViewById(R.id.listView2);
        mTopTracksAdapter = new TopTracksAdapter(getActivity(), R.layout.top_track_entry, new ArrayList<Track>());
        listView.setAdapter(mTopTracksAdapter);

        // Now get the data from Spotify and load it in the adapter
        new URLFetcher(getActivity()).execute(Constants.TOP_TRACKS_QUERY, artistID);

        return rootview;
    }
}
