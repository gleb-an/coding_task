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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "treatment_task")
@Data
public class TreatmentTaskEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private TreatmentAction action;

  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToOne
  private TreatmentPlanEntity treatmentPlan;

  @CreatedDate
  private ZonedDateTime createdAt;

  @LastModifiedDate
  private ZonedDateTime updatedAt;
}
