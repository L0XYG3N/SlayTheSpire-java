package UI;

import UI.Pane.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Game.BaseObject;
import Game.CardGetter;
import Game.Field;
import Game.Player;

import java.awt.*;

public class GUI{
    
    private Player player = Player.getInstance();


    
    public JLabel manaPanel;
    public MainFrame frame;
    public JButton endTurnButton;

    public CardDeckPane drawPane;
    public CardDiscardPane discardPane;
    //public CardExhaustedPane exhaustedPane;

    private TitledBorder drawBorder;
    private TitledBorder discardBorder;


    public GUI() {
        frame = new MainFrame();

        //턴 종료 버튼
        endTurnButton = new JButton("턴 종료");
        endTurnButton.setFont(new Font("Inter", Font.PLAIN, 24));
        endTurnButton.setFocusable(false);
        endTurnButton.setBounds(1150,460,150,40);
        endTurnButton.setBackground(Color.lightGray);
        frame.addComponent(endTurnButton,JLayeredPane.MODAL_LAYER);

        //마나 패널

        manaPanel = new JLabel(player.getMana() + "/" + player.getMaxMana(),SwingConstants.CENTER);
        manaPanel.setFont(new Font("Cooper Black",Font.PLAIN,23));
        manaPanel.setFocusable(false);
        manaPanel.setBounds(35,430,75,75);
        manaPanel.setOpaque(true);
        manaPanel.setBackground(Color.ORANGE);

        frame.addComponent(manaPanel,JLayeredPane.MODAL_LAYER);


        //카드 테스트
        for(int i = 0; i < 6;i++) {
            frame.addComponent(new CardPane(150 + i * (CardPane.CARD_WIDTH),720-CardPane.CARD_HEIGHT,CardGetter.GetCardById(-1)),JLayeredPane.MODAL_LAYER);
        }
        PlayerPane p = new PlayerPane();
        for(int i = 0; i < 5;i++) {
            BaseObject moster;
            
            frame.addComponent(new EnemyPane(475+i*(EnemyPane.width + 10),180, Field.getInstance().enemies[i]),JLayeredPane.MODAL_LAYER);
        }

        frame.addComponent(p, JLayeredPane.MODAL_LAYER);
        frame.addComponent(new CardDeckPane(), JLayeredPane.MODAL_LAYER);
        frame.addComponent(new CardDiscardPane(), JLayeredPane.MODAL_LAYER);
    }


}
