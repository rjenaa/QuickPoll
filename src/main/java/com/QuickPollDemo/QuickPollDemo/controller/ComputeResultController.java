package com.QuickPollDemo.QuickPollDemo.controller;

import com.QuickPollDemo.QuickPollDemo.domain.Vote;
import com.QuickPollDemo.QuickPollDemo.dto.OptionCount;
import com.QuickPollDemo.QuickPollDemo.dto.VoteResult;
import com.QuickPollDemo.QuickPollDemo.repository.VoteRepository;
import com.QuickPollDemo.QuickPollDemo.services.VoteService;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value="/computeresult", method= RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {

        return voteService.computeResult(pollId);
    }



}
