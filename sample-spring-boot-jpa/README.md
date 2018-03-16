```
CREATE DATABASE sample_db CHARACTER SET utf8mb4;
```

```
create table sample (
  id int primary key auto_increment,
  message varchar(255),
  updated timestamp NOT NULL
);
```

```
insert into sample
(message, updated)
VALUES
('aaa', now()),
(null, now());
```
