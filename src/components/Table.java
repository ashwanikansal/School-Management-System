package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table extends SecondaryFrame{

    String who;
    public Table(String str, ResultSet rs) throws SQLException {
        super(str+" Data");
        who=str;
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new FlowLayout());


        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=5;
        gbc.insets.bottom=5;
        add(getItemBox(false,"ID", "Name", "Class", "Date of Birth","Subject", "Designation"), gbc);

        while(rs.next()) {
            gbc.gridy++;
            add(getItemBox(true,  String.valueOf(rs.getInt("id")),
                    rs.getString("fname")+" "+rs.getString("mname")+" "+rs.getString("lname"),
                    rs.getString("class"),rs.getString("dob"),
                    rs.getString("subj"), rs.getString("desg")), gbc);
        }

        setMinimumSize(new Dimension(900, 500));

    }

    protected JPanel getItemBox(Boolean addbtns, String...args){
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
                JOptionPane.showConfirmDialog(null, "Are you sure ?");
            }
        });

        return item;
    }
}
