import components.SecondaryFrame;
import components.SuccessButton;
import components.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SearchST extends SecondaryFrame {

    protected JLabel fname, mname, lname, sclass, id, dob, desg, sub;
    protected JTextField tfname, tmname, tlname, tid, tdesg, tsub;
    protected JFormattedTextField ftdob;
    protected JComboBox <String> cbclass;
    protected SuccessButton searchbtn;
    protected GridBagLayout gb;
    protected GridBagConstraints gbc;
    String who;

    public SearchST(String str) {
        super("Search "+str);
        who = str;
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();

        add(getSearchPanel(), BorderLayout.CENTER);

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

        //---------------event handling---------------
        searchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection con = Connect.dbConnect();
                PreparedStatement pst;
                String ifname = tfname.getText();
                String imname = tmname.getText();
                String ilname = tlname.getText();
                String iid = tid.getText();
                String iclass =classes[cbclass.getSelectedIndex()];
                String idob = ftdob.getText();

                int lf = ifname.length();
                int im = imname.length();
                int il = ilname.length();
                int lid = iid.length();

                String query = "select * from teacher where " +
                        ((lf==0)?"":"fname=?")+
                        (im==0?"": " and mname = ?")+
                        (il==0?"": " and lname = ?")+
                        (lid==0?"": " and id = ?")+
                        " and class = ?"+
                        ((idob.equals("01-01-1970") || idob.length()==0)?"": " and dob = ?");

                //--------for searching Teacher--------

                if(who.equalsIgnoreCase("Teacher")){

                    String idesg = tdesg.getText();
                    String isub = tsub.getText();

                    int ide = idesg.length();
                    int isb = isub.length();

                    query = query + ((ide==0)?"":" and desg = ?")
                            + ((isb==0)?"":" and subj = ?");
                    try {
                        int i=0;
                        pst = con.prepareStatement(query);
                        if(lf!=0) pst.setString(++i,ifname);
                        if(im!=0) pst.setString(++i,imname);
                        if(il!=0) pst.setString(++i,ilname);
                        if(lid!=0) pst.setString(++i,iid);
                        pst.setString(++i,iclass);
                        if(!(idob.equals("01-01-1970") || idob.length()==0)) pst.setString(++i,idob);
                        if(ide!=0) pst.setString(++i, idesg);
                        if(isb!=0) pst.setString(++i, isub);

                        System.out.println(query);

                        ResultSet rs = pst.executeQuery();
                        Table t = new Table(who, rs);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else{

                }
            }
        });

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

    public static void main(String[] args) {
        SearchST s = new SearchST("Teacher");
        s.setVisible(true);
    }

}
