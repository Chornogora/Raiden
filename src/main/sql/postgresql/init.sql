CREATE DATABASE raidendb;
CREATE ROLE raiden WITH CREATEROLE;
ALTER ROLE raiden WITH LOGIN;
GRANT CONNECT ON DATABASE raidendb TO raiden;
GRANT ALL PRIVILEGES ON DATABASE raidendb TO raiden WITH GRANT OPTION;

-- Enter to the system by 'raiden'

CREATE USER client WITH PASSWORD 'userPass';
GRANT ALL PRIVILEGES ON DATABASE raidendb TO client;

CREATE USER admin WITH PASSWORD 'adminPass';
GRANT ALL PRIVILEGES ON DATABASE raidendb TO admin;
GRANT SELECT ON TABLE administrators TO admin;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE services TO admin;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE internet TO admin;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE phone_connection TO admin;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE television TO admin;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE work TO admin;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO admin;

CREATE USER boss WITH PASSWORD 'bossPass';
GRANT ALL PRIVILEGES ON DATABASE raidendb TO boss;
GRANT ALL PRIVILEGES ON TABLE administrators TO boss;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO boss;
