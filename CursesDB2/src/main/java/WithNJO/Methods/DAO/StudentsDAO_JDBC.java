package WithNJO.Methods.DAO;

import WithNJO.Methods.Entity.STUDENTS;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class StudentsDAO_JDBC implements StudentsDAO{

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StudentsDAO_JDBC(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        // Это просто оставили, чтобы не переписывать код
        // В идеале всё должно быть на NamedParameterJdbcOperations
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from STUDENTS", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(STUDENTS students) {
        namedParameterJdbcOperations.update("insert into STUDENTS (ID, FIO, ID_GROUP, CURSES_NAME, MARK) values (:ID, :FIO, :ID_GROUP, :CURSES_NAME, :MARK)",
                Map.of("ID", students.getID(), "FIO", students.getFIO(), "ID_GROUP", students.getID_GROUP(), "CURSES_NAME", students.getCURSES_NAME(), "MARK", students.getMARK()));
    }

    @Override
    public STUDENTS getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, FIO, ID_GROUP, CURSES_NAME, MARK from STUDENTS where id = :id", params, new StudentMapper()
        );
    }

    @Override
    public List<STUDENTS> getAll() {

        return jdbc.query("select ID, FIO, ID_GROUP, CURSES_NAME, MARK from STUDENTS", new StudentMapper());

    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from STUDENTS where id = :id", params
        );
    }

    private static class StudentMapper implements RowMapper<STUDENTS> {

        @Override
        public STUDENTS mapRow(ResultSet resultSet, int i) throws SQLException {
            long ID = resultSet.getLong("ID");
            String FIO = resultSet.getString("FIO");
            int ID_GROUP = resultSet.getInt("ID_GROUP");
            String CURSES_NAME = resultSet.getString("CURSES_NAME");
            int MARK = resultSet.getInt("MARK");
            return new STUDENTS(ID, FIO, ID_GROUP, CURSES_NAME, MARK);
        }
    }
}
