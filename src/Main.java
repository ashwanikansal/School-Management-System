import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.BaseFrame;
import components.SchoolLabel;
import components.SuccessButton;

public class Main extends BaseFrame {

    private JPanel btnPanel;
    private SuccessButton admin, teacher, student;

    Main(){
        super("St. Mary's Convent School");

        UIManager.put("defaultFont", new Font("Arial", Font.PLAIN, 16));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);

        admin = new SuccessButton("Admin");
        teacher = new SuccessButton("Teacher");
        student = new SuccessButton("Student");
        btnPanel = new JPanel();

        btnPanel.add(admin);
        btnPanel.add(teacher);
        btnPanel.add(student);

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage admlgin = new LoginPage("Admin");
                dispose();
                admlgin.setVisible(true);
            }
        });
        teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage tlgn = new LoginPage("Teacher");
                dispose();
                tlgn.setVisible(true);
            }
        });
        student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage slgn = new LoginPage("Student");
                dispose();
                slgn.setVisible(true);
            }
        });

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.ipady=80;
        add(new SchoolLabel(), gbc);

        gbc.gridy++;
        add(btnPanel, gbc);

    }

    public static void main(String[] args) {
        Main fp = new Main();
        fp.setVisible(true);
    }
}
