package com.example.demo.dao;

import com.example.demo.model.Album;

import java.util.List;


public interface AlbumDao {

    Album add(Album album);

    Album get(Long albumId);

    List<Album> getAll();

    void delete(Long albumId);
}
