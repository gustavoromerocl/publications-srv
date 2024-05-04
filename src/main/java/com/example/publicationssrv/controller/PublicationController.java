package com.example.publicationssrv.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.publicationssrv.exception.ResourceNotFoundException;
import com.example.publicationssrv.model.Publication;
import com.example.publicationssrv.service.PublicationService;

@RestController
@RequestMapping("/publications")
public class PublicationController {
  private static final Logger log = LoggerFactory.getLogger(PublicationController.class);
  @Autowired
  private PublicationService publicationService;

  @GetMapping
  public CollectionModel<EntityModel<Publication>> getAllPublications() {
    log.info("GET /publications");
    log.info("Retornando todas las publicaciones");
    List<Publication> publications = publicationService.getAllPublications();
    List<EntityModel<Publication>> publicationResources = publications.stream()
        .map(publication -> EntityModel.of(publication,
            WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                    .getPublicationById(
                        publication.getId()))
                .withSelfRel()))
        .collect(Collectors.toList());

    WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPublications());
    CollectionModel<EntityModel<Publication>> resources = CollectionModel.of(publicationResources,
        linkTo.withRel("publications"));
    return resources;
  }

  @GetMapping("/{id}")
  public EntityModel<Publication> getPublicationById(@PathVariable Long id) {
    log.info("GET /publications/" + id);
    log.info("Retornando publicacion por id");
    Optional<Publication> publication = publicationService.getPublicationById(id);
    if (publication.isPresent()) {
      return EntityModel.of(publication.get(),
          WebMvcLinkBuilder
              .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                  .getPublicationById(id))
              .withSelfRel(),
          WebMvcLinkBuilder
              .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                  .getAllPublications())
              .withRel("all-publications"));
    } else {
      throw new ResourceNotFoundException("The publication id doesnt exists");
    }
  }

  @PostMapping
  public EntityModel<Publication> createPublication(@Validated @RequestBody Publication publication) {
    log.info("POST /publications");
    log.info("Creado una publicación");
    Publication createdPublication = publicationService.createPublication(publication);
    return EntityModel.of(createdPublication,
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(this.getClass())
                .getPublicationById(createdPublication.getId()))
            .withSelfRel(),
        WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllPublications())
            .withRel("all-publications"));
  }

  @PutMapping("/{id}")
  public EntityModel<Publication> updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
    log.info("PUT /publications/" + id);
    log.info("Actualizando una publicacion");
    Publication updatedPublication = publicationService.updatePublication(id, publication);
    return EntityModel.of(updatedPublication,
        WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getPublicationById(id))
            .withSelfRel(),
        WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllPublications())
            .withRel("all-publications"));
  }

  @DeleteMapping("/{id}")
  public void deletePublication(@PathVariable Long id) {
    log.info("DELETE /publications/" + id);
    log.info("Eliminando una publicación por id");
    publicationService.deletePublication(id);
  }

  @GetMapping("/average/{id}")
  public double getAveragePublicationById(@PathVariable Long id) {
    log.info("GET /publications/average" + id);
    log.info("Obteniendo el promedio de una publicación por id");
    return publicationService.getAverageByPublicationId(id);
  }
}
