package tn.esprit.spring;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.ISkierServices;
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


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testRetriveCourse(){
        Mockito.when(skierRepositoryr.findById(Mockito.anyLong())).thenReturn(Optional.of(skier));
        Skier skier1=skierServices.retrieveSkier(Long.valueOf(2));
        Assertions.assertNotNull(skier1);
    }

    @Test
    public void testretrieveAllCourses(){
        Mockito.when(skierRepositoryr.findAll()).thenReturn(liste);
        List<Skier> result=skierServices.retrieveAllSkiers();
        Assertions.assertNotNull(result);
    }


    @Test
    public void testaddCourse(){
        Skier skier1 = new Skier();
        Mockito.when(skierRepositoryr.save(skier1)).thenReturn(skier1);
        Skier result=skierServices.addSkier(skier1);
        Assertions.assertNotNull(result);
    }

}
