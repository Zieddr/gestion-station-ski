package tn.esprit.spring.tests;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;
import tn.esprit.spring.services.ICourseServices;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceImplTest {

    @Autowired
    private ICourseRepository cr;
    @Autowired
    ICourseServices cs;


    @BeforeEach
    public void setUp() {
        cs = new CourseServicesImpl(cr);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the database if needed
        cr.deleteAll();
    }

    @Test
    @Order(1)
    public void testaddCourse(){
        Course result=cs.addCourse(new Course());
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(2)
    public void testretrieveAllCourses(){
        List<Course> result=cs.retrieveAllCourses();
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(3)
    public void testupdateCourse(){
        Course result=cs.updateCourse(new Course());
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(4)
    public void testretrieveCourse(){
        Course c=new Course();
        Course result=cs.retrieveCourse(c.getNumCourse());
        Assertions.assertNotNull(result);
    }
}
