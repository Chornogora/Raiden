create table clients
(
  client_id serial
    constraint clients_pk
      primary key,
  client_password varchar not null,
  client_fullname varchar not null,
  client_passport_series VARCHAR(2),
  client_passport_number int not null,
  client_account NUMERIC default 0 not null,
  client_phone_number VARCHAR(13) not null,
  client_email varchar(40) not null,
  client_status VARCHAR(20) not null
);

create table administrators
(
  administrator_id serial
    constraint administrators_pk
      primary key,
  administrator_login VARCHAR(20) not null,
  administrator_password VARCHAR not null,
  administrator_fullname VARCHAR not null
);

create unique index administrators_administrator_login_uindex
  on administrators (administrator_login);

create table contracts
(
  contract_id serial
    constraint contracts_pk
      primary key,
  contract_connected Date not null,
  contract_control Date,
  contract_status VARCHAR(10),
  contract_address varchar not null,
  client_id int not null
    constraint contracts_clients_client_id_fk
      references clients
      on update restrict on delete restrict
);

create table services
(
  service_id serial
    constraint services_pk
      primary key,
  service_name varchar not null
);

create table internet
(
  service_id int
    constraint internet_pk
      primary key
    constraint internet_services_service_id_fk
      references services,
  internet_speed int not null,
  internet_month_price NUMERIC not null
);

create table phone_connection
(
  service_id serial
    constraint phone_connection_pk
      primary key
    constraint phone_connection_services_service_id_fk
      references services,
  phone_connection_month_price numeric not null,
  phone_connection_mobile_minutes int not null
);

create table television
(
  service_id int
    constraint television_pk
      primary key
    constraint television_services_service_id_fk
      references services,
  television_channels int not null,
  television_format varchar not null,
  television_month_price numeric not null
);

create table work
(
  service_id int
    constraint work_pk
      primary key
    constraint work_services_service_id_fk
      references services,
  work_measure varchar not null,
  work_price numeric not null
);

create table payments
(
  payment_id serial
    constraint payments_pk
      primary key,
  payment_date DATE not null,
  contract_id int
    constraint payments_contracts_contract_id_fk
      references contracts
);

create table contract_services
(
  contract_id int not null
    constraint contract_services_contracts_contract_id_fk
      references contracts,
  service_id int
    constraint contract_services_services_service_id_fk
      references services,
  constraint contract_services_pk
    primary key (contract_id, service_id)
);