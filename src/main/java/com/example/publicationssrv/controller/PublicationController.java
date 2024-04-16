package com.example.publicationssrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.publicationssrv.service.PublicationService;
import com.example.publicationssrv.exception.GlobalExceptionHandler.ErrorResponse;
import com.example.publicationssrv.model.Publication;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public List<Publication> getAllStudents(){
        return publicationService.getAllPublications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable Long id) {
        Optional<Publication> publicationOptional = publicationService.getPublicationById(id);
        return publicationOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Publication createPublication(@RequestBody Publication publication) {
        return publicationService.createPublication(publication);
    }

    @PutMapping("/{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication publication){
        return publicationService.updatePublication(id, publication);
    }
 
    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable Long id){
        publicationService.deletePublication(id);
    }
}
