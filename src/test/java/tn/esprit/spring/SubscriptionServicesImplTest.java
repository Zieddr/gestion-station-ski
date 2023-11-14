import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SubscriptionServicesImplTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddSubscription() {
        // Créer un objet Subscription pour le test
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());

        // Configurer le mock pour retourner l'objet Subscription sauvegardé
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        // Appeler la méthode que vous testez
        Subscription result = subscriptionServices.addSubscription(subscription);

        // Vérifier que la date de fin a été correctement calculée en fonction du type de subscription
        switch (subscription.getTypeSub()) {
            case ANNUAL:
                assertEquals(subscription.getStartDate().plusYears(1), result.getEndDate());
                break;
            case SEMESTRIEL:
                assertEquals(subscription.getStartDate().plusMonths(6), result.getEndDate());
                break;
            case MONTHLY:
                assertEquals(subscription.getStartDate().plusMonths(1), result.getEndDate());
                break;
        }

        // Vérifier que la méthode de sauvegarde a été appelée avec l'objet Subscription
        // (cela garantit que la logique de la méthode addSubscription est correcte)
        // Notez que vous pouvez également utiliser ArgumentCaptor pour capturer l'argument passé à save
        // et effectuer des assertions plus détaillées.
        // verify(subscriptionRepository).save(any(Subscription.class));
    }
}
