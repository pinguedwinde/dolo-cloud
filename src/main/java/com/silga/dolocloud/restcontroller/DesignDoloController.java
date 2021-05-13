package com.silga.dolocloud.restcontroller;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.repository.DoloRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignDoloController {
    private final DoloRepository doloRepository;

    public DesignDoloController(DoloRepository doloRepository) {
        this.doloRepository = doloRepository;
    }

    @GetMapping("recent")
    public Iterable<Dolo> getRecentDolos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return doloRepository.findAll(page).getContent();
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
