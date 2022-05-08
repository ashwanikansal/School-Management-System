package components;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.Format;

public class MyFormattedField extends JFormattedTextField {
    public MyFormattedField(NumberFormatter f){
        super(f);
        setBackground(new MyColors().white);
        setForeground(new MyColors().black);
        setBorder(BorderFactory.createLoweredSoftBevelBorder());
        setFont(new Font("Arial", Font.PLAIN, 15));
        setMargin(new Insets(10,10,10,10));
    }
    public MyFormattedField(DateFormat f){
        super(f);
        setBackground(new MyColors().white);
        setForeground(new MyColors().black);
        setBorder(BorderFactory.createLoweredSoftBevelBorder());
        setFont(new Font("Arial", Font.PLAIN, 15));
        setMargin(new Insets(10,10,10,10));
    }
}
