package UI.Pane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Game.*;
import UI.MainFrame;

public class BattlePane extends JLayeredPane{
    private Player player = Player.getInstance();
    private Game game = Game.getInstance();
    
    private ArrayList<CardPane> drawnCards;

    public static MonsterPane[] monsters = new MonsterPane[5];

    private JLabel manaPanel;
    private JButton endTurnButton;
    private PlayerPane playerPane;
    private CardDeckPane cardDeckPane = new CardDeckPane(this);

    public BattlePane() {
        // Pane 초기 세팅
        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        setLocation(0,0);
        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
        setLayout(null);
        setEnabled(true);
        setVisible(true);
        setOpaque(true);

        

    
        //턴 종료 버튼
        endTurnButton = new JButton("턴 종료");
        endTurnButton.setFont(new Font("Inter", Font.PLAIN, 24));
        endTurnButton.setFocusable(false);
        endTurnButton.setBounds(1150,460,150,40);
        endTurnButton.setBackground(Color.lightGray);
        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.turnEnd();
                updateCardPane();
                playerPane.updateLabel();
                
            }
        });
        add(endTurnButton,JLayeredPane.MODAL_LAYER);

        //마나 패널

        manaPanel = new JLabel(player.getMana() + "/" + player.getMaxMana(),SwingConstants.CENTER);
        manaPanel.setFont(new Font("Cooper Black",Font.PLAIN,23));
        manaPanel.setFocusable(false);
        manaPanel.setBounds(35,430,75,75);
        manaPanel.setOpaque(true);
        manaPanel.setBackground(Color.ORANGE);

        add(manaPanel,JLayeredPane.MODAL_LAYER);


        //카드
        drawnCards = new ArrayList<CardPane>();
        updateCardPane();
        
        //몬스터
        drawMonsters();
        
        //플레이어
        playerPane = new PlayerPane();
        add(playerPane, JLayeredPane.MODAL_LAYER);


        add(cardDeckPane, JLayeredPane.MODAL_LAYER);
        add(new CardDiscardPane(), JLayeredPane.MODAL_LAYER);
    }

    public void updateCardPane() {
        clearCardPane();

        int cardCount = player.cards.field.size();
        cardCount = Math.min(6,cardCount);
        for(int i = 0; i < cardCount;i++) {
            int cardID = player.cards.field.get(i).getCardID();
            CardPane c = new CardPane(150 + i * (CardPane.WIDTH),720-CardPane.HEIGHT,CardGetter.GetCardById(cardID), true);
            add(c,JLayeredPane.MODAL_LAYER);
            drawnCards.add(c);
        }
    }

    public void clearCardPane() {
        for(CardPane c : drawnCards) {
            remove(c);
        }
        drawnCards.clear();
    }

    public void drawMonsters() {
        for(int i = 0; i < 5;i++) {
            Monster monster;
            monster = Field.getInstance().enemies[i];
            if(monster != null) {
                monsters[i] = new MonsterPane(475+i*(MonsterPane.WIDTH + 10),180, monster, i);
                add(monsters[i],JLayeredPane.MODAL_LAYER);
            }
        }
    }
}
