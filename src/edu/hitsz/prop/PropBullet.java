package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class PropBullet extends AbstractPropObject{
    public PropBullet(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void function() {
        HeroAircraft.getInstance().increaseBullet();
        System.out.println("FireSupply active!");
        boolean flag=effectDuration.newOne();
        effectDuration duration = effectDuration.getInstance(5,HeroAircraft.getInstance());
        duration.reSetDuration();
        if(flag) {
            duration.start();
        }
    }
}
