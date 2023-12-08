package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.shoot.ShootMethod;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    /**
     * 子弹一次发射数量
     */
    protected int shootnum;
    /**
     * 子弹伤害
     */
    protected int power;
    /**攻击方式 */
    private ShootMethod shootMethod;

    public void setShootMethod(ShootMethod shootMethod) {
        this.shootMethod = shootMethod;
    }
    public List<BaseBullet>executeShoot(int LocationX,int LoactionY,int speedX,int speedY,int power,int shootNum,int direction)
    {
        return shootMethod.shoot(LocationX,LoactionY,speedX,speedY,power,shootNum,direction);
    }

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootnum, int power) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shootnum=shootnum;
        this.power=power;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }
    public int getHp() {
        return hp;
    }
    public int getShootnum(){return shootnum;}
    public void setShootnum(int x){this.shootnum=x;}
    public int getPower(){return power;}


    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();
}


