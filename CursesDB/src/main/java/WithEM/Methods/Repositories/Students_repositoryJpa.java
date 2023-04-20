package WithEM.Methods.Repositories;

import WithEM.Methods.Entity.STUDENTS;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class Students_repositoryJpa implements Students_repository {

    @PersistenceContext
    private final EntityManager em;

    public Students_repositoryJpa(EntityManager em){
        this.em = em;
    }

    @Transactional
    @Override
    public STUDENTS save(STUDENTS students){
        if (students.getID() <= 0) {
            em.persist(students);
            return students;
        } else {
            return em.merge(students);
        }
    }

    @Override
    public Optional<STUDENTS> findById(long id) {
        return Optional.ofNullable(em.find(STUDENTS.class, id));
    }

    @Override
    public List<STUDENTS> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("student-courses-entity-graph");
        TypedQuery<STUDENTS> query = em.createQuery("select s from STUDENTS s join fetch s.ID_GROUP", STUDENTS.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<STUDENTS> findByName(String FIO) {
        TypedQuery<STUDENTS> query = em.createQuery("select s " +
                        "from STUDENTS s " +
                        "where s.FIO = :FIO",
                STUDENTS.class);
        query.setParameter("FIO", FIO);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String FIO) {
        Query query = em.createQuery("update STUDENTS s " +
                "set s.FIO = :name " +
                "where s.id = :id");
        query.setParameter("name", FIO);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from STUDENTS s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
