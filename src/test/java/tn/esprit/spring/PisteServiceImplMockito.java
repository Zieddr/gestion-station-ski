package tn.esprit.spring;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = GestionStationSkiApplication.class)
@ExtendWith(MockitoExtension.class)
public class PisteServiceImplMockito {
    @Mock(lenient = true)

    IPisteRepository pisteRepository;

        @InjectMocks
        PisteServicesImpl pisteService;

        Piste p1 = new Piste(1L,"Courchevel");
        Piste p2 = new Piste(2L,"Alpes");

        List<Piste> listPistes = new ArrayList<Piste>() {{
            add(p1);
            add(p2);
            add(new Piste(3L,"xx"));
            add(new Piste(4L,"yy"));
        }};

        @Test
        public void testaddPiste(){
            Mockito.when(pisteRepository.save(p1)).thenReturn(p1);
            Piste piste1 = pisteService.addPiste(p1);
            //assertNotNull(p1);
            Mockito.verify(pisteRepository).save(Mockito.any(Piste.class));
        }

        @Test
        public void testretrieveAllPistes() {
            Mockito.when(pisteRepository.findAll()).thenReturn(listPistes);
            List<Piste> listPistes1 = pisteService.retrieveAllPistes();
            assertEquals(listPistes.size(), listPistes1.size());

        }


    }


