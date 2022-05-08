package components;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public BaseFrame(String str){
        super(str);
        setMinimumSize(new Dimension(800, 600));
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new MyColors().white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
