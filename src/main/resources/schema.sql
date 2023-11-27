create table if not exists city
(
    id         bigint    not null
        primary key,
    enabled    boolean,
    name       varchar(255),
    created_on timestamp not null,
    updated_on timestamp not null
);

create sequence if not exists city_seq
    increment by 50;

create table if not exists currency
(
    id             bigint    not null
        primary key,
    code           varchar(4),
    decimal_places integer,
    description    varchar(30),
    enabled        boolean,
    created_on     timestamp not null,
    updated_on     timestamp not null
);

create sequence if not exists currency_seq
    increment by 50;

create table if not exists country
(
    id          bigint      not null
        primary key,
    code        varchar(4)  not null,
    enabled     boolean     not null,
    locale      varchar(6)  not null,
    name        varchar(30) not null,
    time_zone   varchar(10) not null,
    currency_id bigint      not null,
    created_on  timestamp   not null,
    updated_on  timestamp   not null,
    foreign key (currency_id) references currency (id)
);

create sequence if not exists country_seq
    increment by 50;

create table if not exists state
(
    id         bigint    not null
        primary key,
    code       varchar(255),
    enabled    boolean,
    name       varchar(255),
    country_id bigint    not null,
    created_on timestamp not null,
    updated_on timestamp not null,
    foreign key (country_id) references country (id)
);

create sequence if not exists state_seq
    increment by 50;