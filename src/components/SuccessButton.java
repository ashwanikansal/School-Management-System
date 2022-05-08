package components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccessButton extends JButton{

    public SuccessButton(String str){
        super(str);
        setPreferredSize(new Dimension(110,35));
        setBackground(new MyColors().black);
        setFont(new Font("Arial", Font.BOLD, 15));
        setForeground(new MyColors().lsecondary);
        setContentAreaFilled(false);
        setOpaque(true);
        setBorder(BorderFactory.createRaisedBevelBorder());

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(getModel().isPressed()){
                    setBackground(new MyColors().secondary);
                }
                else{
                    setBackground(new MyColors().black);
                }
            }
        });
    }
}
