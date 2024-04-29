package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.publicationssrv.model.Publication;
import com.example.publicationssrv.repository.PublicationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicationRepositoryTest {
  @Autowired
  private PublicationRepository publicationRepository;

  @Test
  public void guardarEstudianteTest() {
    Publication publication = new Publication();
    publication.setTitle("Receta de lasagna");

    Publication resultado = publicationRepository.save(publication);

    assertNotNull(resultado.getId());
    assertEquals("Receta de lasagna", resultado.getTitle());
  }
}
