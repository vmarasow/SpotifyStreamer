package com.project.spotify.spotifyproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private EditText editText;
    public static ArtistAdapter mArtistAdapter;

    private String LOG_TAG = getClass().getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.artist_search_activity, container, false);

        // Get ahold of the ListView and Set up a Adapter to work with it
        mArtistAdapter = new ArtistAdapter(getActivity(), R.layout.artist_search_result, new ArrayList<ArtistListEntry>());
        ListView listView = (ListView) rootview.findViewById(R.id.listView);
        listView.setAdapter(mArtistAdapter);

        // Set up click listener for the listview that should lead into the sacond activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArtistListEntry entry = (ArtistListEntry) parent.getAdapter().getItem(position);
                // Now that we have the data of the item clicked on pass it to the next activity.
                Intent intent = new Intent(getActivity(), ArtistTopTracks.class);
                intent.putExtra(Intent.EXTRA_TEXT, entry.getId());
                startActivity(intent);
            }
        });

        final TextView textView = (TextView) rootview.findViewById(R.id.artist_search_textview);
        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Send Query to spotify api
                String artistName = v.getText().toString();
                new URLFetcher(getActivity()).execute(Constants.ARTISTS_QUERY, artistName);
                return false;
            }
        });
        return rootview;
    }
}
