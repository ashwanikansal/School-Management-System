import components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchST extends SecondaryFrame {
    protected MyLabel fname, mname, lname, sclass, id, dob, desg, sub;
    protected MyTextField tfname, tmname, tlname, tid, tdesg, tsub;
    protected MyFormattedField ftdob;
    protected JComboBox <String> cbclass;
    protected SuccessButton searchbtn;
    protected GridBagLayout gb;
    protected GridBagConstraints gbc;

    protected MyClient mc;
    String who;

    public SearchST(String str, MyClient mc) {
        super("Search "+str);
        who = str;
        this.mc = mc;
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();

        add(getSearchPanel(), BorderLayout.CENTER);
        setMinimumSize(new Dimension(900, 500));
    }

    protected MyPanel getSearchPanel(){

        MyPanel searchbox = new MyPanel();
        searchbox.setLayout(gb);
        searchbox.setBorder(BorderFactory.createTitledBorder(null,"Search By",2,0));

        fname = new MyLabel("First Name");
        tfname = new MyTextField(15);

        mname = new MyLabel("Middle Name");
        tmname = new MyTextField(15);

        lname = new MyLabel("Last Name");
        tlname = new MyTextField(15);

        id = new MyLabel("Id");
        tid = new MyTextField(15);

        desg = new MyLabel("Designation");
        tdesg = new MyTextField(15);

        sub = new MyLabel("Subject");
        tsub = new MyTextField(15);

        dob = new MyLabel("Date of Birth");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ftdob = new MyFormattedField(df);
        ftdob.setColumns(15);
        ftdob.setValue(0);

        sclass = new MyLabel("Class");
        String[] classes = {"Nursery","LKG","UKG","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};
        cbclass = new JComboBox<>(classes);

        searchbtn = new SuccessButton("Search");

        //---------------event handling---------------
        searchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                ArrayList<String> res;

                String tname="students", idname="student_id";
                if(who.equalsIgnoreCase("Teacher")) {
                    tname = "teachers";
                    idname = "teacher_id";
                }

                String query = "select * from "+tname+" where " +
                        "class = '"+iclass+"'"+
                        ((lf==0)?"":" and first_name = "+"'"+ifname+"'")+
                        (im==0?"": " and middle_name = "+"'"+imname+"'")+
                        (il==0?"": " and last_name = "+"'"+ilname+"'")+
                        (lid==0?"": " and "+idname+" = "+"'"+iid+"'")+
                        ((idob.equals("1970-01-01") || idob.length()==0)?"": " and dob = "+"'"+idob+"'");

                //--------for searching Teacher--------

                if(who.equalsIgnoreCase("Teacher")){

                    String idesg = tdesg.getText();
                    String isub = tsub.getText();

                    int ide = idesg.length();
                    int isb = isub.length();

                    query = query + ((ide==0)?"":" and designation = "+"'"+idesg+"'") + ((isb==0)?"":" and subject = "+"'"+isub+"'");
                    try {
                        mc.searchTeacher(query);
                        res = mc.rs;
                        Table t=new Table(who, res, mc);
                        t.setVisible(true);
                    }catch (Exception es){
                        System.out.println(es.getMessage());
                    }
                }
                //--------------for searching student------------
                else{
                    try {
                        System.out.println(query);
                        mc.searchStudent(query);
                        res = mc.rs;
                        Table t = new Table(who, res, mc);
                        t.setVisible(true);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
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
}
