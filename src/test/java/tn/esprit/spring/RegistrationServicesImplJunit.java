package tn.esprit.spring;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.services.RegistrationServicesImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class RegistrationServicesImplJunit {

    @InjectMocks
    private RegistrationServicesImpl registrationService;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void testAssignRegistrationToCourse() {
        // Créez un Registration fictif
        Long numRegistration = 1L;
        Registration registration = new Registration();
        registration.setNumRegistration(numRegistration);

        // Créez un Course fictif
        Long numCourse = 2L;
        Course course = new Course();
        course.setNumCourse(numCourse);

        // Définissez les comportements simulés pour les méthodes de vos repositories
        when(registrationRepository.findById(numRegistration)).thenReturn(Optional.of(registration));
        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));
        when(registrationRepository.save(Mockito.any(Registration.class))).thenReturn(registration);

        // Appelez la méthode que vous testez
        Registration result = registrationService.assignRegistrationToCourse(numRegistration, numCourse);

        // Assurez-vous que la méthode a correctement attribué le Course à la Registration
        assertEquals(course, result.getCourse());

        // Vérifiez que les méthodes des repositories ont été appelées comme prévu
        Mockito.verify(registrationRepository, Mockito.times(1)).findById(numRegistration);
        Mockito.verify(courseRepository, Mockito.times(1)).findById(numCourse);
        Mockito.verify(registrationRepository, Mockito.times(1)).save(registration);
    }

    // Ajoutez d'autres tests au besoin
}
