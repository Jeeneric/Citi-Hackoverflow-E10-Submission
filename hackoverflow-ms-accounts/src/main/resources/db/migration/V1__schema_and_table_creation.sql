CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE app.account (
    account_id uuid NOT NULL,
    user_id uuid NOT NULL,
    balance decimal NOT NULL DEFAULT 0.0,
    account_type varchar(15) NOT NULL,
    CONSTRAINT accounts_pk PRIMARY KEY (account_id)
);