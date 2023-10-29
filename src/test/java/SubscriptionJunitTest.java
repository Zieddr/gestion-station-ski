package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;



//@SpringBootTest
//@ExtendWith(MockitoExtension.class)

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscriptionJunitTest {

    @Autowired
    private SubscriptionServicesImpl subscriptionService;

    @Autowired
    private ISkierRepository skierRepository;

    @Autowired
    private ISubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setUp() {
        subscriptionService = new SubscriptionServicesImpl(subscriptionRepository, skierRepository);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the database if needed
        subscriptionRepository.deleteAll();
        skierRepository.deleteAll();
    }

    /*
    @Test
    public void testAddSubscription() {
        // 1. Create an object Subscription for the test
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());

        // 2. Mock any dependencies if needed (optional)
        // If your service has dependencies, you can mock them using Mockito like this:
        // SomeDependency mockedDependency = mock(SomeDependency.class);
        // when(mockedDependency.someMethod()).thenReturn(someValue);
        // subscriptionService.setDependency(mockedDependency);

        // 3. Call the method to be tested
        Subscription addedSubscription = subscriptionService.addSubscription(subscription);

        // 4. Perform assertions to check the result

        // 4.1 Check if the result is not null
        assertNotNull(addedSubscription);

        // 4.2 Check if the subscription type is ANNUAL
        assertEquals(TypeSubscription.ANNUAL, addedSubscription.getTypeSub());

        // 4.3 Add other assertions as needed based on your requirements

        // For example, you can add more assertions to test other properties of the added subscription:
        assertEquals(LocalDate.now(), addedSubscription.getStartDate());
        Integer expectedValue = null;
        assertEquals(expectedValue, addedSubscription.getStartDate());
    }

    // Write similar test methods for other service methods

    // It's a good practice to write separate test methods for each method you want to test in your service.
*/

    @Test
    @Order(1)
    public void addSubscription() {

        Subscription subscription=new Subscription();
        Assertions.assertNotNull(subscriptionRepository.save(subscription));
    }


}

