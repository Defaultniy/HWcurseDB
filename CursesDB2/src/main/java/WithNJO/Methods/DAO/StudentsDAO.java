package WithNJO.Methods.DAO;

import WithNJO.Methods.Entity.STUDENTS;

import java.util.List;

public interface StudentsDAO {
    int count();

    void insert(STUDENTS students);

    STUDENTS getById(long id);

    List<STUDENTS> getAll();

    void deleteById(long id);
}
