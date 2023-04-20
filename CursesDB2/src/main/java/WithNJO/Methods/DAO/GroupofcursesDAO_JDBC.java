package WithNJO.Methods.DAO;

import WithNJO.Methods.Entity.GROUP_OF_CURSES;
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
public class GroupofcursesDAO_JDBC implements GroupofcursesDAO {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GroupofcursesDAO_JDBC(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        // Это просто оставили, чтобы не переписывать код
        // В идеале всё должно быть на NamedParameterJdbcOperations
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public int count(){
        Integer count = jdbc.queryForObject("select count(*) from GROUP_OF_CURSES", Integer.class);
        return count == null? 0: count;
    }

    public void insert(GROUP_OF_CURSES groupOfCurses){
        namedParameterJdbcOperations.update("insert into GROUP_OF_CURSES (ID, YEAR_OF_ENTER) values (:ID, :YEAR_OF_ENTER)",
                Map.of("ID", groupOfCurses.getID(), "YEAR_OF_ENTER", groupOfCurses.getYEAR_OF_ENTER()));
    }

    public GROUP_OF_CURSES getById(long id){
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, GROUP_CURSES, YEAR_OF_ENTER from GROUP_OF_CURSES where id = :id", params, new GroupofcursesDAO_JDBC.GroupMapper()
        );
    }

    public List<GROUP_OF_CURSES> getAll(){
        return jdbc.query("select ID, GROUP_CURSES, YEAR_OF_ENTER from GROUP_OF_CURSES", new GroupofcursesDAO_JDBC.GroupMapper());
    }

    public void deleteById(long id){
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from GROUP_OF_CURSES where ID = :id", params
        );
    }


    private static class GroupMapper implements RowMapper<GROUP_OF_CURSES> {

        @Override
        public GROUP_OF_CURSES mapRow(ResultSet resultSet, int i) throws SQLException {
            long ID = resultSet.getLong("ID");
            List<STUDENTS> GROUP_CURSES = resultSet.getObject("GROUP_CURSES", List.class);
            int YEAR_OF_ENTER = resultSet.getInt("YEAR_OF_ENTER");
            return new GROUP_OF_CURSES(ID, GROUP_CURSES, YEAR_OF_ENTER);
        }
    }
}
