package UI.Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Game.Player;
import UI.RewardCardButton;
import UI.Pane.CardPane;
import UI.Pane.RewardPane;

public class CardButtonListener implements ActionListener{
    CardPane card;
    RewardCardButton parent;
    RewardCardButton [] buttons;
    boolean elite;
    public CardButtonListener(CardPane c, RewardCardButton parent) {
        this.parent = parent;
        card = c;
        elite = false;
    }

    public void setButtonArray(RewardCardButton [] b) {
        buttons = b;
    }

    public void setCard(CardPane c) {
        card = c;
    }

    public void setEliteReward() {
        elite = true;
    }

    public void setNormalReward() {
        elite = false;
    }

    public void actionPerformed(ActionEvent e) {
        
        if(elite && RewardPane.getInstance().cardClicked > 2) return;
        RewardPane.getInstance().cardClicked++;
        

        
        parent.setEnabled(false);
        parent.remove(parent.cardPane);
        parent.setBackground(Color.DARK_GRAY);

        Player.getInstance().addCard(card.card);
        parent.setBackground(Color.DARK_GRAY);
        if(elite && RewardPane.getInstance().cardClicked < 2) return;
        for(int i = 0; i < 3;i++) {
            buttons[i].setEnabled(false);
            buttons[i].remove(buttons[i].cardPane);
            buttons[i].setBackground(Color.DARK_GRAY);
        }
        
    }
}
