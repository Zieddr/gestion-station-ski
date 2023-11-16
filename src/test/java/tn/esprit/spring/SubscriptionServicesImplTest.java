package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SubscriptionServicesImplTest {

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSubscriptionByType() {
        // Mocking data
        TypeSubscription type = TypeSubscription.ANNUAL;
        Subscription subscription1 = new Subscription(/* initialize your subscription object */);
        Subscription subscription2 = new Subscription(/* initialize another subscription object */);
        Set<Subscription> expectedSubscriptions = new HashSet<>();
        expectedSubscriptions.add(subscription1);
        expectedSubscriptions.add(subscription2);

        // Mocking repository behavior
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(expectedSubscriptions);

        // Calling the service method
        Set<Subscription> actualSubscriptions = subscriptionServices.getSubscriptionByType(type);

        // Assertions
        assertEquals(expectedSubscriptions, actualSubscriptions);
    }
}
