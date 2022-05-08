package components;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(int col){
        super(col);
        setBackground(new MyColors().white);
        setForeground(new MyColors().black);
        setBorder(BorderFactory.createLoweredSoftBevelBorder());
        setFont(new Font("Arial", Font.PLAIN, 15));
        setMargin(new Insets(10,10,10,10));
    }
}
