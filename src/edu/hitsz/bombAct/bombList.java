package edu.hitsz.bombAct;

import java.util.ArrayList;
import java.util.List;

public class bombList {
    private List<bombActivate>list;
    public bombList()
    {
        list=new ArrayList<>();
    }
    public void add(bombActivate a)
    {
        list.add(a);
    }
    public void remove(bombActivate a)
    {
        list.remove(a);
    }
    public void activate()
    {
        for(bombActivate b:list)
        {
            b.update();
        }
    }
}
