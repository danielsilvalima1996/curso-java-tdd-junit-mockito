package br.com.daniel.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.daniel.service.CourseService;
import br.com.daniel.stubs.CourseServiceStub;

class CourseBusinessStubTest {

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("testCoursesRelatedToSpring_When_UsingAsStubs")
    @Test
    void testCoursesRelatedToSpring_When_UsingAsStub() { 
        // Given / Arrange  
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);
        // When / Act  
        var filteredCourse = business.retrieveCoursesRelatedToSpring("Daniel");
        // Then / Assert 
        assertEquals(4, filteredCourse.size(),
            () -> "List size is not equals");
    }

        // test[System Under Test]_[Condition or State Change]_[Expected Result]
        @DisplayName("testCoursesRelatedToSpring_When_UsingAsStubs")
        @Test
        void testCoursesRelatedToSpring_When_UsingFooBarStudent() { 
            // Given / Arrange  
            CourseService stubService = new CourseServiceStub();
            CourseBusiness business = new CourseBusiness(stubService);
            // When / Act  
            var filteredCourse = business.retrieveCoursesRelatedToSpring("Foo Bar");
            // Then / Assert 
            assertEquals(0, filteredCourse.size(),
                () -> "List size is not equals");
        }

}
