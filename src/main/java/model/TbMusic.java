package model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/1.
 */
@Entity
@Table(name = "tb_music", schema = "heymanagement", catalog = "heymanagement")
public class TbMusic {
    private int id;
    private String name;
    private String artist;
    private String album;
    private String description;
    private Integer albumId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "artist", nullable = true, length = 255)
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Basic
    @Column(name = "album", nullable = true, length = 255)
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "album_id", nullable = true, length = 255)
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbMusic tbMusic = (TbMusic) o;

        if (id != tbMusic.id) return false;
        if (name != null ? !name.equals(tbMusic.name) : tbMusic.name != null) return false;
        if (artist != null ? !artist.equals(tbMusic.artist) : tbMusic.artist != null) return false;
        if (album != null ? !album.equals(tbMusic.album) : tbMusic.album != null) return false;
        if (description != null ? !description.equals(tbMusic.description) : tbMusic.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
