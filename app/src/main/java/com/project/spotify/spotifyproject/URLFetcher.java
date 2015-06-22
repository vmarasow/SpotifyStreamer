package com.project.spotify.spotifyproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Tracks;

import static com.project.spotify.spotifyproject.Constants.ARTISTS_QUERY;
import static com.project.spotify.spotifyproject.Constants.QUERY_KEY;
import static com.project.spotify.spotifyproject.Constants.RESULT;
import static com.project.spotify.spotifyproject.Constants.TOP_TRACKS_QUERY;

/**
 * Created by Velvel on 6/16/2015.
 * <p/>
 * This is the updated Adapter that we are using to
 * dis
 */
public class URLFetcher extends AsyncTask<String, Void, HashMap> {

    Context mContext;
    ArtistAdapter mArtistAdapter = MainActivityFragment.mArtistAdapter;
    TopTracksAdapter mTopTracksAdapter = ArtistTopTracksFragment.mTopTracksAdapter;

    private String LOG_TAG = this.getClass().getSimpleName();

    public URLFetcher(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    protected HashMap doInBackground(String... params) {

        // TODO: using switch to select what type of query to make
        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();

        // TODO: Change return type to HashMap with the result returned from server, and the query type
        //       with a key, and pass all of it to the adapter and let the adapter deal with it
        HashMap<String, Object> wrapper = new HashMap<>();
        wrapper.put(QUERY_KEY, params[0]);

        switch (wrapper.get(QUERY_KEY).toString()) {
            case ARTISTS_QUERY: {
                ArtistsPager artistsPager = service.searchArtists(params[1]);

                wrapper.put(RESULT, artistsPager.artists.items);
                break;
            }
            case TOP_TRACKS_QUERY: {
                HashMap<String, Object> queryParams = new HashMap<>();
                queryParams.put(service.COUNTRY, "US");
                Tracks tracks = service.getArtistTopTrack(params[1], queryParams);

                wrapper.put(RESULT, tracks.tracks);
                break;
            }
        }
        return wrapper;
    }

    @Override
    protected void onPostExecute(HashMap wrapper) {
        super.onPostExecute(wrapper);

        switch (wrapper.get(QUERY_KEY).toString()) {
            case ARTISTS_QUERY: {
                // Get ArtistAdapter and pass in the List that we just created
                if (wrapper != null && !wrapper.isEmpty()) {
                    mArtistAdapter.clear();
                    for (Object entry : (List) wrapper.get(RESULT)) mArtistAdapter.add(entry);
                    mArtistAdapter.notifyDataSetChanged();
                } else if (wrapper.isEmpty() && wrapper != null) {
                    mArtistAdapter.clear();
                    Toast.makeText(mContext, "There is no such Artist.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case TOP_TRACKS_QUERY: {
                // Get mTopTracksAdapter and add the new data to it
                if (wrapper != null && !wrapper.isEmpty()) {
                    mTopTracksAdapter.clear();
                    for (Object entry : (List) wrapper.get(RESULT)) {
                        mTopTracksAdapter.add(entry);
                    }
                    mTopTracksAdapter.notifyDataSetChanged();
                } else if (wrapper.isEmpty() && wrapper != null) {
                    mTopTracksAdapter.clear();
                    Toast.makeText(mContext, "Error retrieving data.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
