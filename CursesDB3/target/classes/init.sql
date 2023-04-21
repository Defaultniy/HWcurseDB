create database curses3;

create table curses3.test
(
    timestamp DateTime64(3),
    source String,
    value Double
)

engine = AggregatingMergeTree
order by (source, timestamp);