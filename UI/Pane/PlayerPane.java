package UI.Pane;
import javax.swing.*;
import java.awt.*;

import Game.Player;

public class PlayerPane extends JLayeredPane{
    public Player player;
    public static final int width = 180;
    public static final int height = 250;

    public PlayerPane() {
        setBounds(90,160,width,height);
        setOpaque(true);
        setBackground(Color.GREEN);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        
    }

}
