package com.example.publicationssrv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.publicationssrv.model.Publication;

public interface PublicationService {
    List<Publication> getAllPublications();
    Optional<Publication> getPublicationById(Long id);
    Publication createPublication(Publication publication);
    Publication updatePublication(Long id, Publication publication);
    void deletePublication(Long id);
    Double getAverageByPublicationId(@PathVariable Long id);
}
