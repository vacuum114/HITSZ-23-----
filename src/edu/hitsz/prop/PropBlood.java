package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBlood extends AbstractPropObject{
    public PropBlood(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void function() {
        HeroAircraft.getInstance().increaseHp(30);
    }

}
