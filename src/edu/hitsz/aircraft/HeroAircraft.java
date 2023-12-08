package edu.hitsz.aircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.shoot.SpreadShoot;
import edu.hitsz.shoot.StraightShoot;

import java.util.LinkedList;
import java.util.List;


/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp,int shootnum,int power) {
        super(locationX, locationY, speedX, speedY,hp,shootnum,power);
        this.setShootMethod(new StraightShoot());
    }
    //饿汉式生成英雄机
    private static HeroAircraft instance = new HeroAircraft(
            Main.WINDOW_WIDTH / 2,
            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
            0, 0, 10000,1,30);

    public static HeroAircraft getInstance() {
        return instance;
    }

    public void increaseHp(int increase) {
        hp += increase;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }


    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {

        return this.executeShoot(this.locationX,this.locationY,this.speedX,this.speedY,this.power,this.shootnum,this.direction);
    }

    //获得火力道具
    public void increaseBullet() {
        this.setShootnum(3);
        this.setShootMethod(new SpreadShoot());
    }
    public static void initial()
    {
        instance=new HeroAircraft(
                Main.WINDOW_WIDTH / 2,
                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                0, 0, 10000,1,30);
    }
    public void decreaseBullet()
    {
        this.setShootnum(1);
        this.setShootMethod(new StraightShoot());
    }
}
