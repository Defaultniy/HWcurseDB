package WithNJO.Methods.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//ID BIGINT PRIMARY KEY, FIO VARCHAR(255), GROUP INT REFERENCES GROUP_OF_CURSES(GROUP),CURSES_NAME VARCHAR(255), MARK INT
//класс не курсов а людей в них
@Data
@Entity
@Table(name = "STUDENTS")
@NoArgsConstructor
@AllArgsConstructor
public class STUDENTS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @Column(name = "FIO")
    private String FIO;

    //разобраться с каскадным удалением, где детач, где ол
    //@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    //@JoinColumn(name = "ID_GROUP")
    //private GROUP_OF_CURSES ID_GROUP;

    @Column(name = "ID_GROUP")
    private long ID_GROUP;


    @Column(name = "CURSES_NAME")
    private String CURSES_NAME;

    @Column(name = "MARK")
    private int MARK;

}
