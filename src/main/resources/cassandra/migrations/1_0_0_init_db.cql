CREATE TYPE IF NOT EXISTS authority
(
    roleCode        text,
    roleDescription text,
);

CREATE TABLE IF NOT EXISTS user_details
(
    id               timeuuid,
    userName         text,
    password         text,
    createdAt        date,
    updateAt         date,
    firstName        text,
    lastName         text,
    email            text,
    phoneNumber      text,
    enabled          boolean,
    authorities      list<frozen<authority>>,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS student (
    id                 TIMEUUID,
    student_id         TEXT,
    firstname          TEXT,
    lastname           TEXT,
    email              TEXT,
    phone_number       TEXT,
    address            TEXT,
    date_of_birth      DATE,
    place_of_birth     TEXT,
    nationality        TEXT,
    sex                TEXT,
    level              TEXT,
    faculty            TEXT,
    group              TEXT,
    form_of_study      TEXT,
    head_teacher       TEXT,
    start_date         DATE,
    estimated_end_date DATE,
    active             BOOLEAN,
    PRIMARY KEY(id, student_id)
);

