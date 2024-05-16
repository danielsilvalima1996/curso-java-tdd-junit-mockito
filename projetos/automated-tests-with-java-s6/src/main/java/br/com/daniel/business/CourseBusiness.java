package br.com.daniel.business;

import java.util.ArrayList;
import java.util.List;

import br.com.daniel.service.CourseService;

// CourseBusiness = SUT - System (Method) Under Test
public class CourseBusiness {

    // CourseService is dependency
    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {

        var filteredCourses = new ArrayList<String>();
        var allCourses = service.retrieveCourses(student);

        if("Foo Bar".equals(student))
            return filteredCourses;

        for (String course : allCourses) {
            if(course.contains("Spring")) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }

    public void deleteCoursesNotRelatedToSpring(String student) {

        var allCourses = service.retrieveCourses(student);

        for (String course : allCourses) {
            if(!course.contains("Spring")) {
                service.deleteCourse(course);
            }
        }
    }

}
