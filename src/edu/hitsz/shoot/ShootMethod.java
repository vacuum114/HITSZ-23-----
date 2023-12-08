package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootMethod {
    List<BaseBullet>shoot(int LocationX,int LoactionY,int speedX,int speedY,int power,int shootNum,int direction);

}
