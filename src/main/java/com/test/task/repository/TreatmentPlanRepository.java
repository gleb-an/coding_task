package com.test.task.repository;

import com.test.task.domain.Status;
import com.test.task.domain.TreatmentPlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlanEntity, Long> {

  Page<TreatmentPlanEntity> findAllByStatus(Status status, Pageable pageable);
}
