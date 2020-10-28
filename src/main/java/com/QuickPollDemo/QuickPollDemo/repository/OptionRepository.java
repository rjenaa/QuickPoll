package com.QuickPollDemo.QuickPollDemo.repository;

import com.QuickPollDemo.QuickPollDemo.domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
