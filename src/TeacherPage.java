import components.BaseFrame;
import components.*;
import components.SchoolHeader;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class TeacherPage extends BaseFrame {
    BoldLabel name, id, sclass, subject, designation;
    SimpleLabel sname, sid, ssclass, ssubject, sdesignation;
    SuccessButton signout;
    ArrayList<String> rs;
    public TeacherPage(MyClient mc, ArrayList<String> rs) {
        super("Teacher - "+rs.get(1));
        this.rs = rs;
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);

        if(rs.size()==0){
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
        }
        else {
            MyPanel details = new MyPanel();
            details.add(getDetails());
            details.setLayout(fl);

            signout = new SuccessButton("Sign Out");
            MyPanel sb = new MyPanel();
            sb.add(signout);

            //---------event handling-----------
            signout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LoginPage lp = new LoginPage("Student", mc);
                    dispose();
                    lp.setVisible(true);
                }
            });

            add(new SchoolHeader(), BorderLayout.NORTH);
            add(details, BorderLayout.CENTER);
            add(sb, BorderLayout.AFTER_LAST_LINE);
        }
    }

    MyPanel getDetails(){

        MyPanel pan = new MyPanel();
        pan.setLayout(new GridLayout(7,2));
        pan.setPreferredSize(new Dimension(500, 300));

        name = new BoldLabel("Name : ");
        id = new BoldLabel("ID : ");
        sclass = new BoldLabel("Class : ");
        subject = new BoldLabel("Subject : ");
        designation = new BoldLabel("Designation : ");

        sname = new SimpleLabel(rs.get(1)+" "+rs.get(2)+" "+rs.get(3));
        sid = new SimpleLabel(rs.get(0));
        ssclass = new SimpleLabel(rs.get(4));
        ssubject = new SimpleLabel(rs.get(5));
        sdesignation = new SimpleLabel(rs.get(6));

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