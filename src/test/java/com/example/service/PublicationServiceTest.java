package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.publicationssrv.Comment;
import com.example.publicationssrv.model.Publication;
import com.example.publicationssrv.repository.PublicationRepository;
import com.example.publicationssrv.service.PublicationServiceImp;

@ExtendWith(MockitoExtension.class)
public class PublicationServiceTest {
    @InjectMocks
    private PublicationServiceImp publicationService;

    @Mock
    private PublicationRepository publicationRepositoryMock;

    @Test
    public void savePublicationsTest() {
        Publication publication = new Publication();
        publication.setTitle("Los mejores animes de Otoño");

        when(publicationRepositoryMock.save(any())).thenReturn(publication);

        Publication resultado = publicationService.createPublication(publication);

        assertEquals("Los mejores animes de Otoño", resultado.getTitle());
    }

    @Test
    public void testGetAverageByPublicationId_WhenPublicationExists() {
        Long publicationId = 1L;
        List<Publication> publications = new ArrayList<>();
        Publication publication = new Publication();
        publication.setId(publicationId);
        publication.setComments(new ArrayList<>());
        Comment comment1 = new Comment();
        comment1.setQualification(4.5);
        Comment comment2 = new Comment();
        comment2.setQualification(3.5);
        publication.getComments().add(comment1);
        publication.getComments().add(comment2);
        publications.add(publication);

        when(publicationRepositoryMock.findAll()).thenReturn(publications);

        double expectedAverage = (4.5 + 3.5) / 2;
        double actualAverage = publicationService.getAverageByPublicationId(publicationId);
        assertEquals(expectedAverage, actualAverage);
    }
}
