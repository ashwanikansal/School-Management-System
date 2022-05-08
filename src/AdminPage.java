import components.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends BaseFrame {



    MyLabel wel, desc;
    SuccessButton taddbtn, tsearchbtn, saddbtn, ssearchbtn, signoutbtn;
    MyPanel wpanel,tpanel,spanel,fpanel;

    AdminPage(MyClient mc){
        super("Admin");

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        wel=new MyLabel("Welcome Admin,");
        desc = new MyLabel("goto the following tabs to access teacher and student data.");
        desc.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        desc.setFont(new Font("", Font.PLAIN, 12));
        wpanel = new MyPanel();
        wpanel.setLayout(new BoxLayout(wpanel, BoxLayout.Y_AXIS));

        tpanel = new MyPanel();
        tpanel.setLayout(gb);
        TitledBorder ttb = BorderFactory.createTitledBorder(new LineBorder(new MyColors().black,2),"Teacher");
        ttb.setTitleJustification(TitledBorder.CENTER);
        tpanel.setBorder(ttb);

        taddbtn = new SuccessButton("Add Teacher");
        tsearchbtn = new SuccessButton("Search Teacher");
        tsearchbtn.setPreferredSize(new Dimension(140,35));

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

        spanel = new MyPanel();
        spanel.setLayout(gb);
        TitledBorder tb = BorderFactory.createTitledBorder(new LineBorder(new MyColors().black,2),"Student");
        tb.setTitleJustification(TitledBorder.CENTER);
        spanel.setBorder(tb);

        saddbtn = new SuccessButton("Add Student");
        ssearchbtn = new SuccessButton("Search Student");
        ssearchbtn.setPreferredSize(new Dimension(140,35));

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
        fpanel = new MyPanel();
        fpanel.setLayout(new GridLayout(1,2));

        fpanel.add(tpanel);
        fpanel.add(spanel);

        wpanel.add(wel);
        wpanel.add(desc);
        wpanel.add(fpanel);

        signoutbtn = new SuccessButton("Sign Out");
        MyPanel sb = new MyPanel();
        sb.add(signoutbtn);

        //-------------event handling--------------

        taddbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacherForm f = new AddTeacherForm(mc);
                f.setVisible(true);
            }
        });

        tsearchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchST f = new SearchST("Teacher", mc);
                f.setVisible(true);
            }
        });

        saddbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentForm f = new AddStudentForm(mc);
                f.setVisible(true);
            }
        });

        ssearchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchST f = new SearchST("Student", mc);
                f.setVisible(true);
            }
        });

        signoutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lp = new LoginPage("Admin", mc);
                dispose();
            }
        });

        BorderLayout bl = new BorderLayout(20,20);
        setLayout(bl);

        add(new SchoolHeader(), BorderLayout.NORTH);
        add(wpanel, BorderLayout.CENTER);
        add(sb, BorderLayout.AFTER_LAST_LINE);
        getContentPane().requestFocusInWindow();
    }
}
