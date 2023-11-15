package UI.Listener;

import java.awt.event.MouseAdapter;
import UI.Pane.CardDiscardPane;

public class CardDiscardPaneListener extends MouseAdapter{
    private CardDiscardPane pane;

    public CardDiscardPaneListener(CardDiscardPane pane) {
        this.pane = pane;
    }
}
