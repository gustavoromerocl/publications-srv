package com.example.publicationssrv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.publicationssrv.model.Publication;
import com.example.publicationssrv.repository.PublicationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImp implements PublicationService{
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
        if(publicationRepository.existsById(id)){
            publication.setId(id);
            return publicationRepository.save(publication);
        }
    }
}
