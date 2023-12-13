package UI.Pane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Game.Player;
import UI.GUI;
import UI.MainFrame;
import UI.GUI.ScreenState;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestPane extends JLayeredPane{
    private static RestPane instance = new RestPane();
    public static RestPane getInstance() {return instance;}

    private static Player player;

    private RestPane() {
        
        player = Player.getInstance();

    	// 배경 이미지 추가 - 승훈
    	ImageIcon backgroundImage = new ImageIcon("resource/rest.png"); // 실제 이미지 경로로 대체하세요
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

        JButton rest = new JButton("휴식")
            , reward = new JButton("아이템 획득");


        rest.setBounds(300,334,200,100);
        reward.setBounds(866,334,200,100);
        add(rest);
        add(reward);

        rest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //체력 20퍼센트 회복 후 다시 지도 표시
                player.addHealth((int)(player.maxHealth * 0.3));
                GUI.changeScreen(ScreenState.MAP);
            }
        });

        reward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RewardPane rewardPane = RewardPane.getInstance();
                rewardPane.setGoldReward(1);
                rewardPane.setRandomCardReward(1);
                GUI.changeScreen(ScreenState.REWARD);
            }
        });


    }
}
