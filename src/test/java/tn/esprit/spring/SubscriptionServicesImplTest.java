package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.services.ISubscriptionServices;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class SubscriptionServicesImplTest {

    @Autowired
    ISubscriptionServices sub;
/*
    @Test
    @Order(1)
    public void testRetrieveSubscriptions() {
        List<Subscription> listSubscriptions = sub.retrieveSubscriptions();
        Assertions.assertEquals(0, listSubscriptions.size());
    }*/
}