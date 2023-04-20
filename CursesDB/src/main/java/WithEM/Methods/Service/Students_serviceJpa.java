package WithEM.Methods.Service;


import WithEM.Methods.Entity.STUDENTS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Service
public class Students_serviceJpa implements Students_service {

    @PersistenceContext
    private final EntityManager em;

    public Students_serviceJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Double> AvgMarkOnCurs(){
        //TypedQuery<STUDENTS> query = em.createQuery("select avg(s.MARK) from STUDENTS s GROUP BY s.CURSES_NAME", STUDENTS.class);
        //return query.getResultList();
        TypedQuery<Double> query = em.createQuery("select avg(s.MARK) from STUDENTS s GROUP BY s.CURSES_NAME", Double.class);
        return query.getResultList();
    }
/*
    public List<STUDENTS> AvgMarkOnCurses(){
        TypedQuery<STUDENTS> query = em.createQuery("select ID, FIO, ID_GROUP, CURSES_NAME, MARK, avg(s.MARK) from STUDENTS s GROUP BY s.CURSES_NAME", STUDENTS.class);
        return query.getResultList();
    }
    */
}
