package com.example.publicationssrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.publicationssrv.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{
    
}
