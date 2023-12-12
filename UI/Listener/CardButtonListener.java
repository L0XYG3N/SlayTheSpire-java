package UI.Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Game.Player;
import UI.RewardCardButton;
import UI.Pane.CardPane;

public class CardButtonListener implements ActionListener{
    CardPane card;
    JButton parent;
    RewardCardButton [] buttons;
    public CardButtonListener(CardPane c, JButton parent) {
        this.parent = parent;
        card = c;
    }

    public void setButtonArray(RewardCardButton [] b) {
        buttons = b;
    }

    public void setCard(CardPane c) {
        card = c;
    }

    public void actionPerformed(ActionEvent e) {
        Player.getInstance().addCard(card.card);
        parent.setEnabled(false);

        for(int i = 0; i < 3;i++) {
            buttons[i].setEnabled(false);
            buttons[i].remove(buttons[i].cardPane);
            buttons[i].setBackground(Color.DARK_GRAY);
        }
    }
}
