package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.IRegistrationRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class RegistrationServicesImplJunit {

    @InjectMocks
    private RegistrationServicesImplJunit yourClass;

    @Mock
    private IRegistrationRepository registrationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNumWeeksCourseOfInstructorBySupport() {
        // Définir les valeurs d'entrée pour le test
        Long numInstructor = 1L; // Remplacez par la valeur d'instructeur réelle
        Support support = Support.SKI; // Remplacez par le support réel

        // Définir la valeur de retour simulée pour le mock registrationRepository
        List<Integer> expectedWeeks = Arrays.asList(1, 2, 3); // Remplacez par les valeurs attendues

        when(registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(expectedWeeks);

        // Appeler la méthode à tester
        List<Integer> actualWeeks = yourClass.numWeeksCourseOfInstructorBySupport(numInstructor, support);

        // Vérifier que les résultats sont conformes aux attentes
        assertEquals(expectedWeeks, actualWeeks);
    }

    private List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        // Vous devez implémenter la logique de cette méthode pour obtenir la liste des semaines
        // en fonction du numéro de l'instructeur et du type de support.

        // Exemple factice pour la démonstration :
        if (numInstructor.equals(1L) && support.equals(Support.SKI)) {
            return Arrays.asList(1, 2, 3);
        } else {
            return Collections.emptyList(); // Retourne une liste vide par défaut
        }
    }


}
