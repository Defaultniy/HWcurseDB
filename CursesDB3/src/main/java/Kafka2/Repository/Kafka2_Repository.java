package Kafka2.Repository;


import Kafka2.Domain.Order_domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Kafka2_Repository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Order_domain save(Order_domain order){
        String sql = "insert into curses3.test (timestamp,item,,amount) " +
                "values (:timestamp,:source, :amount);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("timestamp",order.timestamp());
        parameterSource.addValue("item",order.item());
        parameterSource.addValue("amount",order.amount());
        namedParameterJdbcTemplate.update(sql, parameterSource);
        return order;
    }


    public List<Order_domain> findAll(){
        List<Order_domain> query =
                namedParameterJdbcTemplate.query("select * from curses3.test", new Kafka2_Repository.OrderMapper());
        return query;

    }

    @GetMapping(value = "/get_order")
    public List<Order_domain> get(){
        List<Order_domain> query =
                namedParameterJdbcTemplate.query("select * from curses3.test", new Kafka2_Repository.OrderMapper());
        return query;
    }


    public static class OrderMapper implements RowMapper<Order_domain> {
        @Override
        public Order_domain mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Order_domain(
                    rs.getTimestamp("timestamp").toInstant(),
                    rs.getNString("source"),
                    rs.getDouble("value")
            );
        }
    }
}
