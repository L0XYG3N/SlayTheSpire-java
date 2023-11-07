package UI.Pane;
import javax.swing.*;
import java.awt.*;

import Game.Player;

public class PlayerPane extends JLayeredPane{
    public Player player;
    public static final int WIDTH = 180;
    public static final int HEIGHT = 250;

    public PlayerPane() {
        setBounds(90,160,WIDTH,HEIGHT);
        setOpaque(true);
        setBackground(Color.GREEN);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        
    }

}
