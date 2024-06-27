package com.test.task.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.Data;

@Entity
@Table(name = "recurrence_pattern")
@Data
public class RecurrencePatternEntity {

  @Id
  @GeneratedValue
  private Long id;

  private ZonedDateTime nextExecution;

  private Long intervalTime;

  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToOne
  private TreatmentPlanEntity treatmentPlan;
}
