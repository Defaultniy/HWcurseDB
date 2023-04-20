package WithNJO.Methods.DAO;

import WithNJO.Methods.Entity.GROUP_OF_CURSES;
import WithNJO.Methods.Entity.STUDENTS;

import java.util.List;

public interface GroupofcursesDAO {
    int count();

    void insert(GROUP_OF_CURSES groupOfCurses);

    GROUP_OF_CURSES getById(long id);

    List<GROUP_OF_CURSES> getAll();

    void deleteById(long id);
}
