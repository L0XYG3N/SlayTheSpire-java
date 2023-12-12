package UI;

import javax.swing.JButton;

import Game.Card;
import UI.Listener.CardButtonListener;
import UI.Pane.CardPane;

public class RewardCardButton extends JButton{
    CardPane cardPane;
    CardButtonListener listener;
    public RewardCardButton() {
        setLayout(null);
        setSize(150,220);
        //setLocation(x,y);
        listener = new CardButtonListener(cardPane, this);
        addActionListener(listener);
        
        //setCard(CardGetter.GetCardById(1));
    }

    public void setCard(Card c) {
        if(cardPane != null)
            remove(cardPane);
        
        cardPane = new CardPane(0,0, c, 0, false);
        listener.setCard(cardPane);
        add(cardPane);
        revalidate();
    }

    public void disable() {
        //cardPane.disable();
    }

}
