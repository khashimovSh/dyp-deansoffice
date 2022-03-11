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

