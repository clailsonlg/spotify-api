package com.example.spotifyapi;

// import com.example.spotifyapi.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  // List<Usuario> findByPublished(boolean published);
  List<Usuario> findByEmailContaining(String email);
}

