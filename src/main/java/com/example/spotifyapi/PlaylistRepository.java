package com.example.spotifyapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findByNomePlaylistContaining(String nomePlaylist);
}