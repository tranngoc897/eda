
CREATE TABLE "postgres".players (
                                    id int8 NOT NULL,
                                    "name" varchar NOT NULL,
                                    created timestamp(0) NOT NULL,
                                    CONSTRAINT players_pk PRIMARY KEY (id)
);