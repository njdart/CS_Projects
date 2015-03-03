INSERT INTO "Users" ("Firstname", "Lastname", "Phonenumber") VALUES 
    ('Nicholas', 'Dart', '0118999'),
    ('Joe', 'Blogs', '00000000'),
    ('Jack', 'ONeill', '111111111');

INSERT INTO "Sports" ("Name") VALUES
    ('Football'),
    ('Table Tennis'),
    ('Xtreem Frizbe');

INSERT INTO "SportingPlayers" ("UserID", "SportID") VALUES
    (1, 1), /* Nicholas plays Football*/
    (1, 2), /* Nicholas Plays Table Tennis*/
    (2, 1); /* Joe Plays Footbal*/

INSERT INTO "Teams" ("Name", "SportID", "CaptainID") VALUES 
    ('Away Team', 1, 1), /* Nicholas is the captain of the Sports team "Away Team"*/
    ('PongPing', 2, 3);  /* Jack is the captain of the Table Tennis Team*/

INSERT INTO "TeamPlayers" ("SportsPlayerID", "TeamID") VALUES
    (1, 1),
    (2, 2);

INSERT INTO "TrainingTimes" ("TeamID", "TimeStart", "Day") VALUES
    (1, Now(), 'MO'),
    (2, Now(), 'FR'); 