package edu.hitsz.Factory;

import edu.hitsz.prop.PropBullet;
import edu.hitsz.prop.AbstractPropObject;

public class PropBulletFactory implements PropFactory{

    @Override
    public AbstractPropObject creator(int x, int y) {
        return new PropBullet(x,y,0,5);
    }
}
