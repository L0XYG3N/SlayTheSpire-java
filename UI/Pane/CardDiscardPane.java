package UI.Pane;

import javax.swing.*;

import UI.Listener.CardDiscardPaneListener;

import java.awt.*;

public class CardDiscardPane extends JLayeredPane{
    // 무덤 덱에 들어간 카드 보여주는 JLayeredPane

    public CardDiscardPane() {
        setBounds(1255,635,75,75);
        setOpaque(true);
        setBackground(Color.CYAN);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        this.addMouseListener(new CardDiscardPaneListener(this));
    }
}
