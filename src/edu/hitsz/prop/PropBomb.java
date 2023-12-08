package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBomb extends AbstractPropObject{
    public PropBomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void function()
    {
        System.out.println("BombSupply active!");
    }

}
