package com.silga.dolocloud.api.hateoas;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.restcontroller.DesignDoloRestController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.List;
import java.util.stream.Collectors;

public class DoloEntityModelAssembler extends RepresentationModelAssemblerSupport<Dolo, DoloEntityModel> {

    public DoloEntityModelAssembler() {
        super(DesignDoloRestController.class, DoloEntityModel.class);
    }

    @Override
    public DoloEntityModel instantiateModel(Dolo dolo) {
        return new DoloEntityModel (dolo);
    }

    @Override
    public DoloEntityModel toModel(Dolo dolo) {
        return createModelWithId (dolo.getId(),dolo);
    }

    public List<DoloEntityModel> toModels(List<Dolo> dolos) {
        return dolos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
