-- Members names and phone numbers for a given team
SELECT "Firstname","Lastname","Phonenumber","UserID" FROM "Users"
  INNER JOIN "SportingPlayers" ON ("SportingPlayers"."UserID"="Users"."ID")
  INNER JOIN "Sports" ON ("SportingPlayers"."SportID"="Sports"."ID")
  INNER JOIN "Teams" ON ("Teams"."SportID"="Sports"."ID")
WHERE "Teams"."Name" = 'Away Team'; 

-- All the players for a given sport or games
SELECT "Firstname","Lastname","Phonenumber","UserID" FROM "Users"
  INNER JOIN "SportingPlayers" ON ("SportingPlayers"."UserID"="Users"."ID")
  INNER JOIN "Sports" ON ("SportingPlayers"."SportID"="Sports"."ID")
  INNER JOIN "Teams" ON ("Teams"."SportID"="Sports"."ID")
WHERE "Sports"."Name" = 'Table Tennis'; 

--Any games or sports that have no teams
SELECT * FROM "Sports" WHERE "ID" NOT IN (SELECT "ID" FROM "Teams");
