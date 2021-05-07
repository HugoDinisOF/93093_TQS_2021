CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name varchar(255) not null,
    age integer not null,
    nmec integer not null,
    nationality varchar(255) not null,
    course varchar(255) not null
);