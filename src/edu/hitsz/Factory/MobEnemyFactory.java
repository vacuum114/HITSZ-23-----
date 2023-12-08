package edu.hitsz.Factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.application.Game;

public class MobEnemyFactory implements EnemyFactory{
    int baseHp=25;
    @Override
    public AbstractEnemy creator() {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                (int)(baseHp*Game.enemyStrengthen),
                0,
                0
        );
    }
}
