package UI.Pane;

import javax.swing.*;
import java.awt.*;

import Game.*;

public class EnemyPane extends JLayeredPane{
    public static final int width = 160;
    public static final int height = 210;
    private BaseObject enemy;

    public EnemyPane(int x, int y, BaseObject enemy) {
        setBounds(x,y,width,height);
        setOpaque(true);
        setBackground(Color.PINK);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        
    }
}
