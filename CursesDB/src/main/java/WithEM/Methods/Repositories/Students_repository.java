package WithEM.Methods.Repositories;

import WithEM.Methods.Entity.STUDENTS;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface Students_repository {
    //сохранение студента
    STUDENTS save(STUDENTS students);

    //для поиска студента по его id
    Optional<STUDENTS> findById(long id);

    //для поиска всех студентов
    @EntityGraph(attributePaths = "STUDENTS")
    List<STUDENTS> findAll();

    //для поиска студента по его имени
    List<STUDENTS> findByName(String FIO);

    //обновление имени студента по его id (как пример, может быть ситуация со сменой фамилии студентом по каким-либо причинам
    void updateNameById(long id, String FIO);

    //удаление по id и возможно не только студента
    void deleteById(long id);

}
