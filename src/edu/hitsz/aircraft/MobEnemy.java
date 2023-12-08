package edu.hitsz.aircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bombAct.bombActivate;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractPropObject;
import edu.hitsz.shoot.StraightShoot;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemy implements bombActivate {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int shootnum,int power) {
        super(locationX, locationY, speedX, speedY, hp,shootnum,power);
        this.setShootMethod(new StraightShoot());
    }
    @Override
    public List<AbstractPropObject> dropProp() {
        return null;
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
        return this.executeShoot(this.locationX,this.locationY,this.speedX,this.speedY,this.power,this.shootnum,1);
    }

    @Override
    public void update() {
        this.decreaseHp(Integer.MAX_VALUE);
    }
}
