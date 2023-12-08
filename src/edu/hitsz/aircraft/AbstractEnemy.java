package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractPropObject;

import java.util.List;

/**
 * 所有敌机的抽象父类：
 * 敌机（BOSS, ELITE, MOB）
 *
 * @author hitsz
 */
public abstract class AbstractEnemy extends AbstractAircraft{

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp,int shootnum,int power) {
        super(locationX, locationY, speedX, speedY, hp,shootnum,power);
    }
    public abstract List<AbstractPropObject> dropProp();
    protected boolean outOfBound=false;
    public boolean isOutOfBound() {
        return outOfBound;
    }
}
