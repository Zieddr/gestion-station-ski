package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.RegistrationServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RegistrationServicesImplTests {

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private RegistrationServicesImpl registrationServices;

    @Test
    public void testAddRegistrationAndAssignToSkier() {
        Skier skier = new Skier();
        skier.setNumSkier(123456L);
        when(skierRepository.findById(123456L)).thenReturn(Optional.of(skier));

        Registration registration = new Registration();
        registration.setSkier(null);

        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, 123456L);

        verify(skierRepository).findById(123456L);
        assertEquals(skier, registration.getSkier());
        verify(registrationRepository).save(registration);
        assertEquals(result, registration);
    }

    @Test
    void testAssignRegistration() {
        Skier skier = new Skier();
        skier.setNumSkier(123456L);
        Course course = new Course();
        course.setNumCourse(123456L);
        Registration registration = new Registration();
        registration.setSkier(null);
        registration.setCourse(null);

        ArgumentCaptor<Registration> registrationCaptor = ArgumentCaptor.forClass(Registration.class);
        when(registrationRepository.save(registrationCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        Registration result = registrationServices.assignRegistration(registration, skier, course);

        assertEquals(skier, registrationCaptor.getValue().getSkier());
        assertEquals(course, registrationCaptor.getValue().getCourse());
        verify(registrationRepository).save(registrationCaptor.capture());
        assertEquals(result, registrationCaptor.getValue());
    }
}
