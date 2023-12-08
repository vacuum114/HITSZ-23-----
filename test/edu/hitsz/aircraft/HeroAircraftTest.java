package edu.hitsz.aircraft;

import edu.hitsz.Factory.BossEnemyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft hero;
    @BeforeEach
    void getInstance()
    {
        hero=HeroAircraft.getInstance();
    }
    @ParameterizedTest
    @DisplayName("受到伤害测试")
    @ValueSource(ints ={2000,4000,16000})
    void decreaseHp(int power) {
        int hp1= hero.getHp();
        hero.decreaseHp(power);
        int hp2=hero.getHp();
        System.out.println("受到伤害前hp:"+hp1+" "+"受到伤害后hp:"+hp2);
        if(hp1-power>0)
        assertEquals(hp1-power,hp2);
        else
        assertEquals(0,hp2);
    }

    @ParameterizedTest
    @DisplayName("恢复hp测试")
    @ValueSource(ints ={500,1000,20000})
    void increaseHp(int power) {
        hero.decreaseHp(5000);
        int hp1= hero.getHp();
        hero.increaseHp(power);
        int hp2=hero.getHp();
        System.out.println("恢复生命前hp:"+hp1+" "+"恢复生命后hp:"+hp2);
        if(hp1+power< hero.maxHp)
            assertEquals(hp1+power,hp2);
        else
            assertEquals(hero.maxHp,hp2);
    }
}