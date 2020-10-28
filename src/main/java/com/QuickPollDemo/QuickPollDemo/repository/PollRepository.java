package com.QuickPollDemo.QuickPollDemo.repository;

import com.QuickPollDemo.QuickPollDemo.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
