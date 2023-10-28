package tn.esprit.spring;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RegistrationServicesImpl {

    @InjectMocks
    private tn.esprit.spring.services.RegistrationServicesImpl registrationServices;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRegistrationAndAssignToSkier() {
        Skier skier = new Skier();
        Registration registration = new Registration();

        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
        when(registrationRepository.save(registration)).thenReturn(registration);

        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, 1L);

        assertEquals(skier, result.getSkier());
    }

    @Test
    public void testAssignRegistrationToCourse() {
        Registration registration = new Registration();
        Course course = new Course();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(registrationRepository.save(registration)).thenReturn(registration);

        val result = registrationServices.assignRegistrationToCourse(1L, 1L);

        assertEquals(course, result.getCourse());
    }
}
