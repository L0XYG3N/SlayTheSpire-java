package UI.Pane;

import javax.swing.*;
import java.awt.*;

public class CardDiscardPane extends JLayeredPane{
    // 무덤 덱에 들어간 카드 보여주는 JLayeredPane
    public static final int width = 160;
    public static final int height = 210;

    public CardDiscardPane() {
        setBounds(1255,635,75,75);
        setOpaque(true);
        setBackground(Color.CYAN);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
    }
}
