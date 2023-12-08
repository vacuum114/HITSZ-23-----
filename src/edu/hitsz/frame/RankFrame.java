package edu.hitsz.frame;

import edu.hitsz.playerDate.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * 排行榜界面
 */
public class RankFrame extends  JFrame implements MouseListener {
    /**
     * 用于展示排行榜的主体界面
     */
    public static JFrame rankFrame;
    private JPanel panel;
    private DefaultTableModel model;
    private JTable rankTable=new JTable();
    private JScrollPane rankScroll=new JScrollPane();
    private JButton deleteButton=new JButton("删除");
    private JButton backToMenu=new JButton("返回主菜单");
    private playerDao date=new playerDaoImpl();
    /**
     * 下面内容只有在游戏结束后的排行榜界面才会显示
     * 显示玩家得分，并选择是否保存
     */
    static private JButton saveButton=new JButton("保存成绩");
    static public int score;
    static private JLabel nowScore=new JLabel();
    /**
     * 游戏结束后，玩家点击保存成绩时弹出
     * 用于玩家输入名称并保存
     */
    private JFrame saveFrame=new JFrame();
    private JButton saveAffirm=new JButton("保存");;
    private JButton saveBack=new JButton("返回");
    private JTextField inputField=new JTextField();;
    private String name;
    /**
     * 用于玩家确定是否删除玩家数据
     */
    private JFrame deleteFrame=new JFrame();
    private JButton deleteBack=new JButton("取消");
    private JButton deleteAffirm=new JButton("确认");
    public RankFrame()
    {
        rankFrame=new JFrame("玩家排行榜");
        panel=new JPanel();
        initialFrame();
        initialRank();
        initialDelete();
        initialSave();
        //去掉删除界面和保存界面的窗口标题栏
        deleteFrame.setUndecorated(true);
        saveFrame.setUndecorated(true);
    }
    private void initialFrame()
    {
        //初始化排行榜界面
        rankFrame.setSize(515,600);
        rankFrame.setLocationRelativeTo(null);
        rankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化排行榜界面的容器
        rankFrame.setContentPane(panel);
        panel.setBounds(0,0,400,500);
        panel.setLayout(null);
        //展示排行榜内容
        updateRank();
        //初始化玩家名称输入界面
        saveFrame.setSize(300,200);
        saveFrame.setLocationRelativeTo(null);
        saveFrame.setAlwaysOnTop(true);
        //设置保存按钮和当前成绩展示不可见，设置界面可见
        saveButton.setVisible(false);
        nowScore.setVisible(false);
        rankFrame.setVisible(true);
    }

    /**
     * 初始化排行榜
     */
    private void initialRank()
    {
        date.load();
        int playerNum=date.getPlayerNum();
        List<player> players=date.getAllPlayer();
        String[]title={"排名","玩家名称","分数"," 记录时间"};
        String[][]playerDate=new String[playerNum][4];
        int k=0;
        for(player p:players)
        {
            playerDate[k][0]=k+1+"";
            playerDate[k][1]=p.getName();
            playerDate[k][2]=p.getScore()+"";
            playerDate[k][3]=p.getTime();
            ++k;
        }
        if(model!=null)
        {
            model.getDataVector().removeAllElements();
        }
        model=new DefaultTableModel(playerDate,title){
            @Override
            public boolean isCellEditable(int row,int col)
            {
                return false;
            }
        };
        rankTable.setModel(model);
        rankScroll.setViewportView(rankTable);
        //设置列表每列的宽度
        rankTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        rankTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        rankTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        rankTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        //设置各个组件的位置和大小
        JLabel label=new JLabel("排行榜");
        label.setBounds(220,0,200,50);
        rankTable.setBounds(0,50,500,300);
        rankScroll.setBounds(0,50,500,300);
        deleteButton.setBounds(200,360,100,40);
        nowScore.setBounds(40,420,100,30);
        saveButton.setBounds(50,450,100,50);
        backToMenu.setBounds(200,450,100,50);
        //添加按钮的事件
        backToMenu.addMouseListener(this);
        deleteButton.addMouseListener(this);
        saveButton.addMouseListener(this);
        //将组件添加到容器中
        panel.add(label);
        panel.add(rankScroll);
        panel.add(deleteButton);
        panel.add(backToMenu);
        panel.add(saveButton);
        panel.add(nowScore);
    }

    /**
     * 初始化输入玩家名称并保存成绩的界面
     *
     * 保存玩家数据界面
     * 游戏结束后，点击保存按钮弹出，用于输入玩家名称
     *
     */
    private void initialSave()
    {
        //初始化保存界面的容器
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(255,255,255));
        p.setBorder(new LineBorder(Color.black,1,true));
        saveFrame.setContentPane(p);
        //设置各个组件的位置和大小
        JLabel inputLabel=new JLabel("输入玩家名称");
        inputLabel.setBounds(5,30,80,30);
        inputField.setBounds(95,30,150,40);
        saveAffirm.setBounds(20,90,100,50);
        saveBack.setBounds(140,90,100,50);
        //添加按钮的事件
        saveAffirm.addMouseListener(this);
        saveBack.addMouseListener(this);
        //将组件添加到容器中
        saveFrame.add(inputLabel);
        saveFrame.add(inputField);
        saveFrame.add(saveAffirm);
        saveFrame.add(saveBack);
    }
    /**
     *
     * 初始化确认删除界面
     *在点击删除按钮后弹出，选择是否删除当前选中的列上的玩家数据
     */
    private void initialDelete()
    {
        //初始化确认删除界面
        deleteFrame.setAlwaysOnTop(true);
        deleteFrame.setSize(300,200);
        deleteFrame.setLocationRelativeTo(null);
        // 去掉窗口上的标题栏
        deleteFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //初始化确认删除界面的容器
        JPanel jPanel=new JPanel();
        jPanel.setBackground(new Color(255,255,255));
        jPanel.setBorder(new LineBorder(Color.black,1,true));
        deleteFrame.setContentPane(jPanel);
        jPanel.setLayout(null);
        //设置组件大小和位置
        JLabel label=new JLabel("是否删掉玩家数据");
        label.setBounds(100,50,150,50);
        deleteBack.setBounds(175,130,75,30);
        deleteAffirm.setBounds(50,130,75,30);
        //添加按钮的事件
        deleteAffirm.addMouseListener(this);
        deleteBack.addMouseListener(this);
        //添加组件到容器中
        jPanel.add(label);
        jPanel.add(deleteAffirm);
        jPanel.add(deleteBack);
    }
    /**
     * 更新排行榜
     */
    private void updateRank()
    {
        //导入数据，填充列表内容
        date.load();
        int playerNum=date.getPlayerNum();
        List<player> players=date.getAllPlayer();
        String[]title={"排名","玩家名称","分数"," 记录时间"};
        String[][]playerDate=new String[playerNum][4];
        int k=0;
        for(player p:players)
        {
            playerDate[k][0]=k+1+"";
            playerDate[k][1]=p.getName();
            playerDate[k][2]=p.getScore()+"";
            playerDate[k][3]=p.getTime();
            ++k;
        }
        if(model!=null) {
            model.getDataVector().removeAllElements();
            model.addRow(title);
            for (int i = 0; i < k; ++i) {
                model.addRow(playerDate[i]);
            }
        }
        saveButton.setVisible(false);
        nowScore.setVisible(false);
    }
    /**
     * 展示保存成绩的按钮和当前得分
     */
    static public void showSave()
    {
        nowScore.setText("当前成绩："+score);
        saveButton.setVisible(true);
        nowScore.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backToMenu)
        {
            MainMenu.frame.setVisible(true);
            saveButton.setVisible(false);
            nowScore.setVisible(false);
            rankFrame.setVisible(false);
        }
        else if(e.getSource()==deleteButton)
        {
            deleteFrame.setVisible(true);
            rankFrame.setEnabled(false);
        }
        else if(e.getSource()==saveButton)
        {
            saveFrame.setVisible(true);
            rankFrame.setEnabled(false);
        }
        else if(e.getSource()==saveAffirm)
        {
            name=inputField.getText();
            inputField.setText("");
            playerDao temp=new playerDaoImpl();
            temp.addPlayer(new player(name,score));
            updateRank();
            saveFrame.setVisible(false);
            rankFrame.setEnabled(true);
        }
        else if(e.getSource()==saveBack)
        {
            saveFrame.setVisible(false);
            rankFrame.setEnabled(true);
        }
        else if(e.getSource()==deleteAffirm)
        {
            int row=rankTable.getSelectedRow();
            System.out.println(row);
            if(row!=-1)
            {
                model.removeRow(row);
                date.deletePlayer(row-1);
            }
            updateRank();
            rankFrame.setEnabled(true);
            deleteFrame.setVisible(false);
        }
        else if(e.getSource()==deleteBack)
        {
            rankFrame.setEnabled(true);
            deleteFrame.setVisible(false);
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
