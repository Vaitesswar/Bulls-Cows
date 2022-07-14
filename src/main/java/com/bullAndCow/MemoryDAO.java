package com.bullAndCow;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryDAO {
	private static final Map<Integer,LinkedList<Model>> gameMap = new LinkedHashMap<>();
	
	public int addNewGame(Model game) {
		int nextId = gameMap.keySet().size() + 1;
        game.setGameID(nextId);
        LocalDateTime lt = LocalDateTime.now();
        game.setTimeStamp(lt);
        game.setPartial(0);
        game.setExact(0);
        game.setStatus(false);
        LinkedList<Model> gameList = new LinkedList<>();
        gameList.add(game);
        gameMap.put(game.getGameID(),gameList);
        
        return game.getGameID();
    }
	
	
	public Model getFirstGame(int gameID) {
		LinkedList<Model> gameList = gameMap.get(gameID);
		return gameList.getFirst();   
    }
	
	public Model getLastGame(int gameID) {
		LinkedList<Model> gameList = gameMap.get(gameID);
		return gameList.getLast();   
    }
	
	public Map<Integer,LinkedList<Model>> getAllGames() {
		return gameMap;   
    }
	
	public void putNewGuess(Model guess) {
		LinkedList<Model> gameList = gameMap.get(guess.getGameID());
		gameList.add(guess);
		gameMap.put(guess.getGameID(), gameList);
    }
	
	public List<Model> getRounds(int gameID) {
		LinkedList<Model> gameList = gameMap.get(gameID);
		return gameList.subList(1, gameList.size());

    }


}
