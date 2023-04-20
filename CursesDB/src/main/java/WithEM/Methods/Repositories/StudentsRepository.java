package WithEM.Methods.Repositories;

import WithEM.Methods.Entity.STUDENTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentsRepository extends JpaRepository<STUDENTS, Long> {
    @Query("select avg(s.MARK) from STUDENTS s GROUP BY s.CURSES_NAME")
    double avgmark();

}
