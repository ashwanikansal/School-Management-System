import components.BaseFrame;
import components.SchoolHeader;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherPage extends BaseFrame {

    BoldLabel name, id, sclass, subject, designation;
    SimpleLabel sname, sid, ssclass, ssubject, sdesignation;
    SuccessButton signout;
    public TeacherPage() {
        super("Teacher - Rakesh");

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);

        JPanel details = new JPanel();
        details.add(getDetails());
        details.setLayout(fl);

        signout = new SuccessButton("Sign Out");
        JPanel sb = new JPanel();
        sb.add(signout);

        //---------event handling-----------
        signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lp = new LoginPage("Student");
                dispose();
                lp.setVisible(true);
            }
        });

        add(new SchoolHeader(), BorderLayout.NORTH);
        add(details, BorderLayout.CENTER);
        add(sb, BorderLayout.AFTER_LAST_LINE);
    }

    JPanel getDetails(){

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(7,2));
        pan.setPreferredSize(new Dimension(500, 300));

        name = new BoldLabel("Name : ");
        id = new BoldLabel("ID : ");
        sclass = new BoldLabel("Class : ");
        subject = new BoldLabel("Subject : ");
        designation = new BoldLabel("Report : ");

        sname = new SimpleLabel("Rakesh");
        sid = new SimpleLabel("0000000001");
        ssclass = new SimpleLabel("7 th");
        ssubject = new SimpleLabel("Maths");
        sdesignation = new SimpleLabel("pgt");

        pan.add(name);
        pan.add(sname);
        pan.add(id);
        pan.add(sid);
        pan.add(sclass);
        pan.add(ssclass);
        pan.add(subject);
        pan.add(ssubject);
        pan.add(designation);
        pan.add(sdesignation);

        return pan;
    }
}