package com.evolutionnext.dao;

import com.evolutionnext.model.Album;

public interface AlbumDAO {
    Long persist(Album album) throws DAOException;

    java.util.List<Album> getAllAlbums() throws DAOException;
}
