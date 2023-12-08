package edu.hitsz.bullet;

import edu.hitsz.Factory.EliteEnemyFactory;
import edu.hitsz.aircraft.EliteEnemy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyBulletTest {

    EliteEnemy elite=(EliteEnemy) new EliteEnemyFactory().creator();
    @Test
    @DisplayName("子弹移动测试")
    void forward()
    {
        EnemyBullet eb=(EnemyBullet) elite.shoot().get(0);
        int x1=eb.getLocationX(),y1=eb.getLocationY();
        int sx=eb.getSpeedX(),sy=eb.getSpeedY();
        eb.forward();
        int x2=eb.getLocationX(),y2=eb.getLocationY();
        System.out.println("运动前X坐标:"+x1+" "+"运动前Y坐标:"+y1);
        System.out.println("X轴上的运动速度:"+sx+" "+"Y轴上的运动速度:"+sy);
        System.out.println("运动后X坐标:"+x2+" "+"运动后Y坐标:"+y2);
        assertAll(()->assertEquals(x1+sx,x2),()->assertEquals(y1+sy,y2));
    }
    @Test
    @DisplayName("子弹威力测试")
    void getPower() {
        EnemyBullet eb=(EnemyBullet) elite.shoot().get(0);
        int power=eb.getPower();
        System.out.println("敌机子弹威力："+power);
        assertEquals(20,power);
    }
}