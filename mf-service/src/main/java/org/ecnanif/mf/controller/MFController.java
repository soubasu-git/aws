package org.ecnanif.mf.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.ecnanif.mf.model.MF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/mf", produces = "application/hal+json")
public class MFController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Resources<MF>> all() {
        ResponseEntity<Resources<MF>> mfList = restTemplate.exchange("http://db-service/db/mf", HttpMethod.GET,
                null, new ParameterizedTypeReference<Resources<MF>>() {
                });
        Resources<MF> mfBody = mfList.getBody();
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        mfBody.add(new Link(uriString, "self"));
        return ResponseEntity.ok(mfBody);
    }

    @GetMapping("/{mfId}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Resource<MF>> get(@PathVariable("mfId") final BigInteger mfId) {
        ResponseEntity<Resource<MF>> mf = restTemplate.exchange("http://db-service/db/mf/" + mfId, HttpMethod.GET,
                null, new ParameterizedTypeReference<Resource<MF>>() {
                });
        Resource<MF> mfBody = mf.getBody();
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        mfBody.add(new Link(uriString, "self"));
        //notfoundexception
        return ResponseEntity.ok(mfBody);
    }
    public String  fallbackMethod(int employeeid){

        return "Fallback response:: No details available temporarily";
    }

}
