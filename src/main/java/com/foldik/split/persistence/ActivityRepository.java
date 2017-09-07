package com.foldik.split.persistence;

import com.foldik.split.persistence.model.Activity;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    
}
