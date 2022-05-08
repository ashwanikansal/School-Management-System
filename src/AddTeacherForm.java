import components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
public class AddTeacherForm extends BaseForm {
    Connection con = Connect.dbConnect();
    MyLabel designation, qualification, subject, address;
    MyTextField fname, mname, lname, tqualification, tsubject, tdesignation;
    JTextArea taddress;
    MyFormattedField tdate, tphone;
    SuccessButton addteacher, back;
    String[] classes;
    public AddTeacherForm(MyClient mc) {
        super("Add Teacher");
        fname = super.tfname;
        mname = super.tmname;
        lname = super.tlname;
        tdate = super.ftdob;
        tphone = super.ftphone;
        classes = super.classes;

        GridBagLayout gb = super.gb;
        GridBagConstraints gbc = super.gbc;
        JTabbedPane tp = super.tp;

        addteacher = new SuccessButton("Add");
        back = new SuccessButton("Back");

        // ----other details-------

        MyPanel odetails = new MyPanel();
        odetails.setLayout(gb);

        designation = new MyLabel("Designation");
        tdesignation = new MyTextField(20);

        subject = new MyLabel("Subject");
        tsubject = new MyTextField(20);

        qualification = new MyLabel("Qualification");
        tqualification = new MyTextField(20);

        address = new MyLabel("Address");
        taddress = new JTextArea(5, 20);
        taddress.setBackground(new MyColors().white);
        taddress.setForeground(new MyColors().black);
        taddress.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        taddress.setFont(new Font("Arial", Font.PLAIN, 15));
        taddress.setMargin(new Insets(10,10,10,10));

        gbc.gridx = 1;
        gbc.gridy = 1;
        odetails.add(designation, gbc);

        gbc.gridy++;
        odetails.add(subject, gbc);

        gbc.gridy++;
        odetails.add(qualification, gbc);

        gbc.gridy++;
        odetails.add(address, gbc);

        gbc.gridy++;
        gbc.insets.top = 15;
        odetails.add(back, gbc);

        gbc.gridx++;
        odetails.add(addteacher, gbc);

        gbc.gridy--;
        gbc.insets.top = 0;
        odetails.add(taddress, gbc);

        gbc.gridy--;
        odetails.add(tqualification, gbc);

        gbc.gridy--;
        odetails.add(tsubject, gbc);

        gbc.gridy--;
        odetails.add(tdesignation, gbc);

        tp.addTab("Other Details", odetails);

        // -----------event handling-------------------

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp.setSelectedIndex(0);
            }
        });

        addteacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstname = fname.getText();
                String middlename = mname.getText();
                String lastname = lname.getText();
                String gender = (male.isSelected()) ? "Male" : "Female";
                String gclass = classes[cbclass.getSelectedIndex()];
                String designation = tdesignation.getText();
                String subject = tsubject.getText();
                String date = tdate.getText();
                String qualification = tqualification.getText();
                String address = taddress.getText();
                String phone = tphone.getText();

                int id = Math.abs((int)System.currentTimeMillis()/1000);
                String pswd = firstname+date.substring(0,date.indexOf('-'));
                String q = "insert into teachers(first_name,middle_name,last_name,password,class,designation,subject,dob,teacher_id,gender,qualification,address,phone) " +
                        "values('"+firstname+"','"+middlename+"','"+lastname+"','"+pswd+"','"+gclass+"','"+designation+"','"+subject+"','"+date+"',"+id+",'"+gender+"','"+qualification+"','"+address+"','"+phone+"')";
                if(mc.addTeacher(q)) JOptionPane.showMessageDialog(tp, "Data added successfully!");
                else JOptionPane.showMessageDialog(tp, "Error in adding data!!");
                dispose();
            }
        });

    }
}
