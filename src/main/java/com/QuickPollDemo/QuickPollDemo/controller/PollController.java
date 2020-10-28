package com.QuickPollDemo.QuickPollDemo.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.QuickPollDemo.QuickPollDemo.domain.Poll;
import com.QuickPollDemo.QuickPollDemo.exception.ResourceNotFoundException;
import com.QuickPollDemo.QuickPollDemo.repository.PollRepository;
import com.QuickPollDemo.QuickPollDemo.services.PollService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    protected void verifyPoll(Long id) throws ResourceNotFoundException{
        Optional<Poll> poll = pollService.getPollById(id);
        if(poll.isEmpty()){
            throw new ResourceNotFoundException("Poll with id" + id + " not found");

        }
    }

    @RequestMapping("/polls")
    public Iterable<Poll> getAllPolls (){
        return pollService.getAllPolls();
    }

    @RequestMapping(value="/polls", method = RequestMethod.POST)
    public ResponseEntity createPoll(@Valid @RequestBody Poll poll){
        poll = pollService.save(poll);
        HttpHeaders resposeHeaders = new HttpHeaders();
        URI newPollURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        resposeHeaders.setLocation(newPollURI);
        return new ResponseEntity(null, resposeHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
    public ResponseEntity getPoll(@PathVariable Long id){
        verifyPoll(id);
        Optional<Poll> p = pollService.getPollById(id);
        if(p.isEmpty()){
            throw new ResourceNotFoundException("Poll with id: " + id + " not found");
        }
        return new ResponseEntity(p,HttpStatus.OK);

    }
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        // Save the entity
        verifyPoll(pollId);
        pollService.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll( @PathVariable Long pollId) {
        // Save the entity
        verifyPoll(pollId);
        pollService.detetePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);

    }



}
