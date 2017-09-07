package com.foldik.split.persistence;

import com.foldik.split.persistence.model.Activity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findByStartTimeBetween(LocalDateTime from, LocalDateTime to);
}
