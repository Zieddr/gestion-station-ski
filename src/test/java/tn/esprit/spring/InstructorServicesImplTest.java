package tn.esprit.spring;

import org.junit.jupiter.api.*;
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
@ExtendWith(MockitoExtension.class)
class InstructorServicesImplTest {
    @MockBean
    IInstructorRepository ir;

    @InjectMocks
    InstructorServicesImpl is;

    Instructor instructor;

    @BeforeEach
    void setUp() {
        instructor = new Instructor(Long.valueOf(1), "John", "Doe", LocalDate.of(2020, 1, 1), new HashSet<>());
    }

    @Test
    public void testRetrieveInstructor() {
        Mockito.when(ir.findById(Mockito.anyLong())).thenReturn(Optional.of(instructor));
        Instructor result = is.retrieveInstructor(Long.valueOf(1));
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John", result.getFirstName());
        Assertions.assertEquals("Doe", result.getLastName());
    }

}
