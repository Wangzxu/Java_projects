package mapgame.ui;

import mapgame.domain.Saved;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class GameJFram extends JFrame implements KeyListener, ActionListener {

    private int[][] data = new int[4][4];
    private int x=0,y=0;
    String path = "image\\animal\\animal3\\";
    int[][] win = {{1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}};
    int count = 0;

    JMenuItem reset = new JMenuItem("重新游戏");
    JMenuItem close = new JMenuItem("关闭游戏");
    JMenuItem relogin = new JMenuItem("重新登陆");
    JMenu exchange = new JMenu("更换照片");
    JMenu save = new JMenu("存档");
    JMenu read = new JMenu("读档");

    JMenuItem grils = new JMenuItem("美女");
    JMenuItem animals = new JMenuItem("动物");
    JMenuItem sports = new JMenuItem("运动");

    JMenuItem qq = new JMenuItem("公众号");

    JLabel winjlabel = new JLabel(new ImageIcon("image\\win.png"));

    JMenuItem save0 = new JMenuItem("存档0（空）");
    JMenuItem save1 = new JMenuItem("存档1（空）");
    JMenuItem save2 = new JMenuItem("存档2（空）");
    JMenuItem save3 = new JMenuItem("存档3（空）");
    JMenuItem save4 = new JMenuItem("存档4（空）");

    JMenuItem read0 = new JMenuItem("读档0（空）");
    JMenuItem read1 = new JMenuItem("读档1（空）");
    JMenuItem read2 = new JMenuItem("读档2（空）");
    JMenuItem read3 = new JMenuItem("读档3（空）");
    JMenuItem read4 = new JMenuItem("读档4（空）");




    public GameJFram(){
        initJFrame();


        //初始化菜单
        initJMenu();

        // 初始化数据
        initData();

        //初始化图片
        initImage();


        this.setVisible(true);



    }

    private void initData() {
        int[] temparr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random = new Random();
        for (int i = 0; i < temparr.length; i++) {
            int num = random.nextInt(temparr.length);
            int tmp = temparr[i];
            temparr[i] = temparr[num];
            temparr[num] = tmp;

        }

        for (int i = 0; i < temparr.length; i++) {
            if (temparr[i]==0){
                x = i/4;
                y = i%4;
            }
            data[i/4][i%4] = temparr[i];
        }
        //System.out.println("Data已经变动");

    }

    private void initImage() {
        this.getContentPane().removeAll();

        if(judge()){
            winjlabel.setBounds(203,283,197,73);
            this.getContentPane().add(winjlabel);
            System.out.println("胜利");
        }

        JLabel step = new JLabel("步数为：" + count);
        step.setBounds(50,30,100,20);
        this.getContentPane().add(step);

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                JLabel l = new JLabel(new ImageIcon(path +data[i][j]+".jpg"));
                l.setBounds(j*105+83,i*105+134,105,105);
                l.setBorder(new BevelBorder(0));
                this.getContentPane().add(l);
            }
        }

        //设置背景图片
        JLabel bg = new JLabel(new ImageIcon("image\\background.png"));
        bg.setBounds(40,40,508,560);
        this.getContentPane().add(bg);

        this.getContentPane().repaint();


    }

    private void initJMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu funmenu = new JMenu("功能");
        JMenu aboutmenu = new JMenu("关于我们");

        relogin.addActionListener(this);
        reset.addActionListener(this);
        close.addActionListener(this);
        qq.addActionListener(this);
        exchange.addActionListener(this);

        grils.addActionListener(this);
        sports.addActionListener(this);
        animals.addActionListener(this);


        exchange.add(grils);
        exchange.add(animals);
        exchange.add(sports);

        funmenu.add(exchange);
        funmenu.add(reset);
        funmenu.add(relogin);
        funmenu.add(close);

        aboutmenu.add(qq);

        read0.addActionListener(this);
        read1.addActionListener(this);
        read2.addActionListener(this);
        read3.addActionListener(this);
        read4.addActionListener(this);

        save0.addActionListener(this);
        save1.addActionListener(this);
        save2.addActionListener(this);
        save3.addActionListener(this);
        save4.addActionListener(this);



        read.add(read0);
        read.add(read1);
        read.add(read2);
        read.add(read3);
        read.add(read4);

        save.add(save0);
        save.add(save1);
        save.add(save2);
        save.add(save3);
        save.add(save4);

        funmenu.add(read);
        funmenu.add(save);



        bar.add(funmenu);
        bar.add(aboutmenu);

        getInfo();

        this.addKeyListener(this);

        this.setJMenuBar(bar);
    }

    private void getInfo() {
        File file = new File("mapgame\\gameData\\Saveds");
        File[] files = file.listFiles();
        for (File file1 : files) {
            int n = file1.getName().charAt(5) - '0';
//            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file1))) {
//                Saved saved = (Saved) in.readObject();
//
//            }catch (IOException | ClassNotFoundException ine){
//                ine.printStackTrace();
//            }
            save.getItem(n).setText("存档"+n);
            read.getItem(n).setText("读档"+n);

        }
    }

    private void initJFrame() {
        int n = new Random().nextInt(8) + 1;
        path = "image\\animal\\animal" + n +"\\";
        this.setSize(603,680);
        this.setTitle("拼图单机版");

        this.setLayout(null);// 设置默认布局取消


        this.setAlwaysOnTop(true);// 置顶
        this.setLocationRelativeTo(null);// 居中
        this.setDefaultCloseOperation(3);

        this.setFocusable(true);
        this.setFocusableWindowState(true);
        this.requestFocusInWindow();
    }
    private boolean judge() {
        //System.out.println("判断结束");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //System.out.println(code);
        if(code==65){
            this.getContentPane().removeAll();
            JLabel l = new JLabel(new ImageIcon(path + "all.jpg"));
            l.setBounds(83,134,420,420);
            this.getContentPane().add(l);

            //设置背景图片
            JLabel bg = new JLabel(new ImageIcon("D:\\code_java\\image\\background.png"));
            bg.setBounds(40,40,508,560);
            this.getContentPane().add(bg);

            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(judge()){
            System.out.println("您已经胜利");
            return;
        }
        int code = e.getKeyCode();
        if(code==37){
            if(y+1<4){
                data[x][y] = data[x][y+1];
                data[x][y+1] = 0;
                y++;
                count++;
            }

            System.out.println("向左移动");
        }
        if(code==38){
                if(x+1<4){
                data[x][y] = data[x+1][y];
                data[x+1][y] = 0;
                x++;
                count++;
            }
            System.out.println("向上移动");
        }
        if(code==39){
            if(y-1>=0){
                data[x][y] = data[x][y-1];
                data[x][y-1] = 0;
                y--;
                count++;
            }
            System.out.println("向右移动");
        }
        if(code==40){
            if(x-1>=0){
                data[x][y] = data[x-1][y];
                data[x-1][y] = 0;
                x--;
                count++;
            }
            System.out.println("向下移动");
        }
        if(code==87){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    data[i][j] = win[i][j];
                }
            }
            x = 3;y=3;
        }
        System.out.println(count);
        initImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj =e.getSource();

        if (obj==reset){
            System.out.println("重新游戏");

            count = 0;
            initData();



        }else if(obj==relogin){
            System.out.println("重新登录");
            initData();
            LoginJFram loginJFram = new LoginJFram();
            this.dispose();

        }else if(obj==close){
            System.out.println("退出游戏");
            System.exit(0);

        }else if(obj==qq){
            System.out.println("qq号");
            JDialog jDialog = new JDialog();
            JLabel dialog = new JLabel(new ImageIcon("image\\about.png"));
            dialog.setBounds(0,0,258,258);
            jDialog.getContentPane().add(dialog);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);

        }else if(obj==grils){
            Random rand = new Random();
            int num = rand.nextInt(13) + 1;
            path = "image\\girl\\girl" + num + "\\";
            count = 0;
            initData();

        }else if(obj==animals){
            Random rand = new Random();
            int num = rand.nextInt(8) + 1;
            path = "image\\animal\\animal" + num + "\\";
            count = 0;
            initData();

        }else if(obj==sports){
            Random rand = new Random();
            int num = rand.nextInt(10) + 1;
            path = "image\\sport\\sport" + num + "\\";
            count = 0;
            initData();

        }else if(obj==save0 ||obj==save1 ||obj==save2 ||obj==save3 ||obj==save4){
            JMenuItem item= (JMenuItem) obj;
            String str = item.getText();
            int n = str.charAt(2) - '0';
            File file = new File("mapgame\\gameData\\Saveds\\saved" + n + ".data");

            Saved saved = new Saved(path,count,data,x,y);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(saved);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            getInfo();
            System.out.println("已存入");

        }else if(obj==read0 || obj==read1 ||obj==read2 ||obj==read3||obj==read4){
            JMenuItem item= (JMenuItem) obj;
            String str = item.getText();
            int n = str.charAt(2) - '0';
            File file = new File("mapgame\\gameData\\Saveds\\saved" + n + ".data");

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                Saved saved = (Saved) in.readObject();
                count = saved.step;
                path = saved.path;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        data[i][j] = saved.data[i][j];
                    }
                }
                x = saved.x;
                y = saved.y;
            }catch(IOException | ClassNotFoundException ine){
                ine.printStackTrace();
            }
            initImage();
            System.out.println("已读取");




        }

        initImage();

    }
}
