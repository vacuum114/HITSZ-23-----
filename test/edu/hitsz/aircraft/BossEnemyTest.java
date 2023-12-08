package edu.hitsz.aircraft;

import edu.hitsz.Factory.BossEnemyFactory;
import edu.hitsz.prop.AbstractPropObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BossEnemyTest {

    BossEnemy boss;
    @BeforeEach
    void create()
    {
        boss=(BossEnemy) new BossEnemyFactory().creator();
    }
    @ParameterizedTest
    @DisplayName("受到伤害测试")
    @ValueSource(ints ={10,20,40})
    void decreaseHp(int power) {
        int hp1= boss.getHp();
        boss.decreaseHp(power);
        int hp2=boss.getHp();
        assertEquals(hp1-power,hp2);
    }
    @Test
    @DisplayName("道具掉落测试")
    void dropProp() {
        List<AbstractPropObject>arr=boss.dropProp();
        System.out.println("掉落道具数量:"+arr.size());
        assertEquals(3,arr.size());
    }
    @Test
    @DisplayName("敌机移动测试")
    void forward()
    {
        int x1=boss.getLocationX(),y1=boss.getLocationY();
        int sx=boss.getSpeedX(),sy=boss.getSpeedY();
        boss.forward();
        int x2=boss.getLocationX(),y2=boss.getLocationY();
        System.out.println("运动前X坐标:"+x1+" "+"运动前Y坐标:"+y1);
        System.out.println("X轴上的运动速度:"+sx+" "+"Y轴上的运动速度:"+sy);
        System.out.println("运动后X坐标:"+x2+" "+"运动后Y坐标:"+y2);
        assertAll(()->assertEquals(x1+sx,x2),()->assertEquals(y1+sy,y2));
    }
}