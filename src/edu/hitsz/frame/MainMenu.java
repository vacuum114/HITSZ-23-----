package edu.hitsz.frame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 主菜单
 */
public class MainMenu extends JFrame implements MouseListener {
    /**
     * 主菜单界面
     */
    static public JFrame frame;
    private JPanel panel;
    /**
     * 游戏开始按钮，按下后进入难度选择界面
     */
    private JButton startButton;
    /**
     * 玩家排行榜按钮，按下后进入排行榜界面
     */
    private JButton rankButton;
    /**
     * 游戏设置按钮，按下后进入设置界面
     */
    private JButton settingButton;

    /**
     * 初始化界面
     */
    public MainMenu() {
        //初始化主菜单界面
        frame = new JFrame("主菜单");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,600);
        frame.setLocationRelativeTo(null);
        //初始化主菜单容器
        panel = new JPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);
        //调动函数，添加按钮
        showButton();
        //设置界面可见
        frame.setVisible(true);
    }

    /**
     * 添加按钮
     */
    private void showButton()
    {
        //初始化按钮
        startButton =new JButton("开始游戏");
        rankButton=new JButton("排行榜");
        settingButton=new JButton("设置");
        //设置按钮大小和位置
        startButton.setBounds(100,50,200,100);
        rankButton.setBounds(100,200,200,100);
        settingButton.setBounds(100,350,200,100);
        //添加按钮的事件
        startButton.addMouseListener(this);
        rankButton.addMouseListener(this);
        settingButton.addMouseListener(this);
        //添加组件到容器中
        panel.add(startButton);
        panel.add(rankButton);
        panel.add(settingButton);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== startButton)
        {
            if(DifficultyFrame.frame==null) {
                new DifficultyFrame();
            }
            else
            {
                DifficultyFrame.frame.setVisible(true);
            }
        }
        else if(e.getSource()==rankButton)
        {
            if(RankFrame.rankFrame==null)
            {
                new RankFrame();
            }
            else
            {
                RankFrame.rankFrame.setVisible(true);
            }
        }
        else if(e.getSource()==settingButton)
        {
            if(SettingFrame.settingFrame==null)
            {
                new SettingFrame();
            }
            else
            {
                SettingFrame.settingFrame.setVisible(true);
            }
        }
        frame.setVisible(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
