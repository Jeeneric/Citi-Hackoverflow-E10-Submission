CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE app.destination (
    account_id uuid NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(100),
    goal decimal NOT NULL,
    category varchar(30),
    CONSTRAINT destination_pk PRIMARY KEY (account_id)
);