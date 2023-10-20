package tn.esprit.spring.tests;

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
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceImplMock {

    @Mock
    ICourseRepository cr;

    @InjectMocks
    CourseServicesImpl cs;

    Course course= new Course(Long.valueOf(1),1, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, new Float(0),1,new HashSet<Registration>());

    List<Course> liste= new ArrayList<Course>(){
        {
            add(new Course(Long.valueOf(2),2, TypeCourse.COLLECTIVE_CHILDREN, Support.SNOWBOARD,new Float(0),2,null));
            add(new Course(Long.valueOf(3),3, TypeCourse.COLLECTIVE_CHILDREN, Support.SNOWBOARD,new Float(0),3,null));

        }

    };

    @Test
    public void testRetriveCourse(){
        Mockito.when(cr.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Course course1=cs.retrieveCourse(Long.valueOf(2));
        Assertions.assertNotNull(course1);
    }
}
