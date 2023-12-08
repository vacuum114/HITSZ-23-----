package edu.hitsz.application;

import edu.hitsz.Factory.BossEnemyFactory;
import edu.hitsz.Factory.EliteEnemyFactory;
import edu.hitsz.Factory.MobEnemyFactory;
import edu.hitsz.application.Music;
import edu.hitsz.aircraft.*;
import edu.hitsz.bombAct.bombActivate;
import edu.hitsz.bombAct.bombList;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.*;
import edu.hitsz.frame.GameFrame;
import edu.hitsz.frame.RankFrame;
import edu.hitsz.frame.SettingFrame;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {
    public static double enemyStrengthen=1.00;
    private int backGroundTop = 0;
    public static int bossnum=1;
    protected final int scoreToBoss=500;
    protected boolean bossAlive=false;

    protected double  eliteRate=0.2;
    /**
     * Scheduled 线程池，用于任务调度
     */
    protected final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    protected final int timeInterval = 40;

    protected final HeroAircraft heroAircraft;
    protected final List<AbstractEnemy> enemyAircrafts;
    private final List<AbstractPropObject>propObjects;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    //控制音乐
    protected final int voiceSeletion= SettingFrame.voiceSeletion;
    protected final Music bgm=new Music("src\\videos\\bgm.wav",-1,-5);
    protected final Music bgm_boss=new Music("src\\videos\\bgm_boss.wav",-1,5);
    private final Music bullet=new Music("src\\videos\\bullet.wav",1,-10);
    private final Music game_over=new Music("src\\videos\\game_over.wav",0,6);
    protected final Thread t_bgm=new Thread(bgm);
    protected final Thread t_bgm_boss=new Thread(bgm_boss);
    protected final Thread t_game_over=new Thread(game_over);

    protected bombList bomblist=new bombList();

    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    protected int score = 0;
    private int baseMobscore=10;
    private int baseEliteScore=30;
    private int baseBossScore=100;
    /**
     * 当前时刻
     */
    protected int time = 0;

    /**
     * 周期（ms)e
     * 指示子弹的发射、敌机的产生频率
     */
    protected int  cycleDuration = 600;
    private int cycleTime = 0;
    /**
     * 游戏结束标志
     */
    protected boolean gameOverFlag = false;

    public Game() {
        if(HeroAircraft.getInstance().notValid()){HeroAircraft.initial();}
        heroAircraft = HeroAircraft.getInstance();
        heroAircraft.setLocation(Main.WINDOW_WIDTH / 2,Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight());
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        propObjects = new LinkedList<>();
        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());
        if(voiceSeletion==1) {
            executorService.submit(t_bgm);
        }
        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
        enemyStrengthen=1.00;
        bossnum=1;
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public abstract void abstractAction();
    public void action()
    {
        abstractAction();
    }

    //***********************
    //      Action 各部分
    //***********************
    protected boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    protected void shootAction() {
        // TODO 敌机射击
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            List<BaseBullet>bulletList=enemyAircraft.shoot();
            enemyBullets.addAll(bulletList);
            for(BaseBullet b:bulletList)
            {
                bomblist.add((bombActivate) b);
            }
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    protected void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    protected void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }
    protected void propObjectsMoveAction()
    {
        for(AbstractPropObject propobject:propObjects)
        {
            propobject.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    protected void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for(BaseBullet bullet : enemyBullets)
        {
            if (bullet.notValid()) {
                continue;
            }
            if(heroAircraft.notValid())
            {
                continue;
            }
            if(heroAircraft.crash(bullet))
            {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                bomblist.remove((bombActivate) bullet);
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if(voiceSeletion==1) {
                        Music bullet_hit = new Music("src\\videos\\bullet_hit.wav", 0, 0);
                        Thread t_bullet_hit = new Thread(bullet_hit);
                        executorService.submit(t_bullet_hit);
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }
        // Todo: 我方获得道具，道具生效
        for(AbstractPropObject propobject:propObjects)
        {
            if (propobject.notValid()) {
                continue;
            }
            if(heroAircraft.notValid()) {
                continue;
            }
            if(heroAircraft.crash(propobject))
            {
                if(voiceSeletion==1) {
                    Music get_supply = new Music("src\\videos\\get_supply.wav", 0, 0);
                    Thread t_get_supply = new Thread(get_supply);
                    executorService.submit(t_get_supply);
                }
                if(propobject instanceof PropBomb)
                {
                    if(voiceSeletion==1) {
                        Music bomb = new Music("src\\videos\\bomb_explosion.wav", 0, 0);
                        Thread t_bomb = new Thread(bomb);
                        executorService.submit(t_bomb);
                    }
                    bomblist.activate();
                }
                propobject.function();
                propobject.vanish();
            }
        }

        //结算被消灭的敌机
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            if (enemyAircraft.notValid()&&!enemyAircraft.isOutOfBound()) {
                // TODO 获得分数，产生道具补给
                List<AbstractPropObject> prop = enemyAircraft.dropProp();
                if (prop != null) {
                    propObjects.addAll(prop);
                }
                if (enemyAircraft instanceof BossEnemy) {
                    if (voiceSeletion == 1) {
                        bgm_boss.stop();
                        executorService.submit(t_bgm);
                    }
                    score += (int) (baseBossScore * enemyStrengthen);
                    bossAlive = false;
                } else if (enemyAircraft instanceof EliteEnemy) {
                    score += (int) (baseEliteScore * enemyStrengthen);
                } else {
                    score += (int) (baseMobscore * enemyStrengthen);
                }
                bomblist.remove((bombActivate) enemyAircraft);
            }
        }


    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    protected void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        propObjects.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g,propObjects);
        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);
        //游戏结束时，显示游戏结束字样
        if(gameOverFlag) {
            g.drawImage(ImageManager.Over_IMAGE,10,50,null);
            JButton over = new JButton("确定");
            over.setBounds(200, 350, 100, 70);
            this.add(over);
            RankFrame.score=score;
            over.addActionListener((e)->{
                GameFrame.frame.setVisible(false);
                if(RankFrame.rankFrame!=null)
                {
                    RankFrame.showSave();
                    RankFrame.rankFrame.setVisible(true);
                }
                else {
                    new RankFrame();
                    RankFrame.showSave();
                }
            });

        }
    }
    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
