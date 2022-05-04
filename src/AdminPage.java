import components.BaseFrame;
import components.SchoolHeader;
import components.SuccessButton;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends BaseFrame {

    JLabel wel, desc;
    SuccessButton taddbtn, tsearchbtn, saddbtn, ssearchbtn, signoutbtn;
    JPanel wpanel,tpanel,spanel,fpanel;

    AdminPage(){
        super("Admin");

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        wel=new JLabel("Welcome Admin,");
        desc = new JLabel("goto the following tabs to access teacher and student data.");
        desc.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        desc.setFont(new Font("", Font.PLAIN, 12));
        wpanel = new JPanel();
        wpanel.setLayout(new BoxLayout(wpanel, BoxLayout.Y_AXIS));

        tpanel = new JPanel();
        tpanel.setLayout(gb);
        TitledBorder ttb = BorderFactory.createTitledBorder("Teacher");
        ttb.setTitleJustification(TitledBorder.CENTER);
        tpanel.setBorder(ttb);

        taddbtn = new SuccessButton("Add Teacher");
        tsearchbtn = new SuccessButton("Search Teacher");

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=50;
        gbc.insets.bottom=10;
        tpanel.add(taddbtn, gbc);

        gbc.gridy++;
        gbc.insets.top=10;
        gbc.insets.bottom=50;
        tpanel.add(tsearchbtn, gbc);

        //-------------Student-------------

        spanel = new JPanel();
        spanel.setLayout(gb);
        TitledBorder tb = BorderFactory.createTitledBorder("Student");
        tb.setTitleJustification(TitledBorder.CENTER);
        spanel.setBorder(tb);

        saddbtn = new SuccessButton("Add Student");
        ssearchbtn = new SuccessButton("Search Student");

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=50;
        gbc.insets.bottom=10;
        spanel.add(saddbtn, gbc);

        gbc.gridy++;
        gbc.insets.top=10;
        gbc.insets.bottom=50;
        spanel.add(ssearchbtn, gbc);

        //---------Final panel-------------
        fpanel = new JPanel();
        fpanel.setLayout(new GridLayout(1,2));

        fpanel.add(tpanel);
        fpanel.add(spanel);

        wpanel.add(wel);
        wpanel.add(desc);
        wpanel.add(fpanel);

        signoutbtn = new SuccessButton("Sign Out");
        JPanel sb = new JPanel();
        sb.add(signoutbtn);

        //-------------event handling--------------

        taddbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacherForm f = new AddTeacherForm();
                f.setVisible(true);
            }
        });

        tsearchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchST f = new SearchST("Teacher");
                f.setVisible(true);
            }
        });

        saddbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentForm f = new AddStudentForm();
                f.setVisible(true);
            }
        });

        ssearchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchST f = new SearchST("Student");
                f.setVisible(true);
            }
        });

        signoutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lp = new LoginPage("Admin");
                dispose();
            }
        });

        BorderLayout bl = new BorderLayout(20,20);
        setLayout(bl);

        add(new SchoolHeader(), BorderLayout.NORTH);
        add(wpanel, BorderLayout.CENTER);
        add(sb, BorderLayout.AFTER_LAST_LINE);
    }
}
