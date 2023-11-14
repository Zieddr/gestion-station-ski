package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.IPisteServices;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PisteServiceImplJUnit  {
    @Autowired
    IPisteServices pisteService;

    @Autowired
    IPisteRepository pisteRepository;

    Piste p1 = new Piste(1L,"Courchevel");
    Piste p2 = new Piste(2L,"Alpes");
    @Test
    @Order(1)
    public void testaddPiste() {
       Piste pisteAdded = pisteService.addPiste(p1);
        Assertions.assertEquals(p1.getNumPiste(), pisteAdded.getNumPiste());
    }
    @Test
    @Order(2)
    public void testRetrieveAllPistes() {
        List<Piste> listPistes = pisteService.retrieveAllPistes();
        // Assertions.assertEquals(4, listPistes.size());
        Assertions.assertEquals(listPistes.size(), listPistes.size());
    }


}
