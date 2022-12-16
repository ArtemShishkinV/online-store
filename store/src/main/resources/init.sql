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



create table users
(
    id           bigserial
        primary key,
    email        varchar(255),
    fullname     varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    username     varchar(255),
    role         varchar(255)
);

alter table users
    owner to postgres;



create table cart
(
    id         bigint generated always as identity
        constraint cart_pk
            primary key,
    user_id    bigint not null
        constraint cart_users_fk
            references users
            on update cascade on delete cascade,
    product_id bigint not null
        constraint cart_product_fk
            references product
            on update cascade on delete cascade
);

alter table cart
    owner to postgres;

