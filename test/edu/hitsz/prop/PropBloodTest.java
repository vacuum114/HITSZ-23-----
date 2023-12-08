package edu.hitsz.prop;

import edu.hitsz.Factory.EliteEnemyFactory;
import edu.hitsz.Factory.PropBloodFactory;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropBloodTest {

    HeroAircraft hero= HeroAircraft.getInstance();
    @Test
    @DisplayName("道具移动测试")
    void forward() {
        AbstractPropObject pb=new PropBloodFactory().creator(200,150);
        int x1=pb.getLocationX(),y1=pb.getLocationY();
        int sx=pb.getSpeedX(),sy=pb.getSpeedY();
        pb.forward();
        int x2=pb.getLocationX(),y2=pb.getLocationY();
        System.out.println("运动前X坐标:"+x1+" "+"运动前Y坐标:"+y1);
        System.out.println("X轴上的运动速度:"+sx+" "+"Y轴上的运动速度:"+sy);
        System.out.println("运动后X坐标:"+x2+" "+"运动后Y坐标:"+y2);
        assertAll(()->assertEquals(x1+sx,x2),()->assertEquals(y1+sy,y2));
    }

    @Test
    @DisplayName("回血道具生效测试")
    void function() {
        AbstractPropObject pb=new PropBloodFactory().creator(200,200);
        hero.decreaseHp(100);
        int hp1=hero.getHp();
        pb.function();
        int hp2=hero.getHp();
        System.out.println("获得回血道具前血量："+hp1+" "+"获得回血道具后血量："+hp2);
        assertEquals(hp1+30,hp2);


    }
}