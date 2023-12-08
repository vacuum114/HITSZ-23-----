package edu.hitsz.Factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.basic.AbstractFlyingObject;

public interface EnemyFactory {
    public abstract AbstractEnemy creator();
}
