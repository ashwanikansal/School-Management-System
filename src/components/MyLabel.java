package components;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String...str){
        super((str.length!=0)?str[0]:"");
        setFont(new Font("Arial",Font.BOLD,15));
    }
}
