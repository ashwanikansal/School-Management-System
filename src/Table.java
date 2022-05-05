import components.SecondaryFrame;
import components.SuccessButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Table extends SecondaryFrame {
    String who;
    public Table(String str, ResultSet rs, Connection con) throws SQLException {
        super(str+" Data");
        who=str;
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new FlowLayout());

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=5;
        gbc.insets.bottom=5;
        if(who.equalsIgnoreCase("Teacher"))
            add(getItemBox(false,con,"ID", "Name", "Class", "Date of Birth","Subject", "Designation"), gbc);
        else
            add(getItemBox(false,con, "ID", "Name", "Class", "Date of Birth"), gbc);

        while(rs.next()) {
            gbc.gridy++;
            if(who.equalsIgnoreCase("Teacher"))
                add(getItemBox(true,con,  String.valueOf(rs.getInt("teacher_id")),
                    rs.getString("first_name")+" "+
                            (rs.getString("middle_name")==null?"":rs.getString("middle_name"))+" "+
                            (rs.getString("last_name")==null?"":rs.getString("last_name")),
                    rs.getString("class"),rs.getString("dob"),
                    rs.getString("subject"), rs.getString("designation")), gbc);
            else
                add(getItemBox(true, con, String.valueOf(rs.getInt("student_id")),
                        rs.getString("first_name")+" "+
                                (rs.getString("middle_name")==null?"":rs.getString("middle_name"))+" "+
                                (rs.getString("last_name")==null?"":rs.getString("last_name")),
                        rs.getString("class"),rs.getString("dob")),gbc);
        }
        setMinimumSize(new Dimension(900, 500));
    }

    protected JPanel getItemBox(Boolean addbtns,Connection con, String...args){
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

        String tablename = "students", uid="student_id";

        SuccessButton delete = new SuccessButton("Delete");

        item.add(iid);
        item.add(iname);
        item.add(iclass);
        item.add(idob);

        if(who.equalsIgnoreCase("Teacher")) {
            tablename = "teachers";
            uid = "teacher_id";
            isub = new JLabel(args[4]);
            idesg = new JLabel(args[5]);
            item.add(isub);
            item.add(idesg);
        }

        if(addbtns)
            item.add(delete);

        else{
            item.add(new JLabel());
            item.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.gray));
        }
        //--------event handling----------

        String finalTablename = tablename;
        String finalUid = uid;
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=JOptionPane.showConfirmDialog(null, "Are you sure?","Delete", JOptionPane.YES_NO_OPTION);
                if(i==0){
                    try {
                        Statement st = con.createStatement();
                        st.executeUpdate("delete from "+ finalTablename +" where "+ finalUid +" = "+args[0]);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        return item;
    }
}
