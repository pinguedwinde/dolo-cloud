package com.silga.dolocloud.restcontroller;

import com.silga.dolocloud.api.hateoas.DoloEntityModel;
import com.silga.dolocloud.api.hateoas.DoloEntityModelAssembler;
import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.repository.DoloRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignDoloRestController {
    private final DoloRepository doloRepository;

    public DesignDoloRestController(DoloRepository doloRepository) {
        this.doloRepository = doloRepository;
    }

    @GetMapping("recent")
    public CollectionModel<DoloEntityModel> getRecentDolos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Dolo> dolos = doloRepository.findAll(page).getContent();
        List<DoloEntityModel> doloResources = new DoloEntityModelAssembler().toModels(dolos);
        CollectionModel<DoloEntityModel> recentResources = new CollectionModel<>(doloResources);
        recentResources.add(
                WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(DesignDoloRestController.class).getRecentDolos()
                        )
                        .withRel("recents")
        );
        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dolo> getDoloById(@PathVariable("id") Long id){
        Dolo dolo = doloRepository.getOne(id);
        if(Objects.isNull(dolo)){
            return new ResponseEntity<>(dolo, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(dolo, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Dolo saveDolo(@RequestBody Dolo dolo){
        return doloRepository.save(dolo);
    }

}
