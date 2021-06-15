create table "expression"
(
    "id"        integer not null primary key autoincrement,
    "text"      text,
    "result"  double

);

drop table expression;
select * from expression order by id desc limit 10;

