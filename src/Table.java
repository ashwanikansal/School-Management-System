import components.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalCheckBoxIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Table extends SecondaryFrame {
    String who;
    public Table(String str, ArrayList<String> rs, MyClient mc) {
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
            add(getItemBox(false,mc,"ID", "Name", "Class", "Date of Birth","Subject", "Designation"), gbc);
        else
            add(getItemBox(false, mc,"ID", "Name", "Class", "Date of Birth"), gbc);

        for(int i=0; i<rs.size(); i++) {
            gbc.gridy++;
            if(who.equalsIgnoreCase("Teacher")) {
                add(getItemBox(true,mc, rs.get(i++), rs.get(i++) + " " + (rs.get(i++)==null ? "" : rs.get(i - 1)) + " " + (rs.get(i++)==null ? "" : rs.get(i - 1)), rs.get(i++), rs.get(i++), rs.get(i++), rs.get(i)), gbc);
            }
            else {
                add(getItemBox(true,mc, String.valueOf(rs.get(i++)), rs.get(i++) + " " + (rs.get(i++)==null ? "" : rs.get(i - 1)) + " " + (rs.get(i++)==null ? "" : rs.get(i - 1)), rs.get(i++), rs.get(i)), gbc);
            }
        }
        setExtendedState(Frame.MAXIMIZED_BOTH);
        getContentPane().requestFocusInWindow();
    }

    protected MyPanel getItemBox(Boolean addbtns, MyClient mc, String...args){
        MyPanel item = new MyPanel();
        item.setPreferredSize(new Dimension(1400, 50));
        item.setMinimumSize(new Dimension(1400, 50));


        GridLayout gl = new GridLayout(1,4,5,2);
        item.setLayout(gl);

        MyLabel iid, iname, iclass, idob, isub, idesg;

        iid = new MyLabel(args[0]);
        iname = new MyLabel(args[1]);
        iclass = new MyLabel(args[2]);
        idob = new MyLabel(args[3]);

        SuccessButton delete = new SuccessButton("Delete");

        item.add(iid);
        item.add(iname);
        item.add(iclass);
        item.add(idob);

        if(who.equalsIgnoreCase("Teacher")) {
            isub = new MyLabel(args[4]);
            idesg = new MyLabel(args[5]);
            item.add(isub);
            item.add(idesg);
        }

        if(addbtns) {
            item.add(delete);
            item.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,2,0, new MyColors().primary),BorderFactory.createEmptyBorder(0,0,10,0)));
        }

        else{
            item.add(new MyLabel());
            item.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,3,0, new MyColors().black),BorderFactory.createEmptyBorder(0,0,10,0)));
        }

        //--------event handling----------

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=JOptionPane.showConfirmDialog(null, "Are you sure?","Delete", JOptionPane.YES_NO_OPTION);
                String query;
                if(i==0){
                    if(who.equalsIgnoreCase("Teacher")) {
                        query = "delete from teachers where teacher_id = " + args[0];
                        if(mc.deleteTeacher(query)){
                            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Error in deleting!");
                        }
                    }
                    else{
                        query = "delete from students where student_id = " + args[0];
                        if(mc.deleteStudent(query)) JOptionPane.showMessageDialog(null, "Deleted Successfully!");
                        else JOptionPane.showMessageDialog(null, "Error in deleting!");
                    }
                }
            }
        });

        return item;
    }
}
