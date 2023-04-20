create table GROUP_OF_CURSES
(
    ID            bigint,
    GROUP_CURSES  INT,
    YEAR_OF_ENTER INT,
    primary key (ID)
);

create table STUDENTS
(
    ID           bigint,
    FIO          varchar(200),
    ID_GROUP     INT REFERENCES GROUP_OF_CURSES(ID),
    CURSES_NAME  varchar(200),
    MARK         INT,
    primary key (ID)
);
