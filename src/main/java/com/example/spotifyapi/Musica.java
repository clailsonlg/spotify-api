package com.example.spotifyapi;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany(mappedBy = "playlist")
    private List<Playlist> playlists;

    private String nomeMusica;
    private String artista;
    private String pathMusica;

    public Musica() {
    }

    public Musica(String nomeMusica, String artista, String pathMusica) {
        this.nomeMusica = nomeMusica;
        this.artista = artista;
        this.pathMusica = pathMusica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // public void addMusica(Playlist playlist){
    //   this.playlists.add(playlist);
    // }

    // public List<Playlist> getPlaylisListMusicas(){
    //   return this.playlists;
    // }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getPathMusica() {
        return pathMusica;
    }

    public void setPathMusica(String pathMusica) {
        this.pathMusica = pathMusica;
    }

    @Override
    public String toString() {
        return "Musica [artista=" + artista + ", id=" + id + ", nomeMusica=" + nomeMusica + ", pathMusica=" + pathMusica
                + "]";
    }




}
