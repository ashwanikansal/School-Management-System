package components;

import javax.swing.*;
import java.awt.*;

public class SchoolLabel extends JLabel {

    public SchoolLabel() {
        super("St. Mary's Convent School", JLabel.CENTER);
        Font f = new Font("Serif", 1, 20);
        setFont(f);
    }
}
