package WithEM.Methods.Repositories;

import WithEM.Methods.Entity.GROUP_OF_CURSES;
import WithEM.Methods.Entity.STUDENTS;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface Groups_of_curses_repository {
    //сохранение студента
    GROUP_OF_CURSES save(GROUP_OF_CURSES groupOfCurses);

    //для поиска студента по его id
    Optional<GROUP_OF_CURSES> findById(long id);

    //для поиска всех студентов
    List<GROUP_OF_CURSES> findAll();

    //для поиска студента по его имени
    List<GROUP_OF_CURSES> findByName(List<STUDENTS> GROUP_CURSES);

    //обновление имени студента по его id (как пример, может быть ситуация со сменой фамилии студентом по каким-либо причинам
    void updateNameById(long id, int group_curses);

    //удаление по id и возможно не только студента
    void deleteById(long id);
}
