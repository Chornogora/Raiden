create table clients
(
  client_id serial
    constraint clients_pk
      primary key,
  client_fullname varchar not null,
  client_password_series VARCHAR(2),
  client_password_number int not null,
  client_account NUMERIC default 0 not null,
  client_phone_number VARCHAR(13) not null,
  client_email varchar(40) not null,
  client_status VARCHAR(10) not null
);
