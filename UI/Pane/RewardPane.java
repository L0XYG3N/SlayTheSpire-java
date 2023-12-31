package UI.Pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Game.CardGetter;
import Game.Player;
import Game.Card.CardTarget;
import Game.Card.CardType;
import UI.GUI;
import UI.MainFrame;
import UI.RewardCardButton;
import UI.GUI.ScreenState;
import UI.Listener.CardButtonListener;

public class RewardPane extends JLayeredPane{
    JButton goldReward;
    JButton confirmButton;
    RewardCardButton [] cardButton;
    Dimension buttonSize = new Dimension(200,30);
    public int cardClicked = 0;

    CardPane[] card;

    private static RewardPane instance = new RewardPane();
    public static RewardPane getInstance() {return instance;}



    private int randInt(int a, int b) {
        return (int)(Math.random() * ((b + 1) - a)) + a;
    }

    private RewardPane() {
        Dimension size = new Dimension(MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
        setLocation(0,0);
        setSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
        setLayout(null);
        setEnabled(true);
        setVisible(true);
        setBackground(Color.GRAY);
        setOpaque(true);

        goldReward = new JButton();
        confirmButton = new JButton("확인");
        
        cardButton = new RewardCardButton[3];
        card = new CardPane[3];
        
        goldReward.setSize(buttonSize);
        confirmButton.setSize(buttonSize);
        // 배경 이미지 추가 - 승훈
    	ImageIcon backgroundImage = new ImageIcon("resource/rewardRoom.jpg"); // 실제 이미지 경로로 대체하세요
    	JLabel backgroundLabel = new JLabel(backgroundImage);
    	backgroundLabel.setBounds(0, 0, MainFrame.SCREEN_WIDTH, MainFrame.SCREEN_HEIGHT);
    	add(backgroundLabel);
    	setLayer(backgroundLabel, -10);

        for(int i = 0; i < 3;i++) {
            cardButton[i] = new RewardCardButton(cardButton);
            cardButton[i].setLocation(MainFrame.SCREEN_WIDTH/2 + (i-1) * 200 - 75,MainFrame.SCREEN_HEIGHT/5*2);
            add(cardButton[i]);
        }
        
        goldReward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int gold = Integer.parseInt(goldReward.getText().split(" ")[2]);
                //System.out.println("get gold : " + Integer.toString(gold));

                Player.getInstance().addGold(gold);

                goldReward.setEnabled(false);
            }
        });



        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.changeScreen(ScreenState.MAP);
            }
        });

        goldReward.setLocation((int)(MainFrame.SCREEN_WIDTH/2-buttonSize.getWidth()/2),MainFrame.SCREEN_HEIGHT/3);
            
        confirmButton.setLocation(MainFrame.SCREEN_WIDTH/3*2,600);

        add(goldReward);
        add(confirmButton);

        
        
        setGoldReward(1);
    }

    public void setGoldReward(int battleType) {
        String rewardString = "골드 : ";
        
        switch(battleType) {
            case 1://일반몹
                rewardString += Integer.toString(randInt(10,20));
                break;
            case 2://엘리트
                rewardString += Integer.toString(randInt(25,35));
                break;
            case 3://보스
                rewardString += Integer.toString(randInt(95,105));
                break;
        }
        goldReward.setText(rewardString);
        goldReward.setEnabled(true);
    }

    public void setRandomCardReward(int battleType) {
        
        for(int i = 0; i < 3;i++) {
            cardButton[i].setCard(CardGetter.getRandomCard(CardType.ATTACK));
            cardButton[i].setEnabled(true);
            if(battleType > 1) {
                cardButton[i].setEliteReward();
            } else cardButton[i].setNormalReward();
        }
        cardClicked = 0;
    }
}
