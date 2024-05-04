package com.example.publicationssrv.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.publicationssrv.exception.ResourceNotFoundException;
import com.example.publicationssrv.model.Publication;
import com.example.publicationssrv.repository.PublicationRepository;

@Service
public class PublicationServiceImp implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> getPublicationById(Long id) {
        return publicationRepository.findById(id);
    }

    @Override
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication updatePublication(Long id, Publication publication) {
        if (publicationRepository.existsById(id)) {
            publication.setId(id);
            return publicationRepository.save(publication);
        } else {
            return null;
        }
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public Double getAverageByPublicationId(@PathVariable Long id) {
        List<Publication> publications = publicationRepository.findAll();

        // Filter publications by the given ID
        List<Publication> filteredPublications = publications.stream()
                .filter(publication -> publication.getId() == id)
                .collect(Collectors.toList());

        // Check if any publications with the given ID exist
        if (filteredPublications.isEmpty()) {
            throw new ResourceNotFoundException("Publication with ID " + id + " not found");
        }

        // Calculate average qualification
        double totalQualification = filteredPublications.stream()
                .flatMap(publication -> publication.getComments().stream())
                .mapToDouble(comment -> comment.getQualification()) // Assuming Comment class has a getQualification
                                                                    // method
                .sum();

        double averageQualification = (double) totalQualification
                / filteredPublications.stream().flatMap(publication -> publication.getComments().stream()).count();

        return averageQualification;
    }

}
