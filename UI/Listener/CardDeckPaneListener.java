package UI.Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;

import Game.Card;
import Game.Field;
import Game.Player;
import UI.Pane.BattlePane;
import UI.Pane.CardDeckPane;
import UI.Pane.CardPane;


public class CardDeckPaneListener extends MouseAdapter{
    private CardDeckPane pane;
    private BattlePane screen;
    private JLayeredPane showCard;
    private ArrayList<CardPane> cards;
    JButton closeButton;
    boolean expanded;

    public CardDeckPaneListener(CardDeckPane pane, BattlePane screen) {
        this.pane = pane;
        this.screen = screen;
        showCard = new JLayeredPane();
        showCard.setBounds(100,100,500,500);
        showCard.setBackground(Color.red);
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
            pane.setBounds(50,50,1250,630);
            pane.add(showCard);
            screen.setLayer(pane,300);
            pane.add(closeButton);
            showCards();
            expanded = true;
        }
    }

    private void close() {
        pane.remove(showCard);
        pane.remove(closeButton);
        cards.clear();
        pane.setBounds(20,635,75,75);
        pane.repaint();
        expanded = false;
    }

    private void showCards() {
        Player p = Player.getInstance();
        for(int i = 0,j=0; i < p.cards.deck.size(); i++) {
            if(i % 6==0) j++;
            //CardPane c = new CardPane(i * (CardPane.WIDTH+10),j*(CardPane.HEIGHT+10),p.cards.deck.get(i),false);
            //cards.add(c);
            //showCard.add(c);
            //showCard.setLayer(c,1000);
        }
    }
    
}
        