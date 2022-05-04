import components.BaseFrame;
import components.SchoolHeader;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPage extends BaseFrame {

    BoldLabel name, id, sclass, address, parents, report, fees;
    SimpleLabel sname, sid, ssclass, sparents, sreport, sfees;
    AddressArea saddress;
    SuccessButton signout;
    public StudentPage(String... args) {
        super("Student - Rakesh");

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
        address = new BoldLabel("Address : ");
        parents = new BoldLabel("Parent's Name : ");
        report = new BoldLabel("Report : ");
        fees = new BoldLabel("Fees status : ");

        sname = new SimpleLabel("Rakesh");
        sid = new SimpleLabel("0000000001");
        ssclass = new SimpleLabel("7 th");
        saddress = new AddressArea("Patel Nagar, New York, abarakadabra bhootiya haweli");
        saddress.setLineWrap(true);
        sparents = new SimpleLabel("Harish Chandra");
        sreport = new SimpleLabel("Fail");
        sfees = new SimpleLabel("Paid");

        pan.add(name);
        pan.add(sname);
        pan.add(id);
        pan.add(sid);
        pan.add(sclass);
        pan.add(ssclass);
        pan.add(address);
        pan.add(saddress);
        pan.add(parents);
        pan.add(sparents);
        pan.add(report);
        pan.add(sreport);
        pan.add(fees);
        pan.add(sfees);

        return pan;
    }

    public static void main(String[] args) {
        StudentPage f = new StudentPage();
        f.setVisible(true);
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