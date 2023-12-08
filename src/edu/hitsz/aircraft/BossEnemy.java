package edu.hitsz.aircraft;

import edu.hitsz.Factory.PropBloodFactory;
import edu.hitsz.Factory.PropBulletFactory;
import edu.hitsz.Factory.PropBombFactory;
import edu.hitsz.application.Main;
import edu.hitsz.bombAct.bombActivate;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.AbstractPropObject;
import edu.hitsz.shoot.SpreadShoot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * Boss敌机类
 * 可以射击，能散射
 *
 * @author hitsz
 */
public class BossEnemy extends AbstractEnemy implements bombActivate {

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int shootnum,int power) {
        super(locationX, locationY, speedX, speedY, hp,shootnum,power);
        this.setShootMethod(new SpreadShoot());
    }

    final private int direction = 1;
    private final int BloodProp=1;
    private final int BombProp=2;
    private final int BulletProp=3;
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
    public List<AbstractPropObject> dropProp() {
       List<AbstractPropObject>prop=new ArrayList<>();
       final int propNum=3;
       Random random=new Random(System.currentTimeMillis());
        int lx=-20;
        for(int i=0;i<propNum;++i) {
            int x = random.nextInt(3)+1;
            if (x == BloodProp)
            // 生成回血道具
            {
                prop.add(new PropBloodFactory().creator(this.getLocationX()+lx,this.getLocationY()));
            } else if (x == BombProp)
            //生成炸弹道具
            {
                prop.add(new PropBombFactory().creator(this.getLocationX()+lx,this.getLocationY()));
            } else if (x == BulletProp)
            //生成火力道具
            {
                prop.add(new PropBulletFactory().creator(this.getLocationX()+lx,this.getLocationY()));
            }
            lx+=20;
        }
        return prop;
    }

    @Override
    public void update() {
        this.decreaseHp(100);
    }
}
