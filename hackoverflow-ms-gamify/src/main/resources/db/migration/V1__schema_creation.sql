CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE app.game_profile (
    user_id uuid NOT NULL,
    credits integer NOT NULL DEFAULT 0,
    experience integer NOT NULL DEFAULT 0,
    CONSTRAINT game_profile_pk PRIMARY KEY (user_id)
);

CREATE TABLE app.quest (
    quest_id uuid NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(100) NOT NULL,
    goal integer NOT NULL,
    credit_reward integer NOT NULL DEFAULT 0,
    experience_reward integer NOT NULL,
    CONSTRAINT quest_pk PRIMARY KEY (quest_id)
);

CREATE TABLE app.quest_completion (
    user_id uuid NOT NULL,
    quest_id uuid NOT NULL,
    is_completed boolean NOT NULL DEFAULT false,
    progress integer NOT NULL,
    CONSTRAINT quest_completion_pk PRIMARY KEY (user_id,quest_id)
);

CREATE TABLE app.reward (
    reward_id uuid NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(100) NOT NULL,
    cost integer NOT NULL,
    image_url varchar(255),
    CONSTRAINT reward_pk PRIMARY KEY (reward_id)
);

CREATE TABLE app.reward_wallet (
    user_id uuid NOT NULL,
    reward_id uuid NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT reward_wallet_pk PRIMARY KEY (user_id,reward_id)
);