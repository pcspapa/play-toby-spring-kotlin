# H2 database

## Download

http://www.h2database.com/html/main.html

## Run H2

```shell
$ chmod 755 ./bin/h2.sh
$ ./bin/h2.sh
```

## Create DB

- Generic H2 (Server)
- jdbc:h2:tcp://localhost/~/toby-spring

## Create table

```h2
create table users
(
    id       varchar(10) primary key,
    name     varchar(20) not null,
    password varchar(10) not null
);
```

## Verification sql

```h2
select *
from users;
```

## Deletion sql

```h2
delete
from users;
```

# UserDao

## Surround try/catch/finally

## Strategy pattern

- Context method, Strategy interface & class

## Move strategy implementation to local class

## Move strategy local class to anonymous inner class