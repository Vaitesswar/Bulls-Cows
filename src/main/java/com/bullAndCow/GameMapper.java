package com.bullAndCow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public final class GameMapper implements RowMapper<ModelDB>{
	
	public ModelDB mapRow(ResultSet rs, int index) throws SQLException {
        ModelDB game = new ModelDB();
        game.setGameID(rs.getInt("gameID"));
        game.setNo1(rs.getInt("no1"));
        game.setNo2(rs.getInt("no2"));
        game.setNo3(rs.getInt("no3"));
        game.setNo4(rs.getInt("no4"));
        game.setTimeStamp(rs.getTimestamp("gameTime"));
        game.setExact(rs.getInt("exact"));
        game.setPartial(rs.getInt("part"));
        game.setStatus(rs.getBoolean("stat"));
        return game;
    }
}
