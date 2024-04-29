package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
