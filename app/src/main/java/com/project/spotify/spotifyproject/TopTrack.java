package com.project.spotify.spotifyproject;

/**
 * Created by Velvel on 6/17/2015.
 * <p/>
 * This is the updated Adapter that we are using to
 * dis
 */
public class TopTrack {

    private String trackName;

    private String albumName;

    private String nowPlayingImgURL;
    private String listViewImgURL;

    private String previewURL; // We will use this to stream the music in stage 2

    public TopTrack(String trackName, String albumName, String nowPlayingImgURL, String listViewImgURL, String previewURL) {
        this.trackName = trackName;
        this.albumName = albumName;
        this.nowPlayingImgURL = nowPlayingImgURL;
        this.listViewImgURL = listViewImgURL;
        this.previewURL = previewURL;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getNowPlayingImgURL() {
        return nowPlayingImgURL;
    }

    public String getListViewImgURL() {
        return listViewImgURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }
}
