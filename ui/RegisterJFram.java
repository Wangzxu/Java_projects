package mapgame.ui;

import mapgame.domain.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterJFram extends JFrame implements MouseListener {

    private List<User> data ;

    String registerpath = "image\\register\\注册按钮.png";
    String resetpath = "image\\register\\重置按钮.png";

    JButton reset = new JButton();
    JButton register = new JButton();

    JLabel nametex = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
    JLabel passwordtex = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
    JLabel passwordtex2 = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));

    JTextField name = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField password2 = new JPasswordField();

        public RegisterJFram(){
            InitJFrame();

            InitImage();
        }

    private void InitImage() {
        this.getContentPane().removeAll();
        //添加用户名

        nametex.setBounds(85,134,80,17);
        this.getContentPane().add(nametex);

        name.setBounds(195,134,200,30);
        this.getContentPane().add(name);

        //设置密码
        passwordtex.setBounds(100,195,65,16);
        this.getContentPane().add(passwordtex);
        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

        //设置再次输入密码
        passwordtex2.setBounds(70,256,95,30);
        this.getContentPane().add(passwordtex2);

        password2.setBounds(195,256,200,30);
        this.getContentPane().add(password2);



        //设置注册按钮

        register.setBounds(123,310,128,47);
        register.setIcon(new ImageIcon(registerpath));

        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        register.addMouseListener(this);

        //设置重置按钮

        reset.setBounds(256,310,128,47);
        reset.setIcon(new ImageIcon(resetpath));

        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        this.getContentPane().add(reset);

        reset.addMouseListener(this);


        //设置背景

        JLabel bg = new JLabel(new ImageIcon("image\\login\\background.png"));
        bg.setBounds(0,0,470,390);
        bg.setVisible(true);
        this.getContentPane().add(bg);
        this.getContentPane().repaint();

    }

    private void InitJFrame() {
        this.setLayout(null);

        this.setSize(488,445);
        this.setTitle("拼图 注册");


        this.setAlwaysOnTop(true);// 置顶
        this.setLocationRelativeTo(null);// 居中
        this.setDefaultCloseOperation(3);

        this.setVisible(true);

        getData();
    }

    private void saveData() {
        File file = new File("mapgame/gameData/userdata");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(data);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void getData() {
        File file = new File("mapgame/gameData/userdata");
        if(!file.exists()){
            data = new ArrayList<User>();
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
            if(obj==reset){
                password.setText("");
                password2.setText("");
                name.setText("");
            }else if(obj==register){

                if(name.getText().isEmpty()||password.getPassword().length==0||password2.getPassword().length==0){
                    JOptionPane.showMessageDialog(this,"名称或密码不能为空，请重新输入！");
                    return;
                }
                else if(!Arrays.equals(password.getPassword(),password2.getPassword())){
                    JOptionPane.showMessageDialog(this,"两次密码不一致请重新输入！");
                    password2.setText("");
                    password.setText("");
                    return;

                }
                else {
                    User user = new User(name.getText(),new String(password.getPassword()));
                    data.add(user);
                    saveData();
                    JOptionPane.showMessageDialog(this,"注册成功！");
                    new LoginJFram();
                    this.dispose();

                }

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
        if(obj==register){
            registerpath = "image\\register\\注册按下.png";
            register.setIcon(new ImageIcon(registerpath));
        }else if(obj==reset){
            resetpath = "image\\register\\重置按下.png";
            reset.setIcon(new ImageIcon(resetpath));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object obj = e.getSource();
        if(obj==register){
            registerpath = "image\\register\\注册按钮.png";
            register.setIcon(new ImageIcon(registerpath));
        }else if(obj==reset){
            resetpath = "image\\register\\重置按钮.png";
            reset.setIcon(new ImageIcon(resetpath));
        }
    }


}
