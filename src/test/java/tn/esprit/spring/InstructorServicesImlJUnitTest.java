package tn.esprit.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class InstructorServicesImlJUnitTest {

    @Autowired
    private IInstructorRepository instructorRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private InstructorServicesImpl instructorService;

    @BeforeEach
    public void setUp() {
        instructorService = new InstructorServicesImpl(instructorRepository, courseRepository);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the database if needed
        instructorRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    public void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        Instructor savedInstructor = instructorService.addInstructor(instructor);
        //check saved instructor
        assertNotNull(savedInstructor.getNumInstructor());

        //check if instructor exists and compare to our instructor object
        Instructor retrievedInstructor = instructorRepository.findById(savedInstructor.getNumInstructor()).orElse(null);
        assert retrievedInstructor != null;
        assertEquals("John", retrievedInstructor.getFirstName());
        assertEquals("Doe", retrievedInstructor.getLastName());
    }

    @Test
    public void testRetrieveAllInstructors() {
        Instructor instructor1 = new Instructor();
        instructor1.setFirstName("Maher");
        instructor1.setLastName("Gasmi");
        instructor1.setDateOfHire(LocalDate.now());

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Kinene");
        instructor2.setLastName("Dghim");
        instructor2.setDateOfHire(LocalDate.now());

        instructorRepository.save(instructor1);
        instructorRepository.save(instructor2);

        //get all instructors saved in the DB
        List<Instructor> instructors = instructorService.retrieveAllInstructors();

        //if list not empty check the number of retrieved instances
        assertNotNull(instructors);
        assertEquals(2, instructors.size());
    }

}


