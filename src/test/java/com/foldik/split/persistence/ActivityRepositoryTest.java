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
import java.util.List;

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

    @Test
    public void addActivityBetweemTwoDates()
    {
        Activity activity1=new Activity();
        Activity activity2=new Activity();
        Activity activity3=new Activity();

        //Activity1 settings
        activity1.setActivityName("Java Development");
        activity1.setStartTime(LocalDateTime.parse("2015-09-02T15:30:25"));
        activity1.setEndTime(LocalDateTime.parse("2016-09-15T16:45:12"));
        underTest.save(activity1);


        //Activity2 settings
        activity2.setActivityName("Embedded System Development");
        activity2.setStartTime(LocalDateTime.parse("2015-10-20T17:30:00"));
        activity2.setEndTime(LocalDateTime.parse("2016-10-20T17:30:00"));
        underTest.save(activity2);

        //Activity3 settings
        activity3.setActivityName("LeaderShip skills");
        activity3.setStartTime(LocalDateTime.parse("2015-11-20T17:30:00"));
        activity3.setEndTime(LocalDateTime.parse("2016-12-22T17:30:00"));
        underTest.save(activity3);


        List<Activity> results = underTest.findByStartTimeBetween(LocalDateTime.parse("2015-10-02T15:30:25"),LocalDateTime.parse("2015-10-22T17:30:00"));

        //assertThat(results.get(0).getActivityName(),is("Java Development"));
        assertThat(results.get(0).getActivityName(),is("Embedded System Development"));
        //assertThat(results.get(2).getActivityName(),is("LeaderShip skills"));

    }

}