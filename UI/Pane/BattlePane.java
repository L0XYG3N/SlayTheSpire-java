package UI.Pane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.*;

import Game.Card;
import Game.CardGetter;
import Game.Field;
import Game.Monster;
import Game.Player;
import UI.MainFrame;

public class BattlePane extends JLayeredPane{
    public JLabel manaPanel;
    public JButton endTurnButton;
    private Player player = Player.getInstance();
    public static MonsterPane[] monsters = new MonsterPane[5];

    public BattlePane() {
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
        add(endTurnButton,JLayeredPane.MODAL_LAYER);

        //마나 패널

        manaPanel = new JLabel(player.getMana() + "/" + player.getMaxMana(),SwingConstants.CENTER);
        manaPanel.setFont(new Font("Cooper Black",Font.PLAIN,23));
        manaPanel.setFocusable(false);
        manaPanel.setBounds(35,430,75,75);
        manaPanel.setOpaque(true);
        manaPanel.setBackground(Color.ORANGE);

        add(manaPanel,JLayeredPane.MODAL_LAYER);


        //카드 삽입
        updateCardPane();
        

        for(int i = 0; i < 5;i++) {
            Monster monster;
            monster = Field.getInstance().enemies[i];
            if(monster != null) {
                monsters[i] = new MonsterPane(475+i*(MonsterPane.WIDTH + 10),180, monster, i);
                add(monsters[i],JLayeredPane.MODAL_LAYER);
            }
        }

        PlayerPane playerPane = new PlayerPane();
        add(playerPane, JLayeredPane.MODAL_LAYER);


        add(new CardDeckPane(), JLayeredPane.MODAL_LAYER);
        add(new CardDiscardPane(), JLayeredPane.MODAL_LAYER);
    }

    public void updateCardPane() {
        int cardCount = player.cards.field.size();
        cardCount = Math.min(6,cardCount);
        for(int i = 0; i < cardCount;i++) {
            int cardID = player.cards.field.get(i).getCardID();
            add(new CardPane(150 + i * (CardPane.WIDTH),720-CardPane.HEIGHT,CardGetter.GetCardById(cardID)),JLayeredPane.MODAL_LAYER);
        }
    }
}
