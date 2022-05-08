package components;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class BaseForm extends SecondaryFrame{

    protected MyLabel desc, gender, fname, mname, lname, dob, sclass, phone;
    protected MyTextField tfname, tmname, tlname;
    protected JRadioButton male, female;
    protected MyFormattedField ftdob, ftphone;
    protected JComboBox <String>cbclass;
    protected SuccessButton next;
    protected GridBagLayout gb;
    protected GridBagConstraints gbc;
    protected JTabbedPane tp;
    protected String[] classes = {"Nursery","LKG","UKG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};

    public BaseForm(String str) {
        super(str);

        desc = new MyLabel("Fill in all the details below ");
        desc.setBorder(BorderFactory.createEmptyBorder(10,20,20,0));

        next = new SuccessButton("Next");
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        UIManager.put("TabbedPane.contentOpaque", false);
        tp = new JTabbedPane();


        //----------basic details----------------

        MyPanel details = new MyPanel();
        details.setLayout(gb);

        fname = new MyLabel("First Name");
        tfname = new MyTextField(20);

        mname = new MyLabel("Middle Name");
        tmname = new MyTextField(20);

        lname = new MyLabel("Last Name");
        tlname = new MyTextField(20);

        gender = new MyLabel("Gender");
        male = new JRadioButton("Male", true);
        male.setOpaque(false);
        female = new JRadioButton("Female");
        female.setOpaque(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(male); bg.add(female);
        MyPanel gpanel = new MyPanel();
        gpanel.add(male);
        gpanel.add(female);

        dob = new MyLabel("Date of Birth");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        ftdob = new MyFormattedField(df);
        ftdob.setColumns(20);
        ftdob.setValue(0);

        sclass = new MyLabel("Class");
        cbclass = new JComboBox<>(classes);

        phone = new MyLabel("Phone No.");
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        NumberFormatter nft = new NumberFormatter(nf);
        nft.setAllowsInvalid(false);
        ftphone = new MyFormattedField(nft);
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

        //------------action handling-------------

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tp.setSelectedIndex(1);
            }
        });

        tp.addTab("Basic Details", details);
        tp.setBackgroundAt(0,new MyColors().lsecondary);

        add(desc, BorderLayout.NORTH);
        add(tp, BorderLayout.CENTER);
    }
}
