package org.ecnanif.db.controller;

import org.ecnanif.db.exception.MFNotFoundException;
import org.ecnanif.db.model.MF;
import org.ecnanif.db.repository.MFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/db/mf", produces = "application/hal+json")
public class MFDbController {
    @Autowired
    private MFRepository mfRepository;

    public MFDbController(MFRepository mfRepository) {
        this.mfRepository = mfRepository;
    }

    @GetMapping
    public ResponseEntity<Resources<MF>> all() {
        final List<MF> collection =
                mfRepository.findAll();
        final Resources<MF> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{mfId}")
    public ResponseEntity<Resource<MF>> get(@PathVariable("mfId") final BigInteger mfId) throws MFNotFoundException {
        return mfRepository
                .findById(mfId)
                .map(p -> {
                    Resource<MF> resource = new Resource<>(p);
                    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
                    resource.add(new Link(uriString, "self"));
                    return ResponseEntity.ok(resource);
                })
                .orElseThrow(() -> new MFNotFoundException(mfId));
    }

}
