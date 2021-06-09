package com.example.spotifyapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://spotify-frontend-angular.herokuapp.com")
@RestController
@RequestMapping("/spotify")
public class UsuarioController {

  @Autowired
  UsuarioRepository usuarioRepository;

  @GetMapping("/usuarios")
  public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) String email) {
    try {
      List<Usuario> usuarios = new ArrayList<Usuario>();

      if (email == null) {
        usuarioRepository.findAll().forEach(usuarios::add);
      }
      
      else
        usuarioRepository.findByEmailContaining(email).forEach(usuarios::add);
      if (usuarios.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(usuarios, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long id) {
    Optional<Usuario> usuarioData = usuarioRepository.findById(id);

    if (usuarioData.isPresent()) {
      return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/usuariosEmail/{email}")
  public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable("email") String email) {
    List<Usuario> usuarioData = usuarioRepository.findByEmailContaining(email);

    if (!usuarioData.isEmpty()) {
      return new ResponseEntity<>(usuarioData.get(0), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/usuarios")
  public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
    try {
        Usuario _usuario = usuarioRepository
          .save(new Usuario(usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getData()));
      return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }

  @PutMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
    Optional<Usuario> usuarioData = usuarioRepository.findById(id);

    if (usuarioData.isPresent()) {
      Usuario _usuario = usuarioData.get();
      _usuario.setEmail(usuario.getEmail());
      _usuario.setSenha(usuario.getSenha());
      _usuario.setNome(usuario.getNome());
      return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/usuarios/{id}")
  public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
    try {
      usuarioRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/usuarios")
  public ResponseEntity<HttpStatus> deleteAllUsuarios() {
    try {
      usuarioRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

  }
}