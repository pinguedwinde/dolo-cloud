package com.silga.dolocloud.restcontroller;

import com.silga.dolocloud.api.hateoas.DoloEntityModel;
import com.silga.dolocloud.api.hateoas.DoloEntityModelAssembler;
import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.repository.DoloRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RepositoryRestController
public class DoloRestController {
    private final DoloRepository doloRepository;

    public DoloRestController(DoloRepository doloRepository) {
        this.doloRepository = doloRepository;
    }

    @GetMapping(path="/dolos/recent", produces="application/hal+json")
    public ResponseEntity<CollectionModel<DoloEntityModel>> getRecentDolos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Dolo> dolos = doloRepository.findAll(page).getContent();
        List<DoloEntityModel> doloResources = new DoloEntityModelAssembler().toModels(dolos);
        CollectionModel<DoloEntityModel> recentResources = new CollectionModel<>(doloResources);
        recentResources.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(DoloRestController.class).getRecentDolos()
                )
                        .withRel("recents")
        );
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }

    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Dolo>>> doloProcessor(EntityLinks links){

        return new RepresentationModelProcessor<PagedModel<EntityModel<Dolo>>> (){
            @Override
            public PagedModel<EntityModel<Dolo>> process(PagedModel<EntityModel<Dolo>> resource) {
                resource.add(
                        links.linkFor(Dolo.class)
                                .slash("recent")
                                .withRel("recents"));
                return resource;
            }
        };
    }
}
