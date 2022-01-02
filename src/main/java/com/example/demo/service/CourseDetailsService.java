package com.example.demo.service;

import com.example.demo.gen.Status;
import com.example.demo.soap.Course;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.example.demo.gen.Status.*;

@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    {
        Course course1 = new Course(1, "Spring", "10 Steps");
        courses.add(course1);

        Course course2 = new Course(2, "Spring2", "11 Steps");
        courses.add(course2);

        Course course3 = new Course(3, "Spring3", "12 Steps");
        courses.add(course3);

        Course course4 = new Course(4, "Spring4", "13 Steps");
        courses.add(course4);
    }

    public Course findById(int id) {
        Optional<Course> first = courses.stream().filter(o -> o.getId() == id).findFirst();
        return first.orElse(null);
    }

    public List<Course> findAll() {
        return Collections.unmodifiableList(courses);
    }

    public Status deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return SUCCESS;
            }
        }
        return FAILURE;
    }
}
