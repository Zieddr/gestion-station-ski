import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class SubscriptionServicesImplMock {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveSubscriptionById() {
        Long numSubscription = 1L;
        Subscription expectedSubscription = new Subscription();
        expectedSubscription.setNumSub(numSubscription);

        // Configurer le comportement du mock
        when(subscriptionRepository.findById(numSubscription)).thenReturn(Optional.of(expectedSubscription));

        // Appeler la méthode que vous testez
        Subscription result = subscriptionServices.retrieveSubscriptionById(numSubscription);

        // Vérifier le résultat
        assertEquals(numSubscription, result.getNumSub());
    }

    @Test
    void testRetrieveSubscriptionByIdWhenNotExists() {
        Long numSubscription = 1L;

        // Configurer le comportement du mock lorsque l'abonnement n'existe pas
        when(subscriptionRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Appeler la méthode que vous testez
        Subscription result = subscriptionServices.retrieveSubscriptionById(numSubscription);

        // Vérifier que le résultat est null car l'abonnement n'existe pas
        assertEquals(null, result);
    }
}
