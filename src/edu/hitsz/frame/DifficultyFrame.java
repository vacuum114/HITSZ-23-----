package edu.hitsz.frame;

import edu.hitsz.application.ImageManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 难度选择界面
 */
public class DifficultyFrame extends JFrame implements MouseListener {
    static public JFrame frame;
    private JPanel panel;

    /**
     * 三种难度的选择按钮
     */
    private JButton easy=new JButton("简单");
    private JButton normal=new JButton("普通");
    private JButton hard=new JButton("困难");
    /**
     * 难度选择的结果
     * 0表示简单难度，1表示普通难度，2表示困难难度
     */
    static public int difficultySelection;
    public DifficultyFrame()
    {
        //初始化界面
        frame=new JFrame("难度选择");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        //初始化容器
        panel=new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(null);
        //调用函数，添加难度按钮
        difficulty();
        //设置界面可见
        frame.setVisible(true);
    }
    private void difficulty()
    {
        //设置组件的大小和位置
        JLabel title=new JLabel("难度选择");
        title.setBounds(75,30,200,70);
        easy.setBounds(75,100,150,50);
        normal.setBounds(75,175,150,50);
        hard.setBounds(75,250,150,50);
        //添加按钮的事件
        easy.addMouseListener(this);
        normal.addMouseListener(this);
        hard.addMouseListener(this);
        //添加组件到容器中
        panel.add(title);
        panel.add(easy);
        panel.add(normal);
        panel.add(hard);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==easy)
        {
            difficultySelection=0;
        }
        else if(e.getSource()==normal)
        {
            difficultySelection=1;
        }
        else if(e.getSource()==hard)
        {
            difficultySelection=2;
        }
        new GameFrame();
        ImageManager.setBackGround();
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
