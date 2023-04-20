package WithEM.Methods.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "GROUP_OF_CURSES")
@NoArgsConstructor
@AllArgsConstructor
public class GROUP_OF_CURSES {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "ID_GROUP")
    private List<STUDENTS> GROUP_CURSES;


    @Column (name = "YEAR_OF_ENTER")
    private int YEAR_OF_ENTER;
}
