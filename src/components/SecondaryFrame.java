package components;

import javax.swing.*;
import java.awt.*;

public class SecondaryFrame extends JFrame{
    public SecondaryFrame(String str) {
        super(str);
        setMinimumSize(new Dimension(600, 500));
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new MyColors().white);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
