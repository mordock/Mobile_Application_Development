package com.example.maxwe.level4gamebacklog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameDao {
    @Query("SELECT * FROM game")
    public List<Game> getAllGames();

    @Query("SELECT * FROM game WHERE id=:id")
    public Game getGame(Long id);

    @Insert
    public void insertGames(Game game);

    @Delete
    public void deleteGames(Game game);

    @Update
    public void updateGames(Game game);
}
