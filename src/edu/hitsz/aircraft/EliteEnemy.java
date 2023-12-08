package edu.hitsz.aircraft;

import edu.hitsz.Factory.PropBloodFactory;
import edu.hitsz.Factory.PropBombFactory;
import edu.hitsz.Factory.PropBulletFactory;
import edu.hitsz.application.Main;
import edu.hitsz.bombAct.bombActivate;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractPropObject;
import edu.hitsz.shoot.StraightShoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 精英敌机类
 *可以射击，只能直射
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemy implements bombActivate {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int shootnum,int power) {
        super(locationX, locationY, speedX, speedY, hp,shootnum,power);
        this.setShootMethod(new StraightShoot());
    }
    private final double PropRate=0.9;
    private final int BloodProp=1;
    private final int BombProp=2;
    private final int BulletProp=3;
    final private int direction = 1;
    @Override
    public List<AbstractPropObject> dropProp() {
        List<AbstractPropObject>prop=new ArrayList<>();
        if(Math.random()<=PropRate) {
            int x = new Random(System.currentTimeMillis()).nextInt(3)  + 1;
            if (x == BloodProp)
                // 生成回血道具
            {
                prop.add(new PropBloodFactory().creator(this.getLocationX(),this.getLocationY()));
            }
            else if (x == BombProp)
                //生成炸弹道具
            {
                prop.add(new PropBulletFactory().creator(this.getLocationX(),this.getLocationY()));
            }
            else if (x == BulletProp)
                //生成火力道具
            {
                prop.add(new PropBombFactory().creator(this.getLocationX(),this.getLocationY()));
            }
        }
        return prop;
    }


    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
            outOfBound=true;
        }
    }
    @Override
    public List<BaseBullet> shoot() {
        return this.executeShoot(this.locationX,this.locationY,this.speedX,this.speedY,this.power,this.shootnum,this.direction);
    }


    @Override
    public void update() {
        this.decreaseHp(Integer.MAX_VALUE);
    }
}
