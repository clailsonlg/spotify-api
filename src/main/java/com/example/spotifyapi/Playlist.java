package com.example.spotifyapi;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

     @ManyToMany
     @JoinTable(name = "playlist_musica", joinColumns = @JoinColumn(name = "playlistId"), inverseJoinColumns = @JoinColumn(name = "musicaId") )
    List<Musica> playlist;
    private String albumPlaylist;
    private String nomePlaylist;

    public Playlist() {

    }

    public Playlist(String albumPlaylist, String nomePlaylist) {
        this.albumPlaylist = albumPlaylist;
        this.nomePlaylist = nomePlaylist;
    }

    public int getId() {
        return id;
    }


    public void addMusica(Musica musica){
      this.playlist.add(musica);
    }

    public List<Musica> getPlaylisListMusicas(){
      return this.playlist;
    }

    public String getAlbumPlaylist() {
        return albumPlaylist;
    }

    public void setAlbumPlaylist(String albumPlaylist) {
        this.albumPlaylist = albumPlaylist;
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public void setNomePlaylist(String nomePlaylist) {
        this.nomePlaylist = nomePlaylist;
    }

    @Override
    public String toString() {
        return "Playlist [albmPlaylist=" + albumPlaylist + ", id=" + id + ", nomePlaylist=" + nomePlaylist + "]";
    }

}
