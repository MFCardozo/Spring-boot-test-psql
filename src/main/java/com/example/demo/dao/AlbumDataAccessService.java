package com.example.demo.dao;

import com.example.demo.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Repository("postgres")
public class AlbumDataAccessService implements AlbumDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlbumDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Album get(Long albumId) throws ResponseStatusException {

        final String sql = "SELECT id,title,year FROM album WHERE id = ?";

        try{

            return jdbcTemplate.queryForObject(sql,  (ResultSet,i)->{
                Long id = ResultSet.getLong("id");
                String title = ResultSet.getString("title");
                String year = ResultSet.getString("year");

                return new Album(id,title,year);
            },albumId);

        }catch(EmptyResultDataAccessException e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album Not Found", e);
        }


    }

    @Override
    public Album add(Album album) {

        final String sql = "INSERT INTO album (title,year) VALUES (?,?) RETURNING id,title,year" ;

       return jdbcTemplate.queryForObject(sql,  (ResultSet,i)->{
            Long id = ResultSet.getLong("id");
            String title = ResultSet.getString("title");
            String year = ResultSet.getString("year");

            return new Album(id,title,year);
        },album.getTitle(),album.getYear());

    }

    @Override
    public List<Album> getAll() {
       final String sql = "SELECT id,title,year FROM album";

               return jdbcTemplate.query(sql,(ResultSet,i)->{
                Long id = ResultSet.getLong("id");
                String title = ResultSet.getString("title");
                String year = ResultSet.getString("year");

                return new Album(id,title,year);
                });

    }

    @Override
    public void delete(Long albumId)throws ResponseStatusException {
        final String sql = "DELETE FROM album WHERE id = ?";

        Album album = get(albumId);

        jdbcTemplate.update(sql,album.getId());

    }
}
