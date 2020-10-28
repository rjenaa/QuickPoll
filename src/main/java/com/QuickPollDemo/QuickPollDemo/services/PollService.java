package com.QuickPollDemo.QuickPollDemo.services;

import com.QuickPollDemo.QuickPollDemo.domain.Poll;
import com.QuickPollDemo.QuickPollDemo.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public Iterable<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    public Poll save(Poll poll){
        pollRepository.save(poll);
        return poll;
    }

    public Optional<Poll> getPollById(Long Id){
        return pollRepository.findById(Id);
    }

    public void updatePoll(Poll poll, Long id){
        pollRepository.save(poll);
    }
    public void detetePoll(Long id){
        pollRepository.deleteById(id);
    }

}
