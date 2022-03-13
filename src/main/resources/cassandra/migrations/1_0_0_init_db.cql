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

CREATE TABLE IF NOT EXISTS student
(
    id                  timeuuid,
    firstName           text,
    lastName            text,
    major               text,
    dateOfBirth         timestamp,
    startDateOfStudies  timestamp,
    nationality         text,
    placeOfBirth        text,
    active              boolean,
    primary key (id)
);

