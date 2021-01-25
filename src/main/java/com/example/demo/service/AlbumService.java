package com.example.demo.service;

import com.example.demo.dao.AlbumDao;
import com.example.demo.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumDao albumDao;

    @Autowired
    public  AlbumService(@Qualifier("postgres") AlbumDao albumDao){

        this.albumDao = albumDao;
    }

    public Album getAlbum(Long id) {
       return albumDao.get(id);
    }

    public List<Album> getAllAlbums(){

        return albumDao.getAll();
    }

    public Album addAlbum(Album album) {
       return albumDao.add(album);
    }

    public void deleteAlbum(Long albumId) {

        albumDao.delete(albumId);
    }
}
