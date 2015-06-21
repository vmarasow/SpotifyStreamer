package com.project.spotify.spotifyproject;

import kaaes.spotify.webapi.android.models.Image;

;

/**
 * Created by Velvel on 6/16/2015.
 * <p/>
 * This is the updated Adapter that we are using to
 * dis
 */
public class ArtistListEntry {

    private String name;
    private String id;
    private Image[] img;

    public ArtistListEntry(String name, String id, Image[] img) {
        this.name = name;
        this.id = id;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Image[] getImg() {
        return img;
    }
}
