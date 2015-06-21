package com.project.spotify.spotifyproject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;

import static com.project.spotify.spotifyproject.Constants.ARTISTS_QUERY;
import static com.project.spotify.spotifyproject.Constants.TOP_TRACKS_QUERY;

/**
 * Created by Velvel on 6/16/2015.
 * <p/>
 * This is the updated Adapter that we are using to
 * dis
 */
public class URLFetcher extends AsyncTask<String, Void, ArrayList<ArtistListEntry>> {

    Context mContext;
    ArtistAdapter mArtistAdapter = MainActivityFragment.mArtistAdapter;
    TopTracksAdapter mTopTracksAdapter = ArtistTopTracksFragment.mTopTracksAdapter;

    private String LOG_TAG = this.getClass().getSimpleName();

    public URLFetcher(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    protected ArrayList doInBackground(String... params) {

        // TODO: using switch to select what type of query to make
        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();

        ArrayList data = new ArrayList();
        // Pass in the constant for the type of data to the data arraylist so we can access it in the
        // onPostExecute.
        data.add(params[0]);
        Log.d(LOG_TAG, data.get(0).toString());

        switch (data.get(0).toString()) {
            case ARTISTS_QUERY: {
                 ArtistsPager artistsPager = service.searchArtists(params[1]);

                // Parse data and store it in the data ArrayList
                for (Artist artist : artistsPager.artists.items) {

                    // TODO: On refactor place the code to get the right image and store it in the
                    //  ArtistListEntry, for the ListView we need 200px image, for now playing we need
                    //  640px.

                    data.add(new ArtistListEntry(
                            artist.name, artist.id,artist.images.toArray(new Image[]{})
                    ));
                }
            }
            case TOP_TRACKS_QUERY: {
                Tracks tracks = service.getArtistTopTrack(params[1]);

                // Parse data and store it in the data ArrayList
                // we need trackName, albumName, images and previewURL
                String trackName, albumName, previewURL;
                List<Image> images;
                for (Track track : tracks.tracks) {
                    trackName = track.name;
                    albumName = track.album.name;
                    previewURL = track.preview_url;
                    images = track.album.images;
                    data.add(new TopTrack(trackName, albumName, images.get(0).url, images.get(1).url, previewURL));
                }
            }
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList result) {
        super.onPostExecute(result);

        switch (result.get(0).toString()) {
            case ARTISTS_QUERY: {
                // Remove the query code now because we don't need it anymore
                result.remove(0);
                // Get ArtistAdapter and pass in the List that we just created
                if (result != null && !result.isEmpty()) {
                    mArtistAdapter.clear();
                    for (Object entry : result) mArtistAdapter.add(entry);
                    mArtistAdapter.notifyDataSetChanged();
                } else if (result.isEmpty() && result != null) {
                    mArtistAdapter.clear();
                    Toast.makeText(mContext, "There is no such Artist.", Toast.LENGTH_SHORT).show();
                }
            }
            case TOP_TRACKS_QUERY: {
                // Remove the query code now because we don't need it anymore
                result.remove(0);
                // Get mTopTracksAdapter and add the new data to it
                if (result != null && !result.isEmpty()) {
                    mTopTracksAdapter.clear();
                    for (Object entry : result) {
                        mTopTracksAdapter.add(entry);
                    }
                    mTopTracksAdapter.notifyDataSetChanged();
                } else if (result.isEmpty() && result != null) {
                    mTopTracksAdapter.clear();
                    Toast.makeText(mContext, "Error retrieving data.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
