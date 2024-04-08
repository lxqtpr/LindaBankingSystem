create table if not exists debit_card.t_debit_cards
(
    id                 varchar(36) primary key,
    c_number           varchar(16)  not null unique,
    c_card_holder_name varchar(255) not null,
    c_balance          bigint default 0,
    c_expiration_date  varchar(255) not null,
    c_daily_limit      int default 1000,
    c_cvv              varchar(3)   not null,
    c_customer_id      varchar(255) not null unique
);
