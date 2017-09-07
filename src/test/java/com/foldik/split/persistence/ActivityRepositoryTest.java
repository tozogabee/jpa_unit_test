package com.foldik.split.persistence;

import com.foldik.split.persistence.model.Activity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository underTest;

    @Before
    @After
    public void removeActivities() {
        underTest.deleteAll();
    }

    @Test
    public void whenSavedOneActivity_thenShouldBePersisted() {
        Activity activity = new Activity();
        activity.setActivityName("Programming");
        activity.setStartTime(LocalDateTime.parse("2017-09-07T17:30:00"));
        activity.setEndTime(LocalDateTime.parse("2017-09-07T21:00:00"));

        underTest.save(activity);

        Iterable<Activity> result = underTest.findAll();
        Activity resultActivity = result.iterator().next();
        assertThat(resultActivity.getActivityName(), is("Programming"));
    }

}