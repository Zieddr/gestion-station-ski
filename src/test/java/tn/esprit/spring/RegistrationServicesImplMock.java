package tn.esprit.spring;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import java.util.Optional;


import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RegistrationServicesImplMock {

    @InjectMocks
    private tn.esprit.spring.services.RegistrationServicesImpl registrationServices;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository ICourseRepository;

    @InjectMocks
    private RegistrationServicesImplMock registrationServicesImplMock;



    @Test
    public void testAddRegistrationAndAssignToSkier() {
        // Créez un Skier fictif
        Long numSkier = 1L;
        Skier skier = new Skier();
        skier.setNumSkier(numSkier);

        // Créez un Registration fictif
        Registration registration = new Registration();
        registration.setSkier(skier);

        // Définissez les comportements simulés pour les méthodes de vos repositories
        Mockito.when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));
        Mockito.when(registrationRepository.save(Mockito.any(Registration.class))).thenReturn(registration);

        // Appelez la méthode que vous testez
        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, numSkier);

        // Assurez-vous que la méthode a correctement attribué le Skier et enregistré la Registration
        Assertions.assertEquals(skier, result.getSkier());

        // Vérifiez que les méthodes des repositories ont été appelées comme prévu
        Mockito.verify(skierRepository, Mockito.times(1)).findById(numSkier);
        Mockito.verify(registrationRepository, Mockito.times(1)).save(registration);
    }

}
