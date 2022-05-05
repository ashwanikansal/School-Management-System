import components.BaseForm;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddTeacherForm extends BaseForm {


     Connection con = Connect.dbConnect();
    JLabel designation, qualification, subject, address;
    JTextField fname, mname, lname, tqualification, tsubject, tdesignation ,tdate;
    JTextArea taddress;
    SuccessButton addteacher, back;


    public AddTeacherForm() {
        super("Add Teacher");
        fname=super.tfname;
        mname=super.tmname;
        lname=super.tlname;
        tdate=super.ftdob;



        GridBagLayout gb = super.gb;
        GridBagConstraints gbc = super.gbc;
        JTabbedPane tp = super.tp;

        addteacher = new SuccessButton("Add");
        back = new SuccessButton("Back");

        //----other details-------

        JPanel odetails = new JPanel();
        odetails.setLayout(gb);

        designation = new JLabel("Designation");
        tdesignation = new JTextField(20);

        subject = new JLabel("Subject");
        tsubject = new JTextField(20);

        qualification = new JLabel("Qualification");
        tqualification = new JTextField(20);

        address = new JLabel("Address");
        taddress = new JTextArea(5,20);

        gbc.gridx=1;
        gbc.gridy=1;
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

        //-----------event handling-------------------

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp.setSelectedIndex(0);
            }
        });

        addteacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PreparedStatement pst;
                String arr[]={"Nursery", "LKG","UKG", "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};
            String firstname= fname.getText();
            String middlename=mname.getText();
            String lastname=lname.getText();
            String gender= (male.isSelected())?"Male":"Female";
            String gclass= arr[cbclass.getSelectedIndex()];
            String designation=tdesignation.getText();
            String qualification=tqualification.getText();
            String subject=tsubject.getText();
            String address=taddress.getText();
            String date=tdate.getText();

            int i= (int) System.currentTimeMillis();
            int j=12345;
            String q= "insert into teachers(first_name,middle_name,last_name,class,designation,subject,dob,teacher_id,student_id,gender) values(?,?,?,?,?,?,?,?,?,?)";
                try {
                    pst= con.prepareStatement(q);
                    pst.setString(1,firstname);
                    pst.setString(2,middlename);
                    pst.setString(3,lastname);
                    pst.setString(4,gclass);
                    pst.setString(5,designation);
                    pst.setString(6,subject);
                    pst.setString(7,date);
                    pst.setInt(8, i++);
                    pst.setInt(9, j++);
                    pst.setString(10, gender);

                    pst.executeUpdate();
                    fname.setText("");
                    mname.setText("");
                    lname.setText("");
                    tdesignation.setText("");
                    tqualification.setText("");
                    tsubject.setText("");
                    taddress.setText("");
                    tdate.setText("");
                   // con.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(tp, "Data added successfully");
                dispose();
            }
        });

    }
}

