package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISkierServices;
import tn.esprit.spring.services.ISubscriptionServices;
import java.time.LocalDate;

//@SpringBootTest
//@TestMethodOrder(MethodOrderer.class)
public class SkierServiceImplTest {
/*
    @Autowired
    ISkierServices skierServices;

    @Autowired
    ISubscriptionServices subscriptionServices;

    @Test
    @Order(1)
    public void addSkierTest() {
        Subscription s = subscriptionServices.addSubscription(new Subscription(1L,LocalDate.of(1999,1,1),LocalDate.of(1999,1,1),1F, TypeSubscription.ANNUAL));
        Skier skier = new Skier(Long.valueOf(1),"First name1","last name1", LocalDate.of(1999,1,1),"Tunis", s,null,null);
        Assertions.assertNotNull(skierServices.addSkier(skier));
    }
    @Test
    @Order(2)
    public void retrieveAllSkiersTest() {
        Assertions.assertNotNull(skierServices.retrieveAllSkiers());
    }

    @Test
    @Order(3)
    public void retrieveSkierTest() {
        Skier skier = new Skier(Long.valueOf(1),"First name1","last name1", LocalDate.of(1999,1,1),"Tunis", null,null,null);
        Assertions.assertNotNull(skierServices.retrieveSkier(skier.getNumSkier()));
    }
*/



}
