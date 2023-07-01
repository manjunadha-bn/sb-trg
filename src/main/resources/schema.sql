create sequence IF NOT EXISTS inventory_seq start with 1 increment by 50;

create sequence IF NOT EXISTS product_seq start with 1 increment by 50;

create table IF NOT EXISTS inventory (
        count bigint not null,
        id bigint not null,
        product_id bigint unique,
        primary key (id)
    );

create table IF NOT EXISTS product (
        id bigint not null,
        description varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    );

alter table if exists inventory
       add constraint IF NOT EXISTS  FKp7gj4l80fx8v0uap3b2crjwp5
       foreign key (product_id)
       references product;
