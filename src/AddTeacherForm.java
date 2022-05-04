import components.BaseForm;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeacherForm extends BaseForm {

    JLabel designation, qualification, subject, address;
    JTextField tdesignation, tqualification, tsubject;
    JTextArea taddress;
    SuccessButton addteacher, back;

    public AddTeacherForm() {
        super("Add Teacher");
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


                JOptionPane.showMessageDialog(tp, "Data added successfully");
                dispose();
            }
        });

    }
}

