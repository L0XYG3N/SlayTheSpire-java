package UI.Pane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import UI.GUI;
import UI.MainFrame;
import UI.GUI.ScreenState;

public class MainMenuPane extends JLayeredPane{

    JButton startGameButton;
    JButton mapButton;
    JButton rewardButton;

    public MainMenuPane() {
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

        startGameButton = new JButton("게임 시작");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.changeScreen(ScreenState.BATTLE);
            }
        });
        startGameButton.setBounds(100,100,200,50);
        add(startGameButton);

        mapButton = new JButton("지도 보기");
        mapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.changeScreen(ScreenState.MAP);
            }
        });
        mapButton.setBounds(350,100,200,50);
        add(mapButton);

        rewardButton = new JButton("보상 화면");
        rewardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.changeScreen(ScreenState.REWARD);
            }
        });
        rewardButton.setBounds(350,300,200,50);
        add(rewardButton);

    }
}
