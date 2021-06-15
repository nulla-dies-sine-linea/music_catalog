package org.hse.mcatalog;

import java.io.File;

public class MusicItem {
    private Integer id;
    private File coverPic;
    private String name;
    private String artist;
    private String album;
    private Integer year;
    private Integer length;
    private Boolean isFav;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public File getCoverPic() {return coverPic;}
    public void setCoverPic(File pic) {this.coverPic = pic;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getArtist() {return artist;}
    public void setArtist(String artist) {this.artist = artist;}

    public String getAlbum() {return album;}
    public void setAlbum(String album) {this.album = album;}

    public Integer getYear() {return year;}
    public void setYear(Integer year) {this.year = year;}

    public Integer getLength() {return length;}
    public void setLength(Integer length) {this.length = length;}

    public Boolean getFav() {return isFav;}
    public void setFav(Boolean fav) {this.isFav = fav;}
}
