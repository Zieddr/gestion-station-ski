package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImplMock implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

   /* @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }*/


    @Override
   public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
       // Rechercher la registration par son numéro
       Optional<Registration> optionalRegistration = registrationRepository.findById(numRegistration);
       if (optionalRegistration.isEmpty()) {
           // Gérer le cas où la registration n'est pas trouvée
           throw new RegistrationNotFoundException("Registration not found with ID: " + numRegistration);
       }

       // Rechercher le course par son numéro
       Optional<Course> optionalCourse = courseRepository.findById(numCourse);
       if (optionalCourse.isEmpty()) {
           // Gérer le cas où le course n'est pas trouvé
           throw new CourseNotFoundException("Course not found with ID: " + numCourse);
       }

       // Obtenir les objets registration et course à partir des Optionals
       Registration registration = optionalRegistration.get();
       Course course = optionalCourse.get();

       // Affecter le course à la registration
       registration.setCourse(course);

       // Enregistrer la registration mise à jour
       return registrationRepository.save(registration);
   }



    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse()) >=1){
            log.info("Sorry, you're already register to this course of the week :" + registration.getNumWeek());
            return null;
        }

        int ageSkieur = Period.between(skier.getDateOfBirth(), LocalDate.now()).getYears();
        log.info("Age " + ageSkieur);

        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                log.info("add without tests");
                return assignRegistration(registration, skier, course);

            case COLLECTIVE_CHILDREN:
                if (ageSkieur < 16) {
                    log.info("Ok CHILD !");
                    if (registrationRepository.countByCourseAndNumWeek(course, registration.getNumWeek()) < 6) {
                        log.info("Course successfully added !");
                        return assignRegistration(registration, skier, course);
                    } else {
                        log.info("Full Course ! Please choose another week to register !");
                        return null;
                    }
                }
                else{
                    log.info("Sorry, your age doesn't allow you to register for this course ! \n Try to Register to a Collective Adult Course...");
                }
                break;

            default:
                if (ageSkieur >= 16) {
                    log.info("Ok ADULT !");
                    if (registrationRepository.countByCourseAndNumWeek(course, registration.getNumWeek()) < 6) {
                        log.info("Course successfully added !");
                        return assignRegistration(registration, skier, course);
                    } else {
                        log.info("Full Course ! Please choose another week to register !");
                        return null;
                    }
                }
                log.info("Sorry, your age doesn't allow you to register for this course ! \n Try to Register to a Collective Child Course...");
        }
        return registration;
    }
    private Registration assignRegistration (Registration registration, Skier skier, Course course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
