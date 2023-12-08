package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class effectDuration extends Thread {
    static private int duration=0;
    private int setDuration;
    private HeroAircraft hero;
    static private effectDuration instance;
    @Override
    public void run()
    {
        while(duration>0)
        {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            --duration;
        }
        hero.decreaseBullet();
    }
    public effectDuration(int duration, HeroAircraft hero)
    {
        this.setDuration=duration;
        this.hero=hero;
    }
    static public effectDuration getInstance(int dur, HeroAircraft hero)
    {
        if(duration<=0)
        {
            instance=new effectDuration(dur,hero);
        }
        return instance;
    }
    public void reSetDuration()
    {
        duration=setDuration;
    }
    static public boolean newOne()
    {
        if(duration<=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
