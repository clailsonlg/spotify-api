package com.example.spotifyapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    List<Musica> findByNomeMusicaContaining(String nomeMusica);
}