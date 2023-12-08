package edu.hitsz.playerDate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class player {
    private String name;
    private int score;
    private String time;
    public player(String name,int score)
    {
        this.name=name;
        this.score=score;
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        time=sdf.format(date);
    }
    public player(String name,int score,String time)
    {
        this.name=name;
        this.score=score;
        this.time=time;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score)
    {
        this.score=score;
    }
    public String getName() {
        return name;
    }
    public String getTime(){return time;}
    public void setName(String name)
    {
        this.name=name;
    }

}
