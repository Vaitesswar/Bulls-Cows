USE mysql;
DROP TABLE IF EXISTS guess;
DROP TABLE IF EXISTS game;
CREATE TABLE game (
	gameID INT PRIMARY KEY,
	no1 INT,
    no2 INT,
    no3 INT,
    no4 INT,
    gameTime TIMESTAMP,
    exact INT DEFAULT 0,
    part INT DEFAULT 0,
    stat BOOLEAN DEFAULT FALSE
);

CREATE TABLE guess (
	guessID INT PRIMARY KEY AUTO_INCREMENT,
    gameID INT,
	no1 INT,
    no2 INT,
    no3 INT,
    no4 INT,
    gameTime TIMESTAMP,
    exact INT DEFAULT 0,
    part INT DEFAULT 0,
    stat BOOLEAN DEFAULT FALSE,
    CONSTRAINT FOREIGN KEY FK_gameID (gameID)
		REFERENCES game (gameID)
);
TABLE game;
TABLE guess;
 