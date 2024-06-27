package com.test.task.service.impl;

import com.test.task.domain.RecurrencePatternEntity;
import com.test.task.domain.Status;
import com.test.task.domain.TreatmentTaskEntity;
import com.test.task.repository.RecurrencePatternRepository;
import com.test.task.repository.TreatmentPlanRepository;
import com.test.task.repository.TreatmentTaskRepository;
import com.test.task.service.TreatmentTaskSchedulerService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TreatmentTaskSchedulerServiceImpl implements TreatmentTaskSchedulerService {

  public static final int SIZE = 100;
  private final TreatmentPlanRepository treatmentPlanRepository;
  private final TreatmentTaskRepository treatmentTaskRepository;
  private final RecurrencePatternRepository recurrencePatternRepository;

  @Override
  public void scheduleNewTasks() {
    var page = 0;

    var updatedScheduler = new LinkedList<RecurrencePatternEntity>();
    Page<RecurrencePatternEntity> patterns;
    do {

      var nowPlusHour = ZonedDateTime.now(ZoneId.systemDefault()).plusHours(1);
      patterns = recurrencePatternRepository.findAllByStatusAndNextExecutionLessThanEqual(
          Status.ACTIVE,
          nowPlusHour,
          PageRequest.of(page, SIZE)
      );

      var preparedTasks = new ArrayList<TreatmentTaskEntity>(SIZE);

      patterns.forEach(pattern -> {
        var plan = pattern.getTreatmentPlan();
        var newTask = new TreatmentTaskEntity();
        newTask.setAction(plan.getTreatmentAction());
        newTask.setStatus(Status.ACTIVE);
        newTask.setTreatmentPlan(plan);

        preparedTasks.add(newTask);
        pattern.setNextExecution(getNextExecutionTime(pattern.getNextExecution(), pattern.getIntervalTime()));
        updatedScheduler.add(pattern);
      });

      treatmentTaskRepository.saveAll(preparedTasks);
      page++;
    } while (patterns.getTotalPages() > page);

    recurrencePatternRepository.saveAll(updatedScheduler);
  }

  private ZonedDateTime getNextExecutionTime(ZonedDateTime prevExec, Long interval) {
    var now = ZonedDateTime.now(ZoneId.systemDefault());
    ZonedDateTime nextExec = prevExec;
    do {
      nextExec = nextExec.plusSeconds(interval);
    } while (nextExec.isBefore(now));
    return nextExec;
  }
}
