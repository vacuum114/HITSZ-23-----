package edu.hitsz.playerDate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class playerDaoImpl implements playerDao{
    private static List<player>playerList;
    private static int playerNum;
    @Override
    public void load(){
        playerList=new ArrayList<>();
        try {
            BufferedReader br=new BufferedReader(new FileReader("src\\edu\\hitsz\\playerDate\\playerdate.txt"));
            playerNum=Integer.parseInt(br.readLine());
            String temp;
            int i=0;
            while(i<playerNum)
            {
                temp= br.readLine();
                String[]arr=temp.split("-");
                player k=new player(arr[0],Integer.parseInt(arr[1]),arr[2]);
                playerList.add(k);
                ++i;
            }
            playerList.sort((player t1,player t2)->t2.getScore()-t1.getScore() );
            br.close();
            //Todo 文件中的玩家信息读取，并用正则表达式转化为需要的形式，再创建player对象，并加入playerlsit中，最后根据分数进行排序
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public  void update() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\edu\\hitsz\\playerDate\\playerdate.txt"));
            bw.write(playerNum+"\n");
            for (player k : playerList) {
                String str = k.getName() + "-" + k.getScore()+"-"+k.getTime()+"\n";
                bw.write(str);
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void findByName(String name) {
        for(player p:playerList)
        {
            if((p.getName()).equals(name))
            {
                System.out.println("name:"+p.getName()+" "+"score:"+p.getScore());
                return;
            }
        }
        System.out.println("Can not find this player");
    }
    @Override
    public List<player> getAllPlayer() {
        return playerList;
    }

    @Override
    public void addPlayer(player p) {
        playerList.add(p);
        ++playerNum;
        System.out.println("add a new player:name "+p.getName()+" score "+p.getScore());
        playerList.sort((player t1,player t2)->t2.getScore()-t1.getScore());
        update();
    }
    @Override
    public void deletePlayer(int num) {
        if(num<0||num>playerList.size()){
            System.out.println("Can not find this player");
        }
        else
        {
            System.out.println("delete player"+playerList.get(num).getName());
            playerList.remove(num);
            playerNum--;
            update();
        }
    }
    public int getPlayerNum()
    {
        return playerNum;
    }


}
