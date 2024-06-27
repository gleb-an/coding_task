package com.test.task.repository;

import com.test.task.domain.RecurrencePatternEntity;
import com.test.task.domain.Status;
import java.time.ZonedDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurrencePatternRepository extends JpaRepository<RecurrencePatternEntity, Long> {

  Page<RecurrencePatternEntity> findAllByStatusAndNextExecutionLessThanEqual(Status status, ZonedDateTime nextExecution, Pageable pageable);
}
