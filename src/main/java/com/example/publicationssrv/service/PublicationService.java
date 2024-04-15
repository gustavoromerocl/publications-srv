package com.example.publicationssrv.service;

import com.example.publicationssrv.model.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationService {
    List<Publication> getAllPublications();
    Optional<Publication> getPublicationById(Long id);
    Publication createPublication(Publication publication);
    Publication updatePublication(Long id, Publication publication);
    void deletePublication(Long id);
}
