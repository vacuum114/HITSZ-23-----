package edu.hitsz.Factory;

import edu.hitsz.prop.PropBomb;
import edu.hitsz.prop.AbstractPropObject;

public class PropBombFactory implements PropFactory{


    @Override
    public AbstractPropObject creator(int x, int y) {
        return new PropBomb(x,y,0,5);
    }
}
