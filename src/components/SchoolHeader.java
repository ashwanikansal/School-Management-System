package components;

import javax.swing.*;
import java.awt.*;

public class SchoolHeader extends JPanel {
    public SchoolHeader(){
        super();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(gb);
        setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLACK));

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.insets.top=40;
        gbc.insets.bottom=20;
        add(new SchoolLabel(), gbc);
    }
}
