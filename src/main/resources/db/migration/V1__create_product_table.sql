create sequence IF NOT EXISTS product_seq start with 1 increment by 50;

create table IF NOT EXISTS product (
        id bigint not null,
        description varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    );
