package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class InstructorServicesImplTest {

    @MockBean
    IInstructorRepository ir;

    @InjectMocks
    InstructorServicesImpl is;

    Instructor instructor = new Instructor(Long.valueOf(1), "John", "Doe", LocalDate.of(2020, 1, 1), new HashSet<>());

    List<Instructor> instructorList = new ArrayList<Instructor>() {
        {
            add(new Instructor(Long.valueOf(2), "Kinene", "Dghim", LocalDate.of(2023, 10, 16), new HashSet<>()));
            add(new Instructor(Long.valueOf(3), "Maher", "Gasmi", LocalDate.of(2023, 10, 15), new HashSet<>()));
        }
    };


    @Test
    public void testRetriveInstructor(){
        Mockito.when(ir.findById(Mockito.anyLong())).thenReturn(Optional.of(instructor));
        Instructor course1=is.retrieveInstructor(Long.valueOf(3));
        Assertions.assertNotNull(course1);
    }

}
