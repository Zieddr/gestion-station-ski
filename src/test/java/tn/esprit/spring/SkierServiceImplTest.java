package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.class)
public class SkierServiceImplTest {

    @Autowired
    ISkierRepository skierRepository;

    @Test
    @Order(1)
    public void retrieveAllSkiersTest() {

        Assertions.assertNotNull(skierRepository.findAll());
    }

    @Test
    @Order(2)
    public void addSkierTest() {

        Skier skier=new Skier();
        Assertions.assertNotNull(skierRepository.save(skier));
    }


}
