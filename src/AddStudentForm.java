import components.BaseFrame;
import components.SuccessButton;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class AddStudentForm extends BaseFrame {
    JLabel desc, gender, fname, mname, lname, dob, pgender, pfname, pmname, plname, address, sclass, phone;
    JTextField tfname, tmname, tlname, tpfname, tpmname, tplname;
    JRadioButton male, female, pmale, pfemale;
    JFormattedTextField ftdob, ftphone;
    JComboBox <String>cbclass;
    JTextArea taddress;
    SuccessButton next, addstudent, back;
    public AddStudentForm(){
        super("Add Student");

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        desc = new JLabel("Fill in all the details below ");

        JTabbedPane tp = new JTabbedPane();
        next = new SuccessButton("Next");
        back = new SuccessButton("Back");
        addstudent = new SuccessButton("Add");

        //----------basic details of student----------------

        JPanel details = new JPanel();
        details.setLayout(gb);

        fname = new JLabel("First Name");
        tfname = new JTextField(20);

        mname = new JLabel("Middle Name");
        tmname = new JTextField(20);

        lname = new JLabel("Last Name");
        tlname = new JTextField(20);

        gender = new JLabel("Gender");
        male = new JRadioButton("Male", true);
        female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male); bg.add(female);
        JPanel gpanel = new JPanel();
        gpanel.add(male);
        gpanel.add(female);

        dob = new JLabel("Date of Birth");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        ftdob = new JFormattedTextField(df);
        ftdob.setColumns(20);
        ftdob.setValue(0);

        sclass = new JLabel("Class");
        String[] classes = {"Nursery","LKG","UKG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};
        cbclass = new JComboBox<>(classes);

        phone = new JLabel("Phone No.");
        NumberFormat nf = NumberFormat.getInstance();
        NumberFormatter nft = new NumberFormatter(nf);
        nft.setAllowsInvalid(false);
        ftphone = new JFormattedTextField(nft);
        ftphone.setColumns(20);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top = 5;
        gbc.insets.bottom=5;
        gbc.insets.right=5;
        gbc.insets.left=5;
        details.add(fname, gbc);

        gbc.gridy++;
        details.add(mname, gbc);

        gbc.gridy++;
        details.add(lname, gbc);

        gbc.gridy++;
        details.add(gender, gbc);

        gbc.gridy++;
        details.add(dob, gbc);

        gbc.gridy++;
        details.add(sclass, gbc);

        gbc.gridy++;
        details.add(phone, gbc);

        gbc.gridy++;
        gbc.gridx++;
        gbc.insets.top = 15;
        details.add(next, gbc);

        gbc.insets.top=0;
        gbc.gridy--;
        details.add(ftphone, gbc);

        gbc.gridy--;
        details.add(cbclass, gbc);

        gbc.gridy--;
        details.add(ftdob, gbc);

        gbc.gridy--;
        details.add(gpanel, gbc);

        gbc.gridy--;
        details.add(tlname, gbc);

        gbc.gridy--;
        details.add(tmname, gbc);

        gbc.gridy--;
        details.add(tfname, gbc);

        //-----------event handling--------------------

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp.setSelectedIndex(1);
            }
        });

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
                AdminPage ap = new AdminPage();
                dispose();
            }
        });

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

        tp.addTab("Basic Details", details);
        tp.addTab("Family Details", pdetails);

        add(desc, BorderLayout.NORTH);
        add(tp, BorderLayout.CENTER);

        setLocationRelativeTo(desc);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

//    public static void main(String[] args) {
//        AddStudentForm f = new AddStudentForm();
//        f.setVisible(true);
//    }
}
