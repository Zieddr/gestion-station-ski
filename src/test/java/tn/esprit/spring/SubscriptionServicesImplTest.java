package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SubscriptionServicesImplTest {

    @InjectMocks
    private SubscriptionServicesImpl subscriptionService;

    @MockBean
    private ISubscriptionRepository subscriptionRepository;

    @MockBean
    private ISkierRepository skierRepository;

    @Test
    void addSubscription() {
        // Create a mock Subscription
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        // Configure the behavior of the mock subscriptionRepository for the save method
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class)))
                .thenReturn(subscription);

        // Call the addSubscription method
        Subscription addedSubscription = subscriptionService.addSubscription(subscription);

        // Verify that the save method of the mock subscriptionRepository was called once
        verify(subscriptionRepository, times(1)).save(Mockito.any(Subscription.class));

        // Add assertions to check the result
        assertEquals(TypeSubscription.ANNUAL, addedSubscription.getTypeSub());
    }

    @Test
    void updateSubscription() {
        // Create a mock Subscription
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.MONTHLY); // You can customize the fields as needed

        // Configure the behavior of the mock subscriptionRepository for the save method
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class)))
                .thenReturn(subscription);

        // Call the updateSubscription method
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);

        // Verify that the save method of the mock subscriptionRepository was called once
        verify(subscriptionRepository, times(1)).save(subscription);

        // Add your assertions to check the result
        assertEquals(TypeSubscription.MONTHLY, updatedSubscription.getTypeSub()); // Add more assertions as needed
    }
}
