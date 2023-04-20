package WithEM;

import WithEM.Methods.Entity.GROUP_OF_CURSES;
import WithEM.Methods.Entity.STUDENTS;
import WithEM.Methods.Repositories.Groups_of_curses_repository;
import WithEM.Methods.Service.Students_service;
import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import WithEM.Methods.Repositories.Students_repository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
//@SpringBootApplication
@SpringBootApplication
@EnableJpaRepositories
public class Main {
    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);


        Students_repository studentsRepository = applicationContext.getBean(Students_repository.class);

        STUDENTS student1 = new STUDENTS();
        student1.setFIO("Ivanov Ivan Jovanovich");
        student1.setMARK(5);
        student1.setCURSES_NAME("DB");

        List<STUDENTS> studentsList = List.of(student1);

        Groups_of_curses_repository groupsOfCursesRepository = applicationContext.getBean((Groups_of_curses_repository.class));
        GROUP_OF_CURSES groupOfCurses1 = new GROUP_OF_CURSES();
        groupOfCurses1.setGROUP_CURSES(studentsList);
        groupOfCurses1.setYEAR_OF_ENTER(2023);

        groupsOfCursesRepository.save(groupOfCurses1);
        student1.setID_GROUP(groupOfCurses1.getID());
        studentsRepository.save(student1);



        Students_service studentsService = applicationContext.getBean(Students_service.class);
        Iterable<Double> marked = studentsService.AvgMarkOnCurs();
        System.out.println(marked);

        Iterable<STUDENTS> fios = studentsRepository.findByName("III");
        System.out.println(fios);

        Optional<STUDENTS> studbyid = studentsRepository.findById(1);
        System.out.println(studbyid);


        Console.main(args);
    }
}