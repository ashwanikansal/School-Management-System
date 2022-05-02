import components.BaseFrame;
import components.SchoolHeader;
import components.SuccessButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends BaseFrame {
    JLabel userlb, passlb;
    JTextField usertf;
    JPasswordField passtf;
    SuccessButton loginbtn, cancelbtn;

    LoginPage(String user){

        super(user+" Login");
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        userlb = new JLabel("Username ");
        passlb = new JLabel("Password ");
        usertf = new JTextField(20);
        passtf = new JPasswordField(20);
        loginbtn = new SuccessButton("Login");
        cancelbtn = new SuccessButton("Cancel");

        JPanel form = new JPanel();
        form.setLayout(gb);

        JPanel btns = new JPanel();
        btns.setLayout(gb);

        JPanel cent = new JPanel();
        cent.setLayout(gb);

        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main f = new Main();
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
                        AdminPage ap = new AdminPage();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(cent, "Username or Password is incorrect!");
                    }
                }
                if(user.equalsIgnoreCase("Teacher")){

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
