package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.SkierServicesImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SkierServiceImplTestMock {

    @MockBean
    ISkierRepository skierRepositoryr;

    @InjectMocks
    SkierServicesImpl skierServices;


    Skier skier = new Skier(Long.valueOf(1),"First name1","last name1", LocalDate.of(1999,1,1),"Tunis", new Subscription(),null,null);

    List<Skier> liste= new ArrayList<Skier>(){
        {
            add( new Skier(Long.valueOf(2),"First name2","last name2", LocalDate.of(1999,2,2),"Tunis", new Subscription(),null,null));
            add( new Skier(Long.valueOf(3),"First name3","last name3", LocalDate.of(1999,1,1),"Tunis", new Subscription(),null,null));

        }

    };


    @Test
    public void testRetriveSkier(){
        Mockito.when(skierRepositoryr.findById(Mockito.anyLong())).thenReturn(Optional.of(skier));
        Skier skier1=skierServices.retrieveSkier(Long.valueOf(2));
        Assertions.assertNotNull(skier1);
    }

    @Test
    public void testretrieveAllSkiers(){
        Mockito.when(skierRepositoryr.findAll()).thenReturn(liste);
        List<Skier> result=skierServices.retrieveAllSkiers();
        Assertions.assertNotNull(result);
    }


}
