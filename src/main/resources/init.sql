create table category
(
    id    bigserial
        primary key,
    title varchar(255)
);

alter table category
    owner to postgres;

create table product
(
    id          bigserial
        primary key,
    description varchar(255),
    image       varchar(255),
    price       bigint,
    title       varchar(255),
    category_id bigint
        constraint product_category_fk
            references category
);

alter table product
    owner to postgres;