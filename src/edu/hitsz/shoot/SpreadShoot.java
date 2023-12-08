package edu.hitsz.shoot;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpreadShoot implements ShootMethod{
    @Override
    public List<BaseBullet> shoot(int LocationX,int LoactionY,int speedX,int speedY,int power,int shootNum,int direction) {
        if(shootNum==0){return new ArrayList<BaseBullet>();}
        List<BaseBullet> res = new LinkedList<>();
        int x = LocationX;
        int y = LoactionY + direction*2;
        int sY = speedY + direction*5;
        BaseBullet bullet=null;
        int sX=-shootNum/2;
        if(shootNum%2==0){++sX;}
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(direction==-1) {
                bullet = new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, sX, sY, power);
            }
            else if(direction==1)
            {
                bullet=new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, sX, sY, power);
            }
            res.add(bullet);
            ++sX;
            if(shootNum%2==0&&i==shootNum/2-1){--sX;}
        }
        return res;
    }
}
