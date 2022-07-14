package com.bullAndCow;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceDB {
	
	@Autowired
	private final DatabaseDAO DAO;
	
	public ServiceDB(DatabaseDAO DAO) {
		this.DAO = DAO;
	}
	
	public int generateNewGame() throws SQLException {
		Random random = new Random();
		int[] numbers = {0,0,0,0};
		int total = 0;
		while (total != 4) {
			int number = random.nextInt(1,10);
			boolean match = false;
			for (int i:numbers) {
				if (i == number) {
					match = true;
					break;
				}
			}
			if (!match) {
				numbers[total] = number;
				total += 1;
			}
		}
		ModelDB game = new ModelDB();
		game.setNo1(numbers[0]);
		game.setNo2(numbers[1]);
		game.setNo3(numbers[2]);
		game.setNo4(numbers[3]);
		int gameID = DAO.addNewGame(game);
		return gameID;
	}
	
	public String guessGame(ModelDB guess) {
		ModelDB game = DAO.getFirstGame(guess.getGameID());
		int exact = 0;
		int partial = 0;
		if (game.getNo1() == guess.getNo1()){
			exact += 1;
		} else if (game.getNo2() == guess.getNo1() || game.getNo3() == guess.getNo1() || game.getNo4() == guess.getNo1()){
			partial += 1;
		}
		if (game.getNo2() == guess.getNo2()){
			exact += 1;
		} else if (game.getNo1() == guess.getNo2() || game.getNo3() == guess.getNo2() || game.getNo4() == guess.getNo2()){
			partial += 1;
		} 
		if (game.getNo3() == guess.getNo3()){
			exact += 1;
		} else if (game.getNo1() == guess.getNo3() || game.getNo2() == guess.getNo3() || game.getNo4() == guess.getNo3()){
			partial += 1;
		}
		if (game.getNo4() == guess.getNo4()){
			exact += 1;
		} else if (game.getNo1() == guess.getNo4() || game.getNo2() == guess.getNo4() || game.getNo3() == guess.getNo4()){
			partial += 1;
		}
		
		guess.setExact(exact);
		guess.setPartial(partial);
		if (exact == 4) {
			guess.setStatus(true);
		} else {
			guess.setStatus(false);
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		guess.setTimeStamp(timestamp);
        DAO.putNewGuess(guess);
        
        String output = "Guess: " + guess.getNo1() + guess.getNo2() + guess.getNo3() + guess.getNo4() +
        				", Time: " + timestamp + ", e:" + exact + "p:" + partial;
        
        return output;
	}
	
	public String getRounds(int gameID) {
		List<ModelDB> rounds = DAO.getRounds(gameID);
		String output = new String();
		for(ModelDB round:rounds) {
			String line = "Guess: " + round.getNo1() + round.getNo2() + round.getNo3() + round.getNo4() +
	        		", Time: " + round.getTimeStamp() +
	        		", e:" + round.getExact() + "p:" + round.getPartial() + ".\n";
			output += line;
		}
		return output;
	}
	
	public String getGame(int gameID) {
		ModelDB game = DAO.getLastGame(gameID);
		String output = new String();
		if (game.getStatus()) {
			String line = "Status: Finished, " + "4 digit number: " + game.getNo1() 
			+ game.getNo2() + game.getNo3() + game.getNo4();
			output += line;
		} else {
			output += "Status: In progress";
		}		
		return output;
	}
	
	public String getGames() {
		List<ModelDB> gameList = DAO.getAllGames();
		String output = new String();
		int maxGameID = 0;
		for(ModelDB game:gameList) {
			if(game.getGameID() > maxGameID) {
				maxGameID = game.getGameID();
			}
		}
		List<ModelDB> guessList = DAO.getAllGuess();
		for (int id = 1; id <= maxGameID; id += 1) {
			boolean status = false;
			for(ModelDB guess:guessList) {
				if (guess.getGameID() == id && guess.getStatus()) {
					status = true;
					break;
				}
			}
			if (status) {
				ModelDB game = DAO.getFirstGame(id);
				String line = "Game ID: " + id + ", Status: Finished, " + "4 digit number: " + game.getNo1() 
				+ game.getNo2() + game.getNo3() + game.getNo4() + "\n";
				output += line;
			} else {
				output += "Game ID: " + id + ", Status: In progress" + "\n";
			}		
		}	
		return output;
	}
}

