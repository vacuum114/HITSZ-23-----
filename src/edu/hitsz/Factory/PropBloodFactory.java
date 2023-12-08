package edu.hitsz.Factory;

import edu.hitsz.prop.PropBlood;
import edu.hitsz.prop.AbstractPropObject;

public class PropBloodFactory implements PropFactory{

    @Override
    public AbstractPropObject creator(int x,int y) {
        return new PropBlood(x,y,0,5);
    }
}
