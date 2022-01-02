package com.example.demo.soap.endpoints;

import com.example.demo.exception.CustomException;
import com.example.demo.gen.*;
import com.example.demo.service.CourseDetailsService;
import com.example.demo.soap.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    private static final String NAMESPACE_URI = "http://www.snitechnology.net";

    @Autowired
    private CourseDetailsService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        System.out.println("Request works");
        Course course = service.findById(request.getId());
        if (course == null) throw new CustomException("Invalid Course ID: " + request.getId());
        return mapCourse(course);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        System.out.println("Request All works");
        GetAllCourseDetailsResponse allCourseDetailsResponse = new GetAllCourseDetailsResponse();
        List<Course> all = service.findAll();

        for (Course course : all) {
            CourseDetails courseDetails = mapCourseDetails(course);
            allCourseDetailsResponse.getCourseDetails().add(courseDetails);
        }
        return allCourseDetailsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processDeleteCourseDetails(@RequestPayload DeleteCourseDetailsRequest request) {
        System.out.println("Delete Works");
        Status status = service.deleteById(request.getId());
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(status);
        return response;
    }

    private GetCourseDetailsResponse mapCourse(Course course) {
        GetCourseDetailsResponse getCourseDetailsResponse = new GetCourseDetailsResponse();
        CourseDetails courseDetails = mapCourseDetails(course);
        getCourseDetailsResponse.setCourseDetails(courseDetails);
        return getCourseDetailsResponse;
    }

    private CourseDetails mapCourseDetails(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }
}
