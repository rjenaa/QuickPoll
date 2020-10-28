package com.QuickPollDemo.QuickPollDemo.controller;

import com.QuickPollDemo.QuickPollDemo.domain.Vote;
import com.QuickPollDemo.QuickPollDemo.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class VoteController {

    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/polls/{id}/votes", method = RequestMethod.POST)
    public ResponseEntity createVote(@PathVariable Long id, @RequestBody Vote vote){
        voteService.createVote(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
            return new ResponseEntity(null, responseHeaders, HttpStatus.CREATED);
    }


    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteService.getAllVotes(pollId);
    }





}
