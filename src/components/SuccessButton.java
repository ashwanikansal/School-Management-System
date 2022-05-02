package components;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class SuccessButton extends JButton{

    public SuccessButton(String str){
        super(str);
        setPreferredSize(new Dimension(100,30));
//        setBackground(new java.awt.Color(0, 255, 0));
        setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
//        setForeground(new java.awt.Color(255, 255, 255));
        setBorder(BorderFactory.createRaisedBevelBorder());
//        System.out.println(getBorder());
    }
}
