package edu.hitsz.Factory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.frame.DifficultyFrame;

public class BossEnemyFactory implements EnemyFactory{
    int baseHp=300;
    int basePower=15;

    @Override
    public AbstractEnemy creator() {
        int x= DifficultyFrame.difficultySelection;
        int hardHp=0;
        if(x==2){hardHp=50*Game.bossnum;}
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                2,
                0,
                (int)(baseHp* Game.enemyStrengthen+hardHp),
                3,
                (int)(basePower*Game.enemyStrengthen)
        );
    }
}
