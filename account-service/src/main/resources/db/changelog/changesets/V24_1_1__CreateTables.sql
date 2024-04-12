create table if not exists account.t_customers
(
    id            varchar(36) primary key,
    c_number      varchar(20)  not null unique,
    c_holder      varchar(255) not null unique,
    c_opened_date timestamp    null,
    c_balance     bigint default 0,
    c_customer_id varchar(36)  not null unique
);
