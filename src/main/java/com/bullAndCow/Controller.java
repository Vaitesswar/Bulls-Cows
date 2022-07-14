package com.bullAndCow;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	private final ServiceDB service;
	
	public Controller(ServiceDB service) {
		this.service = service;
	}
	
	@PostMapping("/begin")
	public ResponseEntity<String> begin() throws SQLException {
		int gameID = service.generateNewGame();
		return new ResponseEntity<String>("The game ID is " + gameID + ".", HttpStatus.CREATED);
	}
	
	@PostMapping("/guess")
	public String guess(@RequestBody ModelDB guess) {
		return service.guessGame(guess);
	}
	
	@GetMapping("/rounds/{gameID}")
	public String getRounds(@PathVariable int gameID) {
		return service.getRounds(gameID);
	}
	
	@GetMapping("/game/{gameID}")
	public String getGame(@PathVariable int gameID) {
		return service.getGame(gameID);
	}
	
	@GetMapping("/game")
	public String getGames() {
		return service.getGames();
	}
}