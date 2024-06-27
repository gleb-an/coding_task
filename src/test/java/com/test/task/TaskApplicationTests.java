package com.test.task;

import com.test.task.repository.RecurrencePatternRepository;
import com.test.task.repository.TreatmentTaskRepository;
import com.test.task.service.TreatmentTaskSchedulerService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskApplicationTests {

  @Autowired
  private TreatmentTaskSchedulerService service;

  @Autowired
  private TreatmentTaskRepository treatmentTaskRepository;

  @Autowired
  private RecurrencePatternRepository recurrencePatternRepository;

  @Test
  void contextLoads() {

    service.scheduleNewTasks();

    var tasks = treatmentTaskRepository.findAll();
    Assertions.assertEquals(2, tasks.size());

    var pattern1 = recurrencePatternRepository.findById(1L).orElseThrow();
    Assertions.assertEquals(
        ZonedDateTime.now(ZoneId.systemDefault())
            .plusDays(1)
            .withHour(11)
            .withMinute(0)
            .withSecond(0)
            .withNano(0).toInstant(), pattern1.getNextExecution().toInstant());

    var pattern2 = recurrencePatternRepository.findById(2L).orElseThrow();
    Assertions.assertEquals(
        ZonedDateTime.now(ZoneId.systemDefault())
            .plusDays(1)
            .withHour(18)
            .withMinute(0)
            .withSecond(0)
            .withNano(0).toInstant(), pattern2.getNextExecution().toInstant());
  }

}
