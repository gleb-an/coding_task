package com.test.task.repository;

import com.test.task.domain.TreatmentTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentTaskRepository extends JpaRepository<TreatmentTaskEntity, Long> {

}
