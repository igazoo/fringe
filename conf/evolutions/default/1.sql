# --- !Ups
create table user (
    id int auto_increment primary key,
    name varchar(255) not null,
    message varchar(255) not null,

);
insert into user values (default, '太郎','友達とタピオカなう');
insert into user values (default, 'ともか','今日は映画に行きます');
insert into user values (default, 'ゆうき','私はお腹が空いてる');


# --- !Downs
drop table user
