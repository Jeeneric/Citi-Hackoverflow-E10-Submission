CREATE TABLE IF NOT EXISTS user_details(
   uuid                   VARCHAR(36) PRIMARY KEY,
   firstname              VARCHAR(100),
   lastname               VARCHAR(100),
   username               VARCHAR(100),
   password               VARCHAR(100),
   email                  VARCHAR(100),
   authority              VARCHAR(100) DEFAULT 'Client' 
);

CREATE TABLE IF NOT EXISTS token_mapping(
   authorizationToken     VARCHAR(36) PRIMARY KEY,
   jwt                    VARCHAR(200) 
);