package com.bullAndCow;


import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseDAO {
	@Autowired
	private final JdbcTemplate jdbc;
	private static int gameID = 0;
	 
	public DatabaseDAO(JdbcTemplate jdbc) { 
		 this.jdbc = jdbc;
	}
	
	public int addNewGame(ModelDB game) throws SQLException { 
		
		gameID += 1;
		jdbc.update("INSERT INTO game(gameID,no1,no2,no3,no4) VALUES(?,?,?,?,?)",
					gameID,game.getNo1(),game.getNo2(),game.getNo3(),game.getNo4());
		return gameID;
		//
	}
	
	public void putNewGuess(ModelDB guess) {
		jdbc.update("INSERT INTO guess(gameID,no1,no2,no3,no4,gameTime,exact,part,stat) VALUES(?,?,?,?,?,?,?,?,?)",
				guess.getGameID(),guess.getNo1(),guess.getNo2(),guess.getNo3(),guess.getNo4(),
				guess.getTimeStamp(),guess.getExact(),guess.getPartial(),guess.getStatus());
    }
	
	public ModelDB getFirstGame(int gameID) {
		ModelDB game = jdbc.queryForObject("SELECT * FROM game WHERE gameID = ?",new GameMapper(),gameID);
		return game;   
    }
	
	public ModelDB getLastGame(int gameID) {
		List<ModelDB> gameList = jdbc.query("SELECT * FROM guess WHERE gameID = ?",new GameMapper(),gameID);
		ModelDB game = new ModelDB();
		game.setStatus(false);
		if (gameList.size() > 0) {
			game = gameList.get(gameList.size()-1);
		}
		return game;   
    }
	
	public List<ModelDB> getRounds(int gameID) {
		List<ModelDB> gameList = jdbc.query("SELECT * FROM guess WHERE gameID = ?",new GameMapper(),gameID);
		return gameList;
    }
	
	public List<ModelDB> getAllGuess() {
		List<ModelDB> guessList = jdbc.query("SELECT * FROM guess",new GameMapper());
		return guessList;   
    }
	
	public List<ModelDB> getAllGames() {
		List<ModelDB> gameList = jdbc.query("SELECT * FROM game",new GameMapper());
		return gameList;   
    }
}

