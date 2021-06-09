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
public class PlaylistController {

    @Autowired
    PlaylistRepository playlistRepository;

    @GetMapping("/playlist")
    public ResponseEntity<List<Playlist>> getAllPlaylist(@RequestParam(required = false) String nomePlaylist) {
        try {
            List<Playlist> playlist = new ArrayList<Playlist>();

            if (nomePlaylist == null) {
                playlistRepository.findAll().forEach(playlist::add);
            }

            else
                playlistRepository.findByNomePlaylistContaining(nomePlaylist).forEach(playlist::add);
            if (playlist.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/playlistId/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable("id") int id) {
        Optional<Playlist> playlistData = playlistRepository.findById(id);

        if (playlistData.isPresent()) {
            return new ResponseEntity<>(playlistData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/playlist")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        try {
            Playlist _playlist = playlistRepository
                    .save(new Playlist(playlist.getAlbumPlaylist(), playlist.getNomePlaylist()));
            return new ResponseEntity<>(_playlist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/playlist/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") int id, @RequestBody Playlist playlist) {
        Optional<Playlist> playlistData = playlistRepository.findById(id);

        if (playlistData.isPresent()) {
            Playlist _playlist = playlistData.get();
            _playlist.setNomePlaylist(playlist.getNomePlaylist());
            _playlist.setAlbumPlaylist(playlist.getAlbumPlaylist());
            return new ResponseEntity<>(playlistRepository.save(_playlist), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/playlistAddMusica/{id}")
    public ResponseEntity<Playlist> addMusica(@PathVariable("id") int id, @RequestBody Musica musica) {
        Optional<Playlist> playlistData = playlistRepository.findById(id);

        if (playlistData.isPresent()) {
          System.out.println("AAAAAAQUUIUUI");
            Playlist _playlist = playlistData.get();
            System.out.println("entrou aqui no ggggggg");
           _playlist.addMusica(musica);
           System.out.println("entrou aqui no 1" +  _playlist.getPlaylisListMusicas());
            return new ResponseEntity<>(playlistRepository.save(_playlist), HttpStatus.OK);
        } else {
          System.out.println("entrou aqui no 2");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/playlist/{id}")
    public ResponseEntity<HttpStatus> deletePlaylist(@PathVariable("id") int id) {
        try {
            playlistRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/playlist")
    public ResponseEntity<HttpStatus> deleteAllPlaylist() {
        try {
            playlistRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }
}
