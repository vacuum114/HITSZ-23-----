package edu.hitsz.Factory;

import edu.hitsz.prop.AbstractPropObject;

public interface PropFactory
{
    public abstract AbstractPropObject creator(int x,int y);
}
