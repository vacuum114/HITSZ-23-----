package edu.hitsz.playerDate;

import java.util.List;

public interface playerDao {
    void  findByName(String name);
    List<player> getAllPlayer();
    void addPlayer(player p);
    void deletePlayer(int num);
    void load();
    void update();
    int getPlayerNum();
}
