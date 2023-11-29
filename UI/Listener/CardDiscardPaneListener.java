package UI.Listener;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


import Game.Player;
import UI.Pane.BattlePane;
import UI.Pane.CardDiscardPane;
import UI.Pane.CardPane;
import java.awt.event.ActionEvent;

public class CardDiscardPaneListener extends MouseAdapter{
    private CardDiscardPane pane;
    private JLayeredPane showCard;
    private ArrayList<CardPane> cards;
    JButton closeButton;
    boolean expanded;
    JLabel cardCount;

    public CardDiscardPaneListener(CardDiscardPane pane) {
        this.pane = pane;
        showCard = new JLayeredPane();
        showCard.setBounds(0,0,1250,630);
        cards = new ArrayList<CardPane>();
        closeButton = new JButton("닫기");
        closeButton.setBounds(1150,0,100,20);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }

    public void mousePressed(MouseEvent e) {
        if(!expanded) {
            pane.setBounds(50,50,1250,670);
            pane.add(showCard);
            BattlePane.getInstance().setLayer(pane,2000);
            pane.add(closeButton);
            showCards();
            expanded = true;
        }
    }

    private void close() {
        for(int i = 0; i < cards.size();i++) {
            showCard.remove(cards.get(i));
        }

        pane.remove(showCard);
        pane.remove(closeButton);
        cards.clear();
        pane.setBounds(1255,635,75,75);
        pane.repaint();
        BattlePane.getInstance().setLayer(pane,150);
        expanded = false;
    }

    private void showCards() {
        Player p = Player.getInstance();
        for(int i = 0,j=0; i < p.cards.discard.size(); i++) {
            if(i % 7 == 0 && i != 0) j++;

            CardPane c = new CardPane(
                (i % 7) * (CardPane.WIDTH + 10) + 10,
                j * (CardPane.HEIGHT + 10) + 10,
                p.cards.discard.get(i),
                0 ,
                false
            );

            cards.add(c);
            showCard.add(c);
            //showCard.setLayer(c,5);
        }
    }
}
