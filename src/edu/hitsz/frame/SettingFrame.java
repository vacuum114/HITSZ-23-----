package edu.hitsz.frame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 设置界面
 */
public class SettingFrame extends JFrame implements MouseListener {
    /**
     * 设置面板的界面和容器
     */
    public static JFrame settingFrame;
    private JPanel panel;
    /**
     * 设置声音开关的按钮
     */
    private JButton voiceOnButton;
    private JButton voiceOffButton;
    /**
     * 返回主菜单按钮
     */
    private JButton backButton;
    /**
     * 音效选择，为1时开启音效，为0时关闭音效
     */
    public static int voiceSeletion=1;
    public SettingFrame()
    {
        //初始化界面
        settingFrame=new JFrame("设置界面");
        settingFrame.setSize(400,600);
        settingFrame.setLocationRelativeTo(null);
        settingFrame.setResizable(false);
        settingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化容器
        panel=new JPanel();
        settingFrame.setContentPane(panel);
        panel.setBounds(0,0,400,600);
        panel.setLayout(null);
        SettingButton();
        //设置界面可见
        settingFrame.setVisible(true);

    }
    private void SettingButton()
    {
        //初始化按钮
        voiceOnButton=new JButton("音效开启");
        voiceOffButton=new JButton("音效关闭");
        backButton=new JButton("返回");
        //设置组件位置和大小
        JLabel voiceLabel=new JLabel("音效选择",JLabel.CENTER);
        voiceLabel.setFont(new Font("微软雅黑",Font.BOLD,15));
        voiceLabel.setBounds(0,20,390,70);
        voiceLabel.setBorder(new LineBorder(Color.black,2,false));
        voiceOffButton.setBounds(225,100,100,40);
        voiceOnButton.setBounds(75,100,100,40);
        backButton.setBounds(150,500,100,50);
        //添加按钮的事件
        voiceOnButton.addMouseListener(this);
        voiceOffButton.addMouseListener(this);
        backButton.addMouseListener(this);
        //添加组件到容器中
        panel.add(voiceLabel);
        panel.add(voiceOffButton);
        panel.add(voiceOnButton);
        panel.add(backButton);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==voiceOnButton)
        {
            voiceSeletion=1;
        }
        else if(e.getSource()==voiceOffButton)
        {
            voiceSeletion=0;
        }
        else if(e.getSource()==backButton)
        {
            MainMenu.frame.setVisible(true);
            settingFrame.setVisible(false);
        }
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
