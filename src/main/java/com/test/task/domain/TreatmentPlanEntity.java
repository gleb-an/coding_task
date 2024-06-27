package com.test.task.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "treatment_plan")
@Data
public class TreatmentPlanEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private TreatmentAction treatmentAction;

  private String patient;

  private ZonedDateTime startTime;

  private ZonedDateTime endTime;

  @Enumerated(EnumType.STRING)
  private Status status;

  @OneToMany(mappedBy = "treatmentPlan")
  private List<RecurrencePatternEntity> patterns;

  @OneToMany(mappedBy = "treatmentPlan")
  private List<TreatmentTaskEntity> tasks;

  @CreatedDate
  private ZonedDateTime createdAt;

  @LastModifiedDate
  private ZonedDateTime updatedAt;
}
