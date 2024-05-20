package br.com.daniel.service;

import java.util.List;

public interface CourseService {

    public List<String> retrieveCourses(String student);
    public List<String> doSomeThing(String student);
    public void deleteCourse(String course);

}
