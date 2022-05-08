import components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends BaseFrame {
    MyLabel userlb, passlb;
    MyTextField usertf;
    MyPasswordField passtf;
    SuccessButton loginbtn, cancelbtn;
    GridBagLayout gb;
    GridBagConstraints gbc;

    LoginPage(String user, MyClient mc){

        super(user+" Login");
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();

        userlb = new MyLabel("Username ");
        passlb = new MyLabel("Password ");
        usertf = new MyTextField(20);
        passtf = new MyPasswordField(20);
        loginbtn = new SuccessButton("Login");
        cancelbtn = new SuccessButton("Cancel");

        MyPanel form = new MyPanel();
        form.setLayout(gb);

        MyPanel btns = new MyPanel();
        btns.setLayout(gb);

        MyPanel cent = new MyPanel();
        cent.setLayout(gb);

        //------------event handling--------------

        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main(mc);
                dispose();
            }
        });

        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uname = usertf.getText();
                String upass = String.valueOf(passtf.getPassword());

                if(user.equalsIgnoreCase("Admin")){
                    if(uname.equals("admin") && upass.equals("admin")){
                        new AdminPage(mc);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(cent, "Username or Password is incorrect!");
                    }
                }
                else if(user.equalsIgnoreCase("Teacher")){
                    String query = "select * from teachers where teacher_id = "+uname+" and password = '"+upass+"'";
                    if(mc.teacherlogin(query)){
                        new TeacherPage(mc, mc.rs);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(cent, "Username or Password is incorrect!");
                    }
                }
                else if(user.equalsIgnoreCase("Student")){
                    String query = "select * from students where student_id = '"+uname+"' and password = '"+upass+"'";
                    if(mc.studentlogin(query)){
                        new StudentPage(mc, mc.rs);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(cent, "Username or Password is incorrect!");
                    }
                }
            }
        });

        //--------form------------------

        gbc.ipady=0;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=5;
        gbc.insets.right=20;
        form.add(userlb, gbc);

        gbc.gridx++;
        gbc.insets.right=0;
        form.add(usertf, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.insets.right=20;
        gbc.insets.bottom=50;
        form.add(passlb, gbc);

        gbc.gridx++;
        gbc.insets.right=0;
        form.add(passtf, gbc);

        //----------buttons------------

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.ipadx=30;
        gbc.insets.right=10;
        gbc.insets.bottom=0;
        btns.add(loginbtn, gbc);

        gbc.gridx++;
        gbc.ipadx=30;
        btns.add(cancelbtn, gbc);

        //----------Center------------
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.right=0;
        gbc.insets.bottom=0;
        cent.add(form, gbc);

        gbc.gridy++;
        cent.add(btns, gbc);

        BorderLayout bl = new BorderLayout(20,20);
        setLayout(bl);
        add(new SchoolHeader(), BorderLayout.NORTH);
        add(cent, BorderLayout.CENTER);
    }
}
