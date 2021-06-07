package org.hse.mcatalog;

import java.io.File;

public class MusicItem {
    private File coverPic;
    private String name;
    private String author;
    private String album;
    private Integer year;
    private Integer length;

    public File getCoverPic() {return coverPic;}
    public void setCoverPic(File pic) {this.coverPic = pic;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    public String getAlbum() {return album;}
    public void setAlbum(String album) {this.album = album;}

    public Integer getYear() {return year;}
    public void setYear(Integer year) {this.year = year;}

    public Integer getLength() {return length;}
    public void setLength(Integer length) {this.length = length;}
}
