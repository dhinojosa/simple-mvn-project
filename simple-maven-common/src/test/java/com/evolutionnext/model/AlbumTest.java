package com.evolutionnext.model;

import com.evolutionnext.model.Album;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


public class AlbumTest {
    @Test
    public void testProperties() throws Exception {
        Album album = new Album();
        album.setName("Rumours");
        album.setId(13L);
        assertThat(album.getName()).isEqualTo("Rumours");
        assertThat(album.getId()).isEqualTo(13L);
    }
}
