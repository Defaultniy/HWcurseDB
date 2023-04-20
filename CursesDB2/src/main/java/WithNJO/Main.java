package WithNJO;

import WithNJO.Methods.DAO.GroupofcursesDAO;
import WithNJO.Methods.DAO.StudentsDAO;
import WithNJO.Methods.Entity.GROUP_OF_CURSES;
import WithNJO.Methods.Entity.STUDENTS;
import WithNJO.Methods.Service.Students_service;
import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
public class Main {
    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        StudentsDAO studentsDAO = context.getBean(StudentsDAO.class);

        GroupofcursesDAO groupofcursesDAO = context.getBean(GroupofcursesDAO.class);

        STUDENTS student1 = new STUDENTS();
        STUDENTS student2 = new STUDENTS();
        student1.setID(1);
        student1.setFIO("Ivanov Ivan Jovanovich");
        student1.setMARK(5);
        student1.setCURSES_NAME("DB");
        student2.setID(2);
        student2.setFIO("QQQ");
        student2.setCURSES_NAME("WS");
        student2.setMARK(4);

        List<STUDENTS> studentsList = List.of(student1);

        GROUP_OF_CURSES groupOfCurses1 = new GROUP_OF_CURSES();
        groupOfCurses1.setGROUP_CURSES(studentsList);
        groupOfCurses1.setYEAR_OF_ENTER(2023);
        student1.setID_GROUP(groupOfCurses1.getID());
        student2.setID_GROUP(groupOfCurses1.getID());

        groupofcursesDAO.insert(groupOfCurses1);
        studentsDAO.insert(student1);
        studentsDAO.insert(student2);

        Students_service studentsService = context.getBean(Students_service.class);
        Iterable<Double> marked = studentsService.AvgMarkOnCurs();
        System.out.println(marked);



        Console.main(args);
    }
}