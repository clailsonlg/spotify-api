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
public class MusicaController {

    @Autowired
    MusicaRepository musicaRepository;

    @GetMapping("/musica")
    public ResponseEntity<List<Musica>> getAllMusica(@RequestParam(required = false) String nomeMusica) {
        try {
            List<Musica> musica = new ArrayList<Musica>();

            if (nomeMusica == null) {
                musicaRepository.findAll().forEach(musica::add);
            }

            else
                musicaRepository.findByNomeMusicaContaining(nomeMusica).forEach(musica::add);
            if (musica.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(musica, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/musica/{id}")
    public ResponseEntity<Musica> getMusicaById(@PathVariable("id") int id) {
        Optional<Musica> musicaData = musicaRepository.findById(id);

        if (musicaData.isPresent()) {
            return new ResponseEntity<>(musicaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/musica")
    public ResponseEntity<Musica> createMusica(@RequestBody Musica musica) {
        try {
            Musica _musica = musicaRepository
                    .save(new Musica(musica.getNomeMusica(), musica.getArtista(), musica.getPathMusica()));
            return new ResponseEntity<>(_musica, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/musica/{id}")
    public ResponseEntity<Musica> updateMusica(@PathVariable("id") int id, @RequestBody Musica musica) {
        Optional<Musica> musicaData = musicaRepository.findById(id);

        if (musicaData.isPresent()) {
            Musica _musica = musicaData.get();
            _musica.setNomeMusica(musica.getNomeMusica());
            _musica.setArtista(musica.getArtista());
            _musica.setPathMusica(musica.getPathMusica());
            return new ResponseEntity<>(musicaRepository.save(_musica), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/musica/{id}")
    public ResponseEntity<HttpStatus> deleteMusica(@PathVariable("id") int id) {
        try {
            musicaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/musica")
    public ResponseEntity<HttpStatus> deleteAllMusica() {
        try {
            musicaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }
}