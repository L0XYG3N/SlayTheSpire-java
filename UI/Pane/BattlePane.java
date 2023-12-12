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

import javax.sound.sampled.*;    // 턴종료 사운드를 위해 추가 - 승훈
import javax.swing.ImageIcon;    // 턴 종료 이미지를 위해 추가 - 승
import java.io.File;    // 파일 경로를 위해 추가 - 승훈

public class BattlePane extends JLayeredPane {
    private static BattlePane instance = new BattlePane();

    private Player player = Player.getInstance();
    private Game game = Game.getInstance();

    public static MonsterPane[] monsters = new MonsterPane[5];

    private JLabel manaPanel;
    private JButton endTurnButton;
    private Clip turnEndSound; // 턴 종료 사운드 추가 - 승훈
    private PlayerPane playerPane;
    private CardDeckPane cardDeckPane = new CardDeckPane();
    private CardDiscardPane discardPane = new CardDiscardPane();
    private ArrayList<CardPane> drawnCards;

    public static BattlePane getInstance() {
        return instance;
    }

    private BattlePane() {
        // Pane 초기 세팅
        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        setLocation(0, 0);
        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
        setLayout(null);
        setEnabled(true);
        setVisible(true);
        setOpaque(true);

    	// 배경 이미지 추가 - 승훈
    	ImageIcon backgroundImage = new ImageIcon("resource/background1.png"); // 실제 이미지 경로로 대체하세요
    	JLabel backgroundLabel = new JLabel(backgroundImage);
    	backgroundLabel.setBounds(0, 0, MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
    	add(backgroundLabel);
    	setLayer(backgroundLabel, -10);
        
        // 턴 종료 버튼
        endTurnButton = new JButton("턴 종료");
        endTurnButton.setFont(new Font("Inter", Font.PLAIN, 24));
        endTurnButton.setFocusable(false);
        endTurnButton.setBounds(1150, 460, 150, 40);
        endTurnButton.setBackground(Color.lightGray);

        // 턴 종료 버튼 이미지 추가 - 승훈
        ImageIcon endTurnIcon = new ImageIcon("path/to/endTurnImage.png"); // 이미지 경로로 대체하세요
        endTurnButton.setIcon(endTurnIcon);

        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.turnEnd();
                updateCardPane();
                playerPane.updateLabel();

                // 턴 종료 사운드 재생
                playTurnEndSound();
            }
        });
        add(endTurnButton, 150);
        // 턴 종료 사운드 초기화
        initTurnEndSound();

        // 마나 패널
        manaPanel = new JLabel(player.getMana() + "/" + player.getMaxMana(), SwingConstants.CENTER);
        manaPanel.setFont(new Font("Cooper Black", Font.PLAIN, 23));
        manaPanel.setFocusable(false);
        manaPanel.setBounds(35, 430, 75, 75);
        manaPanel.setOpaque(true);
        manaPanel.setBackground(Color.ORANGE);

        add(manaPanel, 150);

        // 카드
        drawnCards = new ArrayList<CardPane>();

        // 몬스터
        // drawMonsters();

        // 플레이어
        playerPane = PlayerPane.getInstance();
        add(playerPane, 150);

        add(discardPane, 151);
        add(cardDeckPane, 150);

    }

    public void initBattlePane() {
        updateCardPane();
        drawMonsters();
    }

    public void updateCardPane() {
        clearCardPane();

        int cardCount = player.cards.field.size();

        for (int i = 0; i < cardCount; i++) {
            int cardID = player.cards.field.get(i).getCardID();
            int x;
            if (cardCount % 2 == 0) {
                x = MainFrame.SCREEN_WIDTH / 2 - (cardCount / 2 - i) * CardPane.WIDTH;
            } else {
                x = MainFrame.SCREEN_WIDTH / 2 - CardPane.WIDTH / 2 - (cardCount / 2 - i) * CardPane.WIDTH;
            }

            CardPane card = new CardPane(x, 720 - CardPane.HEIGHT, CardGetter.GetCardById(cardID), i, true);
            add(card);
            setLayer(card, 400);
            drawnCards.add(card);
        }

        setLayer(playerPane, 100);

        updateManaPanel();
    }

    public void clearCardPane() {
        for (CardPane c : drawnCards) {
            remove(c);
        }
        drawnCards.clear();
    }

    public void drawMonsters() {
        for (int i = 0; i < 5; i++) {
            if(monsters[i] != null) {
                remove(monsters[i]);
                monsters[i] = null;
            }

            Monster monster;
            monster = Field.getInstance().enemies[i];
            if (monster != null) {
                monsters[i] = new MonsterPane(475 + i * (MonsterPane.WIDTH + 10), 180, monster, i);
                add(monsters[i], 150);
            }
        }
    }

    public void updateManaPanel() {
        manaPanel.setText(player.getMana() + "/" + player.getMaxMana());
    }

    private void initTurnEndSound() {
        try {
            // 사운드 파일 경로에 따라 수정
            File soundFile = new File("resource/EndTurn.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            turnEndSound = AudioSystem.getClip();
            turnEndSound.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playTurnEndSound() {
        if (turnEndSound != null) {
            turnEndSound.stop(); // 이미 재생 중인 경우 중지
            turnEndSound.setFramePosition(0); // 재생 위치를 처음으로 되돌림
            turnEndSound.start(); // 사운드 재생
        }
    }

    public void splitSlime() {
        Field.getInstance().split(1);
         for (int i = 0; i < 5; i++) {
             Monster monster;
             monster = Field.getInstance().enemies[i];
             if (monster != null) {
                 monsters[i] = new MonsterPane(475 + i * (MonsterPane.WIDTH + 10), 180, monster, i);
                 add(monsters[i], 150);
             }
         }
     }
     
     public void AcidSlime() {
        Field.getInstance().split(2);
         for (int i = 0; i < 5; i++) {
             Monster monster;
             monster = Field.getInstance().enemies[i];
             if (monster != null) {
                 monsters[i] = new MonsterPane(475 + i * (MonsterPane.WIDTH + 10), 180, monster, i);
                 add(monsters[i], 150);
             }
         }
     }
}
