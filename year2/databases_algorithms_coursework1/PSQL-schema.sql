
/**** Users Table ****/

CREATE TABLE "Users" (
    /*PK*/"ID" BIGSERIAL NOT NULL,
    "Firstname" character varying(50) NOT NULL,
    "Lastname" character varying(50) NOT NULL,
    "Phonenumber" character varying(20) NOT NULL
);

ALTER TABLE ONLY "Users"
    ADD CONSTRAINT "Users_ID" PRIMARY KEY ("ID");

/**** Sports Table ****/

CREATE TABLE "Sports" (
    /*PK*/"ID" BIGSERIAL NOT NULL,
    "Name" character varying(100) NOT NULL
);

ALTER TABLE ONLY "Sports"
    ADD CONSTRAINT "Sports_ID" PRIMARY KEY ("ID");

/**** Teams Table ****/

CREATE TABLE "Teams" (
    /*PK*/"ID" BIGSERIAL NOT NULL,
    "Name" character varying(100) NOT NULL,
    /*FK*/"SportID" bigint NOT NULL,
    /*FK*/"CaptainID" bigint NOT NULL
);

ALTER TABLE ONLY "Teams"
    ADD CONSTRAINT "Teams_ID" PRIMARY KEY ("ID");

/**** Sporting Players Table ****/

CREATE TABLE "SportingPlayers" (
    /*PK*/"ID" BIGSERIAL NOT NULL,
    /*FK*/"UserID" bigint NOT NULL,
    /*FK*/"SportID" bigint NOT NULL
);

ALTER TABLE ONLY "SportingPlayers"
    ADD CONSTRAINT "SportingPlayers_ID" PRIMARY KEY ("ID");

/**** Team Players Table ****/

CREATE TABLE "TeamPlayers" (
    /*FK*/"SportsPlayerID" bigint NOT NULL,
    /*FK*/"TeamID" bigint NOT NULL
);

/**** Training Times table ****/

CREATE TABLE "TrainingTimes" (
    /*PK*/"ID" BIGSERIAL NOT NULL,
    /*FK*/"TeamID" integer NOT NULL,
    "TimeStart" time without time zone NOT NULL,
    /*FK*/"Day" character varying(2) NOT NULL
);

ALTER TABLE ONLY "TrainingTimes"
    ADD CONSTRAINT "TrainignTimes_id" PRIMARY KEY ("ID");

/**** Days Table ****/


CREATE TABLE "Days" (
    /*PK*/"Day" character varying(2) NOT NULL
);

ALTER TABLE ONLY "Days"
    ADD CONSTRAINT "Days_day" PRIMARY KEY ("Day");


INSERT INTO "Days" ("Day") VALUES
('MO'),
('TU'),
('WE'),
('TH'),
('FR'),
('SA'),
('SU');

/**** Forign Keys ****/

/**** Sporting Playes ****/
ALTER TABLE ONLY "SportingPlayers"
    ADD CONSTRAINT "SportingPlayers_id_fkey" FOREIGN KEY ("SportID") REFERENCES "Sports"("ID");

    
ALTER TABLE ONLY "SportingPlayers"
    ADD CONSTRAINT "SportingPlayers_User_id_fkey" FOREIGN KEY ("UserID") REFERENCES "Users"("ID");

/**** Team Players ****/
ALTER TABLE ONLY "TeamPlayers"
    ADD CONSTRAINT "TeamPlayers_Team_id_fkey" FOREIGN KEY ("TeamID") REFERENCES "Teams"("ID");


ALTER TABLE ONLY "TeamPlayers"
    ADD CONSTRAINT "TeamPlayers_Sports_player_id_fkey" FOREIGN KEY ("SportsPlayerID") REFERENCES "SportingPlayers"("ID");

/**** Teams ****/
ALTER TABLE ONLY "Teams"
    ADD CONSTRAINT "Teams_captain_id_fkey" FOREIGN KEY ("CaptainID") REFERENCES "Users"("ID");


ALTER TABLE ONLY "Teams"
    ADD CONSTRAINT "Teams_sport_id_fkey" FOREIGN KEY ("SportID") REFERENCES "Sports"("ID");

/**** Training Times ****/
ALTER TABLE ONLY "TrainingTimes"
    ADD CONSTRAINT "Training_times_Day_fkey" FOREIGN KEY ("Day") REFERENCES "Days"("Day");

ALTER TABLE ONLY "TrainingTimes"
    ADD CONSTRAINT "Training_times_Team_id_fkey" FOREIGN KEY ("TeamID") REFERENCES "Teams"("ID");

