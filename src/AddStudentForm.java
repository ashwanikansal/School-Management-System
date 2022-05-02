import components.BaseForm;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentForm extends BaseForm {
    JLabel desc, pgender, pfname, pmname, plname, address;
    JTextField tpfname, tpmname, tplname;
    JRadioButton pmale, pfemale;
    JTextArea taddress;
    SuccessButton addstudent, back;
    public AddStudentForm(){
        super("Add Student");

        GridBagLayout gb = super.gb;
        GridBagConstraints gbc = super.gbc;
        JTabbedPane tp = super.tp;

        back = new SuccessButton("Back");
        addstudent = new SuccessButton("Add");

        //------------personal details of student---------------

        JPanel pdetails = new JPanel();
        pdetails.setLayout(gb);

        pfname = new JLabel("Parent First Name");
        tpfname = new JTextField(20);

        pmname = new JLabel("Parent Middle Name");
        tpmname = new JTextField(20);

        plname = new JLabel("Parent Last Name");
        tplname = new JTextField(20);

        pgender = new JLabel("Parent Gender");
        pmale = new JRadioButton("Male", true);
        pfemale = new JRadioButton("Female");
        ButtonGroup pbg = new ButtonGroup();
        pbg.add(pmale); pbg.add(pfemale);
        JPanel pgpanel = new JPanel();
        pgpanel.add(pmale);
        pgpanel.add(pfemale);

        address = new JLabel("Address");
        taddress = new JTextArea(5,20);

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
                JOptionPane.showMessageDialog(tp, "Data added successfully");
                dispose();
            }
        });

    }
}
