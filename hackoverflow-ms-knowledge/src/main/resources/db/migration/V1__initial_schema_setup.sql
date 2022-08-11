CREATE TABLE IF NOT EXISTS articles(
   id                    SERIAL PRIMARY KEY,
   date                  VARCHAR(100),
   name                  VARCHAR(100) UNIQUE,
   article               VARCHAR(255),
   image                 VARCHAR(255)
);
