package org.ecnanif.db.resource;

import org.ecnanif.db.controller.MFDbController;
import org.ecnanif.db.exception.MFNotFoundException;
import org.ecnanif.db.model.MF;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigInteger;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MFResource extends ResourceSupport {

    private final MF mf;

    public MFResource(final MF mf) {
        this.mf = mf;
        final BigInteger id = mf.getMfId();
        add(linkTo(MFDbController.class).withRel("mf"));
        try {
            add(linkTo(methodOn(MFDbController.class).get(id)).withSelfRel());
        } catch (MFNotFoundException e) {
            e.printStackTrace();
        }
    }
}