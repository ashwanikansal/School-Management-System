import components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentForm extends BaseForm {
    MyLabel desc, pgender, pfname, pmname, plname, address;
    MyTextField fname, mname, lname, tpfname, tpmname, tplname;
    JRadioButton pmale, pfemale;
    JTextArea taddress;
    MyFormattedField tdate, tphone;
    SuccessButton addstudent, back;
    String[] classes;
    public AddStudentForm(MyClient mc){
        super("Add Student");
        fname = super.tfname;
        mname = super.tmname;
        lname = super.tlname;
        tdate = super.ftdob;
        tphone = super.ftphone;
        classes = super.classes;

        GridBagLayout gb = super.gb;
        GridBagConstraints gbc = super.gbc;
        JTabbedPane tp = super.tp;

        back = new SuccessButton("Back");
        addstudent = new SuccessButton("Add");

        //------------personal details of student---------------

        MyPanel pdetails = new MyPanel();
        pdetails.setLayout(gb);

        pfname = new MyLabel("Parent First Name");
        tpfname = new MyTextField(20);

        pmname = new MyLabel("Parent Middle Name");
        tpmname = new MyTextField(20);

        plname = new MyLabel("Parent Last Name");
        tplname = new MyTextField(20);

        pgender = new MyLabel("Parent Gender");
        pmale = new JRadioButton("Male", true);
        pmale.setOpaque(false);
        pfemale = new JRadioButton("Female");
        pfemale.setOpaque(false);
        ButtonGroup pbg = new ButtonGroup();
        pbg.add(pmale); pbg.add(pfemale);
        MyPanel pgpanel = new MyPanel();
        pgpanel.add(pmale);
        pgpanel.add(pfemale);

        address = new MyLabel("Address");
        taddress = new JTextArea(5,20);
        taddress.setBackground(new MyColors().white);
        taddress.setForeground(new MyColors().black);
        taddress.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        taddress.setFont(new Font("Arial", Font.PLAIN, 15));
        taddress.setMargin(new Insets(10,10,10,10));

        gbc.gridx = 1;
        gbc.gridy = 1;
        pdetails.add(pfname, gbc);

        gbc.gridy++;
        pdetails.add(pmname, gbc);

        gbc.gridy++;
        pdetails.add(plname, gbc);

        gbc.gridy++;
        pdetails.add(pgender, gbc);

        gbc.gridy++;
        pdetails.add(address, gbc);

        gbc.gridy++;
        gbc.insets.top = 15;
        pdetails.add(back, gbc);

        gbc.gridx++;
        pdetails.add(addstudent, gbc);

        gbc.gridy--;
        gbc.insets.top = 0;
        pdetails.add(taddress, gbc);

        gbc.gridy--;
        pdetails.add(pgpanel, gbc);

        gbc.gridy--;
        pdetails.add(tplname, gbc);

        gbc.gridy--;
        pdetails.add(tpmname, gbc);

        gbc.gridy--;
        pdetails.add(tpfname, gbc);

        tp.addTab("Family Details", pdetails);

        //-----------event handling-------------------

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp.setSelectedIndex(0);
            }
        });

        addstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = fname.getText();
                String middlename = mname.getText();
                String lastname = lname.getText();
                String gender = (male.isSelected()) ? "Male" : "Female";
                String gclass = classes[cbclass.getSelectedIndex()];
                String date = tdate.getText();
                String address = taddress.getText();
                String phone = tphone.getText();
                String pfirstname = tpfname.getText();
                String pmiddlename = tpmname.getText();
                String plastname = tplname.getText();
                String pgender = (pmale.isSelected())?"Male":"Female";
                int fees = getFees(gclass);

                int id = Math.abs((int)System.currentTimeMillis()/1000);
                String pswd = firstname+date.substring(0,date.indexOf('-'));
                String q = "insert into students(first_name,middle_name,last_name,password,class,dob,student_id,gender,address,phone,pfirst_name,pmiddle_name, plast_name,pgender,fees) " +
                        "values('"+firstname+"','"+middlename+"','"+lastname+"','"+pswd+"','"+gclass+"','"+date+"',"+id+",'"+gender+"','"+address+"','"+phone+"','"+pfirstname+"','"+pmiddlename+"','"+plastname+"','"+pgender+"',"+fees+")";

                if(mc.addTeacher(q)) JOptionPane.showMessageDialog(tp, "Data added successfully!");
                else JOptionPane.showMessageDialog(tp, "Error in adding data!!");
                dispose();
            }
        });

    }

    int getFees(String cls){
        return switch (cls) {
            case "Nursery" -> 1000;
            case "LKG" -> 2000;
            case "UKG" -> 3000;
            case "1st" -> 4000;
            case "2nd" -> 5000;
            case "3rd" -> 5000;
            case "4th" -> 5000;
            case "5th" -> 6000;
            case "6th" -> 6000;
            case "7th" -> 7000;
            case "8th" -> 7000;
            case "9th" -> 7000;
            case "10th" -> 8000;
            default -> 0;
        };
    }
}
