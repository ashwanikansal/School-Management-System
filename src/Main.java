import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import components.*;

public class Main extends BaseFrame {
    private MyLabel logo;
    private MyPanel btnPanel;
    private SuccessButton admin, teacher, student;

    Main(MyClient mc) {
        super("Hogwarts School of Witchcraft and Wizardry");
        UIManager.put("defaultFont", new Font("Arial", Font.PLAIN, 16));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        admin = new SuccessButton("Admin");
        teacher = new SuccessButton("Teacher");
        student = new SuccessButton("Student");
        btnPanel = new MyPanel();
        setLayout(gb);

        btnPanel.add(admin);
        btnPanel.add(teacher);
        btnPanel.add(student);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\Resources\\schoolLogo.png").getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
        logo = new MyLabel();
        logo.setIcon(imageIcon);

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage admlgin = new LoginPage("Admin", mc);
                dispose();
                admlgin.setVisible(true);
            }
        });
        teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage tlgn = new LoginPage("Teacher", mc);
                dispose();
                tlgn.setVisible(true);
            }
        });
        student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage slgn = new LoginPage("Student", mc);
                dispose();
                slgn.setVisible(true);
            }
        });

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.ipady=80;
        add(logo, gbc);

        gbc.gridy++;
        add(btnPanel, gbc);
        getContentPane().requestFocusInWindow();
    }
}
