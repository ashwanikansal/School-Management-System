package components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SchoolLabel extends JLabel {

    public SchoolLabel() {
        super("Hogwarts School of Witchcraft and Wizardry", JLabel.CENTER);

        Font customFont = null;
        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\Resources\\MagicSchoolOne.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        setForeground(new MyColors().white);
        setFont(customFont);
    }
}
