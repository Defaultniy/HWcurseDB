package WithEM.Methods.Repositories;

import WithEM.Methods.Entity.GROUP_OF_CURSES;
import WithEM.Methods.Entity.STUDENTS;
import WithEM.Methods.Repositories.Groups_of_curses_repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class Groups_of_curses_repositoryJpa implements Groups_of_curses_repository {

    @PersistenceContext
    private final EntityManager em;

    public Groups_of_curses_repositoryJpa(EntityManager em){
        this.em = em;
    }

    //сохранение студента
    @Transactional
    @Override
    public GROUP_OF_CURSES save(GROUP_OF_CURSES groupOfCurses){
        if (groupOfCurses.getID() <= 0) {
            em.persist(groupOfCurses);
            return groupOfCurses;
        } else {
            return em.merge(groupOfCurses);
        }
    }

    //для поиска студента по его id
    @Override
    public Optional<GROUP_OF_CURSES> findById(long id){
        return Optional.ofNullable(em.find(GROUP_OF_CURSES.class, id));
    }

    //для поиска всех студентов
    @Override
    public List<GROUP_OF_CURSES> findAll(){
        EntityGraph<?> entityGraph = em.getEntityGraph("student-courses-entity-graph");
        TypedQuery<GROUP_OF_CURSES> query = em.createQuery("select g from GROUP_OF_CURSES g join fetch g.ID", GROUP_OF_CURSES.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    //для поиска студента по его имени
    @Override
    public List<GROUP_OF_CURSES> findByName(List<STUDENTS> GROUP_CURSES){
        TypedQuery<GROUP_OF_CURSES> query = em.createQuery("select s " +
                        "from GROUP_OF_CURSES s " +
                        "where s.GROUP_CURSES = :GROUP_CURSES",
                GROUP_OF_CURSES.class);
        query.setParameter("GROUP_CURSES", GROUP_CURSES);
        return query.getResultList();
    }

    //обновление имени студента по его id (как пример, может быть ситуация со сменой фамилии студентом по каким-либо причинам
    //@Override
    public void updateNameById(long id, int group_curses){
        Query query = em.createQuery("update GROUP_OF_CURSES g " +
                "set g.GROUP_CURSES = :group_curses " +
                "where g.id = :id");
        query.setParameter("group_curses", group_curses);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    //удаление по id и возможно не только студента
    @Override
    public void deleteById(long id){
        Query query = em.createQuery("delete " +
                "from GROUP_OF_CURSES g " +
                "where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
