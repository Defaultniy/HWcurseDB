package WithNJO.Methods.Service;

import WithNJO.Methods.DAO.GroupofcursesDAO_JDBC;
import WithNJO.Methods.DAO.StudentsDAO_JDBC;
import WithNJO.Methods.Entity.GROUP_OF_CURSES;
import WithNJO.Methods.Entity.STUDENTS;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
public class Student_serviceJDBC implements Students_service {


    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public Student_serviceJDBC(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        // Это просто оставили, чтобы не переписывать код
        // В идеале всё должно быть на NamedParameterJdbcOperations
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Double>  AvgMarkOnCurs(){
        return jdbc.query("select avg(MARK) from STUDENTS GROUP BY CURSES_NAME", new StudentMapper());
    }

    private static class StudentMapper implements RowMapper<Double> {

        /*@Override
        public STUDENTS mapRow(ResultSet resultSet, int i) throws SQLException {
            long ID = resultSet.getLong("ID");
            String FIO = resultSet.getString("FIO");
            int ID_GROUP = resultSet.getInt("ID_GROUP");
            String CURSES_NAME = resultSet.getString("CURSES_NAME");
            int MARK = resultSet.getInt("MARK");
            return new STUDENTS(ID, FIO, ID_GROUP, CURSES_NAME, MARK);
        }*/
        @Override
        public Double mapRow(ResultSet resultSet, int i) throws SQLException{
            return resultSet.getDouble("avg(MARK)");
        }
    }
}
