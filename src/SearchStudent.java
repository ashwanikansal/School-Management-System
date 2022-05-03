import components.SecondaryFrame;
import components.SuccessButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SearchStudent extends SecondaryFrame {

    protected JLabel fname, mname, lname, sclass, id, dob, desg, sub;
    protected JTextField tfname, tmname, tlname, tid, tdesg, tsub;
    protected JFormattedTextField ftdob;
    protected JComboBox <String> cbclass;
    protected SuccessButton searchbtn;
    protected GridBagLayout gb;
    protected GridBagConstraints gbc;
    String who;

    public SearchStudent(String str) {
        super("Search "+str);
        who = str;
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();

        add(getSearchPanel(), BorderLayout.NORTH);
        add(new JScrollPane(getItemsPanel()), BorderLayout.CENTER);

        setMinimumSize(new Dimension(900, 500));
    }

    protected JPanel getSearchPanel(){
        JPanel searchbox = new JPanel();
        searchbox.setLayout(gb);
        searchbox.setBorder(BorderFactory.createTitledBorder(null,"Search By",2,0));

        fname = new JLabel("First Name");
        tfname = new JTextField(15);

        mname = new JLabel("Middle Name");
        tmname = new JTextField(15);

        lname = new JLabel("Last Name");
        tlname = new JTextField(15);

        id = new JLabel("Id");
        tid = new JTextField(15);

        desg = new JLabel("Designation");
        tdesg = new JTextField(15);

        sub = new JLabel("Subject");
        tsub = new JTextField(15);

        dob = new JLabel("Date of Birth");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        ftdob = new JFormattedTextField(df);
        ftdob.setColumns(15);
        ftdob.setValue(0);

        sclass = new JLabel("Class");
        String[] classes = {"Nursery","LKG","UKG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};
        cbclass = new JComboBox<>(classes);

        searchbtn = new SuccessButton("Search");

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=15;
        gbc.insets.right=3;
        gbc.insets.left=3;
        searchbox.add(fname, gbc);

        gbc.gridx++;
        searchbox.add(mname, gbc);

        gbc.gridx++;
        searchbox.add(lname, gbc);

        gbc.gridy++;
        gbc.insets.top=5;
        searchbox.add(tlname, gbc);

        gbc.gridx--;
        searchbox.add(tmname, gbc);

        gbc.gridx--;
        searchbox.add(tfname, gbc);

        gbc.gridy++;
        searchbox.add(id, gbc);

        gbc.gridx++;
        searchbox.add(sclass, gbc);

        gbc.gridx++;
        searchbox.add(dob, gbc);

        gbc.gridy++;
        searchbox.add(ftdob, gbc);

        gbc.gridx--;
        searchbox.add(cbclass, gbc);

        gbc.gridx--;
        searchbox.add(tid, gbc);

        if(who.equalsIgnoreCase("teacher")) {
            gbc.gridy++;
            searchbox.add(desg, gbc);

            gbc.gridx++;
            gbc.gridx++;
            searchbox.add(sub, gbc);

            gbc.gridy++;
            gbc.insets.bottom = 15;
            searchbox.add(tsub, gbc);

            gbc.gridx--;
            gbc.insets.top = 15;
            searchbox.add(searchbtn, gbc);

            gbc.gridx--;
            gbc.insets.top = 0;
            searchbox.add(tdesg, gbc);

        }

        else{
            gbc.gridx++;
            gbc.gridy++;
            gbc.insets.top = 15;
            gbc.insets.bottom = 15;
            searchbox.add(searchbtn, gbc);
        }

        return searchbox;
    }

    protected JPanel getItemsPanel(){
        JPanel items = new JPanel();
        items.setLayout(gb);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=5;
        gbc.insets.bottom=5;
        items.add(getItemBox(false,"ID", "Name", "Class", "Date of Birth", "Subject", "Designation"), gbc);

        for(int i=0; i<10; i++) {
            gbc.gridy++;
            items.add(getItemBox(true,"132445789", "Rakesh Kumar Sharma", "10th", "24-10-1999", "Maths", "Principal"), gbc);
        }
        return items;
    }

    protected JPanel getItemBox( Boolean addbtns, String...args){
        JPanel item = new JPanel();
        item.setPreferredSize(new Dimension(850, 30));
        item.setMinimumSize(new Dimension(850, 30));

        GridLayout gl = new GridLayout(1,4,5,2);
        item.setLayout(gl);

        JLabel iid, iname, iclass, idob, isub, idesg;

        iid = new JLabel(args[0]);
        iname = new JLabel(args[1]);
        iclass = new JLabel(args[2]);
        idob = new JLabel(args[3]);

        SuccessButton update = new SuccessButton("Update");
        SuccessButton delete = new SuccessButton("Delete");

        item.add(iid);
        item.add(iname);
        item.add(iclass);
        item.add(idob);

        if(who.equalsIgnoreCase("Teacher")) {
            isub = new JLabel(args[4]);
            idesg = new JLabel(args[5]);
            item.add(isub);
            item.add(idesg);
        }

        if(addbtns) {
            item.add(update);
            item.add(delete);
        }
        else{
            item.add(new JLabel());
            item.add(new JLabel());
            item.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.gray));
        }
        //--------event handling----------

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(searchbtn, "Are you sure ?");
            }
        });

        return item;
    }

}
