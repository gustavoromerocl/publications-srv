package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.publicationssrv.PublicationssrvApplication;
import com.example.publicationssrv.model.Publication;
import com.example.publicationssrv.repository.PublicationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes=PublicationssrvApplication.class)
public class PublicationRepositoryTest {
  @Autowired
  private PublicationRepository publicationRepository;

  @Test
  public void savePublicationTest() {
    Publication publication = new Publication();
    publication.setTitle("Receta de lasagna");

    Publication result = publicationRepository.save(publication);

    assertNotNull(result.getId());
    assertEquals("Receta de lasagna", result.getTitle());
  }
}
