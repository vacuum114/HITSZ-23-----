package edu.hitsz.shoot;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class StraightShoot implements ShootMethod{
    @Override
    public List<BaseBullet> shoot(int LocationX,int LoactionY,int speedX,int speedY,int power,int shootNum,int direction) {
        if(shootNum==0){return new ArrayList<BaseBullet>();}
        List<BaseBullet> res = new LinkedList<>();
        BaseBullet bullet=null;
        int x = LocationX;
        int y = LoactionY + direction*2;
        int sX=speedX;
        int sY = speedY + direction*5;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            if(direction==-1) {
                bullet = new HeroBullet(x + (i * 2 - shootNum + 1) * 10,y, sX, sY, power);
            }else if(direction==1)
            {
                bullet=new EnemyBullet(x + (i * 2 - shootNum + 1) * 10,y, sX, sY, power);
            }
            res.add(bullet);
        }
        return res;
    }
}
