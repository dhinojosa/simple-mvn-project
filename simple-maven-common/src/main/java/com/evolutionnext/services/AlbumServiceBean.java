package com.evolutionnext.services;

import com.evolutionnext.dao.AlbumDAO;
import com.evolutionnext.dao.DAOException;
import com.evolutionnext.model.Album;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AlbumServiceBean {

    private Album album;

    @ManagedProperty(value = "#{albumDAO}")
    private AlbumDAO albumDAO;

    @PostConstruct
    public void init() {
        this.album = new Album();
    }


    public void setAlbumDAO(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public String create() {
        try {
            this.albumDAO.persist(album);
            return "success";
        } catch (DAOException e) {
            return "failure";
        }
    }

    public Album getAlbum() {
        return album;
    }
}
