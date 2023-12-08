package edu.hitsz.application;

import edu.hitsz.Factory.BossEnemyFactory;
import edu.hitsz.Factory.EliteEnemyFactory;
import edu.hitsz.Factory.MobEnemyFactory;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.bombAct.bombActivate;

import java.util.concurrent.TimeUnit;

public class NormalGame extends Game{
    public NormalGame()
    {
        super();
        super.enemyMaxNumber=7;
    }

    @Override
    public void abstractAction() {
        Runnable task = () -> {

            time += timeInterval;
            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                Game.enemyStrengthen+=0.01;
                super.cycleDuration*=0.999;
                if(super.eliteRate<=1) {
                    super.eliteRate += 0.0005;
                }
                System.out.printf("精英敌机概率:%.2f,敌机产生周期:%d,当前敌机属性提升倍率和得分提升倍率:%.2f\n",super.eliteRate,super.cycleDuration,Game.enemyStrengthen);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    AbstractEnemy t;
                    if(Math.random()<=eliteRate)
                    {   //产生精英敌机
                        t=new EliteEnemyFactory().creator();
                    }
                    else
                    {   //产生普通敌机
                        t=new MobEnemyFactory().creator();
                    }
                    enemyAircrafts.add(t);
                    bomblist.add((bombActivate) t);
                }
                //产生boss敌机
                if(score>=bossnum*scoreToBoss&&bossAlive==false)
                {
                    if(voiceSeletion==1) {
                        bgm.stop();
                        executorService.submit(t_bgm_boss);
                    }
                    AbstractEnemy t=new BossEnemyFactory().creator();
                    enemyAircrafts.add(t);
                    bomblist.add((bombActivate) t);
                    bossAlive=true;
                    ++bossnum;
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propObjectsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                gameOverFlag = true;
                System.out.println("Game Over!");
                if(voiceSeletion==1) {
                    executorService.submit(t_game_over);
                    bgm.stop();
                    bgm_boss.stop();
                }
                executorService.shutdown();
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }
}


