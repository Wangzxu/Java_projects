package mapgame.ui;

import mapgame.domain.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoginJFram extends JFrame implements MouseListener {
    private List<User> data ;

    String loginpath = "image\\login\\登录按钮.png";
    String registpath = "image\\login\\注册按钮.png";

    JButton login = new JButton();
    JButton register = new JButton();

    JLabel rightcode = new JLabel();
    String right;

    JLabel nametex = new JLabel(new ImageIcon("image\\login\\用户名.png"));
    JLabel passwordtex = new JLabel(new ImageIcon("image\\login\\密码.png"));
    JLabel codetex  = new JLabel(new ImageIcon("image\\login\\验证码.png"));

    JTextField name = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();





    public LoginJFram(){
        InitJFrame();
        
        InitImage();
    }

    private void InitImage() {

        this.getContentPane().removeAll();
        //添加用户名

        nametex.setBounds(116,134,47,17);
        this.getContentPane().add(nametex);

        name.setBounds(195,134,200,30);
        this.getContentPane().add(name);

        //设置密码
        passwordtex.setBounds(130,195,32,16);
        this.getContentPane().add(passwordtex);
        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

        //设置验证码
        codetex.setBounds(133,256,50,30);
        this.getContentPane().add(codetex);

        code.setBounds(195,256,100,30);
        this.getContentPane().add(code);


        rightcode.setText(right);
        rightcode.setBounds(300,256,50,30);
        rightcode.addMouseListener(this);
        this.getContentPane().add(rightcode);

        //设置登录按钮

        login.setBounds(123,310,128,47);
        login.setIcon(new ImageIcon(loginpath));

        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        login.addMouseListener(this);

        //设置注册按钮

        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon(registpath));

        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        register.addMouseListener(this);


        //设置背景

        JLabel bg = new JLabel(new ImageIcon("image\\login\\background.png"));
        bg.setBounds(0,0,470,390);
        bg.setVisible(true);
        this.getContentPane().add(bg);
        this.getContentPane().repaint();

    }

    private void InitJFrame() {
        this.setSize(488,445);
        this.setTitle("拼图 登录");


        this.setAlwaysOnTop(true);// 置顶
        this.setLocationRelativeTo(null);// 居中
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        generateCode();
        getData();

        this.setVisible(true);
    }


    private void getData() {
        File file = new File("mapgame/gameData/userdata");
        if(!file.exists()){
            data = new ArrayList<>();
        }else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                data = (List<User>) in.readObject();
            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj==rightcode){
            generateCode();
            rightcode.setText(right);
        }else if(obj==login){
            if(name.getText().isEmpty()||password.getPassword().length==0){
                JOptionPane.showMessageDialog(this,"密码或用户名不得为空！");
                return;
            }
            else if(!right.equals(code.getText())){
                JOptionPane.showMessageDialog(this,"验证码错误请重新输入！");
                code.setText("");
                generateCode();
                rightcode.setText(right);
                return;
            }
            else{
                User current = new User(name.getText(),new String(password.getPassword()));
                boolean flag = false;
                for (User user:data) {
                    if(current.equals(user)){
                        flag = true;
                        new GameJFram();
                        this.dispose();
                    }
                }
                if(!flag) {JOptionPane.showMessageDialog(this,"该用户未注册请先注册");return;}
                JOptionPane.showMessageDialog(this,"恭喜你成功登录");
            }


        }else if(obj==register){
            new RegisterJFram();
            this.dispose();
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
        Object obj = e.getSource();
        if(obj==login){
            loginpath = "image\\login\\登录按下.png";
            login.setIcon(new ImageIcon(loginpath)); // 更新按钮图标
        }
        else if(obj==register){
            registpath = "image\\login\\注册按下.png";
            register.setIcon(new ImageIcon(registpath));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getSource();
        if(obj==login){
            loginpath = "image\\login\\登录按钮.png";
            login.setIcon(new ImageIcon(loginpath)); // 更新按钮图标

        }
        else if(obj==register){
            registpath = "image\\login\\注册按钮.png";
            register.setIcon(new ImageIcon(registpath));
        }

    }

    public void generateCode() {
        // 定义验证码的字符集合，可以包含数字和字母
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(5);

        // 随机从字符集合中选择字符，直到达到指定长度
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        right = code.toString();
    }
}
