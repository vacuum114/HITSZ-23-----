package edu.hitsz.Factory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.frame.DifficultyFrame;

public class EliteEnemyFactory implements EnemyFactory {
    int baseHp=60;
    int basePower=15;
    int x= DifficultyFrame.difficultySelection;
    @Override
    public AbstractEnemy creator() {
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                (int)(baseHp* Game.enemyStrengthen),
                1,
                (int)(basePower*Game.enemyStrengthen)
        );
    }
}
