package com.evolutionnext.services;

import com.evolutionnext.dao.AlbumDAO;
import com.evolutionnext.dao.DAOException;
import com.evolutionnext.services.AlbumServiceBean;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.fest.assertions.Assertions.assertThat;

public class AlbumServiceBeanTest {

    @Test
    public void testCreate() throws DAOException {
        AlbumDAO albumDAOMock = createMock(AlbumDAO.class);
        AlbumServiceBean albumServiceBean = new AlbumServiceBean();
        albumServiceBean.init();

        expect(albumDAOMock.persist(albumServiceBean.getAlbum())).andReturn(2L);

        replay(albumDAOMock);
        albumServiceBean.setAlbumDAO(albumDAOMock);

        assertThat(albumServiceBean.create()).isEqualTo("success");
        verify(albumDAOMock);
    }

    @Test
    public void testCreateFailure() throws DAOException {
        AlbumDAO albumDAOMock = createMock(AlbumDAO.class);
        AlbumServiceBean albumServiceBean = new AlbumServiceBean();
        albumServiceBean.init();

        expect(albumDAOMock.persist(albumServiceBean.getAlbum())).andThrow(new DAOException(new Exception("All hell broke loose")));

        replay(albumDAOMock);
        albumServiceBean.setAlbumDAO(albumDAOMock);

        assertThat(albumServiceBean.create()).isEqualTo("failure");
        verify(albumDAOMock);
    }
}
