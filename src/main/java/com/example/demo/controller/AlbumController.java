package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/v1/album")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping
    public List<Album> getAllAlbums(){

        return albumService.getAllAlbums();

    }

    @GetMapping("/{albumId}")
    public Album getAlbum(@PathVariable("albumId")Long albumId){

            return albumService.getAlbum(albumId);

    }
    @PostMapping
    public Album addAlbum(@NotNull @Valid @RequestBody Album album){
        
        return albumService.addAlbum(album);
    }
    
    @DeleteMapping("/{albumId}")
    public void deleteAlbum(@PathVariable("albumId") Long albumId) {
        albumService.deleteAlbum(albumId);
    }

}
