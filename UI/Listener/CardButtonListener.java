package UI.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Game.Player;
import UI.Pane.CardPane;

public class CardButtonListener implements ActionListener{
    CardPane card;
    JButton parent;
    public CardButtonListener(CardPane c, JButton parent) {
        this.parent = parent;
        card = c;
    }

    public void setCard(CardPane c) {
        card = c;
    }

    public void actionPerformed(ActionEvent e) {
        Player.getInstance().addCard(card.card);
        //card.disable();
        parent.setEnabled(false);
        parent.remove(card);
        parent.repaint();
    }
}
