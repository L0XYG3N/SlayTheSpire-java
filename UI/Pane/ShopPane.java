package UI.Pane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Game.Card;
import Game.CardGetter;
import Game.Player;
import Game.Card.CardType;
import UI.GUI;
import UI.MainFrame;
import UI.GUI.ScreenState;
import UI.Listener.ShopCardListener;

public class ShopPane extends JLayeredPane {

    private static ShopPane instance = new ShopPane();
    public static ShopPane getInstance() {return instance;}


    private Player player;
    private JLabel goldLabel;
    private JButton skipButton;
    
    ArrayList<CardPane> displayCard = new ArrayList<CardPane>();
    ArrayList<JLabel> displayLabel = new ArrayList<JLabel>(); 

    private ShopPane() {
       
       // 배경 이미지 추가 - 승훈
       ImageIcon backgroundImage = new ImageIcon("resource/shop.png"); // 실제 이미지 경로로 대체하세요
       JLabel backgroundLabel = new JLabel(backgroundImage);
       backgroundLabel.setBounds(0, 0, MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
       add(backgroundLabel);
       setLayer(backgroundLabel, -10);
       
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
        player = Player.getInstance();
        goldLabel = new JLabel();
        skipButton = new JButton();

        goldLabel.setText(Integer.toString(player.getGold()));
        goldLabel.setFont(new Font("gothic",Font.PLAIN,40));
        goldLabel.setForeground(Color.black);
        goldLabel.setBounds(MainFrame.SCREEN_WIDTH/2-50,50,100,30);
        goldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        goldLabel.setVerticalAlignment(SwingConstants.CENTER);
        goldLabel.setOpaque(true);
        goldLabel.setBackground(Color.DARK_GRAY);
        goldLabel.setForeground(Color.white);
        add(goldLabel);

        skipButton.setText("넘어가기");
        skipButton.setBounds(MainFrame.SCREEN_WIDTH/3*2,500,100,40);
        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.changeScreen(ScreenState.MAP);
            }
        });
        add(skipButton);
        
        displayCards();

    }

    public void updateGoldLabel() {
        goldLabel.setText(Integer.toString(player.getGold()));
    }

    public void displayCards() {
        //3개의 공격 카드, 3개의 스킬 카드, 3개의 파워 카드가 랜덤으로 뜸
        for(int i = 0; i < displayCard.size(); i++) {
            remove(displayCard.get(i));
            remove(displayLabel.get(i));
        }
        displayCard.clear();
        displayLabel.clear();


        Card randomCard;
        for(int i = 0;i<9;i++) {
            if(i / 3 == 0)
                randomCard = CardGetter.getRandomCard(CardType.ATTACK);
            else if(i / 3 == 1)
                randomCard = CardGetter.getRandomCard(CardType.POWER);
            else
                randomCard = CardGetter.getRandomCard(CardType.SKILL);

            int x = (i % 5) * (CardPane.WIDTH + 70) + 100;
            int y = (CardPane.HEIGHT + 40) * (i / 5)+ 100;
            CardPane pane = new CardPane(x, y, randomCard, i, false);
            int price = new Random().nextInt(60)+35;
            // 수정된 코드
            
            
            JLabel priceLabel = new JLabel(Integer.toString(price), SwingConstants.CENTER);
            priceLabel.setBounds(x, y + CardPane.HEIGHT, CardPane.WIDTH, 20);
            priceLabel.setFont(new Font("gothic", Font.PLAIN, 16)); // 폰트 크기 조정
            priceLabel.setForeground(Color.WHITE); // 글자색을 흰색으로 변경
            priceLabel.setOpaque(true); // 배경색을 사용하도록 설정
            priceLabel.setBackground(new Color(0, 0, 0, 180)); // 검정색 배경, 불투명도 100


            displayCard.add(pane);
            displayLabel.add(priceLabel);
            
            pane.addMouseListener(new ShopCardListener(price,i));
            add(priceLabel);
            add(pane);
        }
    }

    public void buyItem(int i) {
        Card card = displayCard.get(i).card;
        player.cards.cardList.add(card);
        //player.addCard(card);
        updateGoldLabel();  // 골드 라벨 업데이트
        remove(displayCard.get(i));
        remove(displayLabel.get(i));
        repaint();
    }

}