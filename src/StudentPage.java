import components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentPage extends BaseFrame {
    BoldLabel name, id, sclass, dob, fees;
    SimpleLabel sname, sid, ssclass, sfees, sdob;
    SuccessButton signout;
    ArrayList<String> rs;
    public StudentPage(MyClient mc, ArrayList<String> rs) {
        super("Student - "+rs.get(1));
        this.rs = rs;
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);

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

    MyPanel getDetails(){

        MyPanel pan = new MyPanel();
        pan.setLayout(new GridLayout(7,2));
        pan.setPreferredSize(new Dimension(500, 300));

        name = new BoldLabel("Name : ");
        id = new BoldLabel("ID : ");
        sclass = new BoldLabel("Class : ");
        dob = new BoldLabel("Date of Birth : ");
        fees = new BoldLabel("Fees status : ");

        sname = new SimpleLabel(rs.get(1)+" "+rs.get(2)+" "+rs.get(3));
        sid = new SimpleLabel(rs.get(0));
        ssclass = new SimpleLabel(rs.get(4));
        sdob = new SimpleLabel(rs.get(5));
        sfees = new SimpleLabel(rs.get(6));

        pan.add(name);
        pan.add(sname);
        pan.add(id);
        pan.add(sid);
        pan.add(sclass);
        pan.add(ssclass);
        pan.add(dob);
        pan.add(sdob);
        pan.add(fees);
        pan.add(sfees);

        return pan;
    }
}

class BoldLabel extends JLabel {
    BoldLabel(String str){
        super(str);
        Font ft = new Font("Dialog", Font.BOLD, 16);
        setFont(ft);
    }
}

class SimpleLabel extends JLabel {
    SimpleLabel(String str){
        super(str);
        Font ft = new Font("Dialog", Font.PLAIN, 16);
        setFont(ft);
    }
}

class AddressArea extends JTextArea{
    AddressArea(String str){
        super(str,5,20);
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false);
        setFont(new Font("Dialog", Font.PLAIN, 16));
        setEditable(false);
    }
}