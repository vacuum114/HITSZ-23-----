package edu.hitsz.frame;

import edu.hitsz.application.EasyGame;
import edu.hitsz.application.Game;
import edu.hitsz.application.HardGame;
import edu.hitsz.application.NormalGame;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏界面
 */
public class GameFrame extends JFrame {
    public static JFrame frame;
    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public GameFrame()
    {
        //初始化游戏界面
        frame=new JFrame("游戏界面");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        Game game;
        if(DifficultyFrame.difficultySelection==0)
        {
            game=new EasyGame();
        }
        else if(DifficultyFrame.difficultySelection==1)
        {
            game=new NormalGame();
        }
        else
        {
            game=new HardGame();
        }
        frame.add(game);
        frame.setVisible(true);
        game.action();
    }
}
