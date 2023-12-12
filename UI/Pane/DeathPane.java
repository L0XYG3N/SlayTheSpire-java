package UI.Pane;
import Game.Game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import UI.GUI;
import UI.MainFrame;
import UI.GUI.ScreenState;

/* 여기서부터 아래까지 전부 배경음악 , 배경 이미지 , 배경 버튼을 위해 추가했습니다 - 승훈 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl.Type;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/* 여기서부터 아래까지 전부 배경음악 , 배경 이미지 , 배경 버튼을 위해 추가했습니다 - 승훈 */

public class DeathPane extends JLayeredPane{

    JButton restartButton;
    JButton exitButton;
    private Image backgroundImage = (new ImageIcon("resource/DeathPane.png")).getImage();	//	배경 이미지 추가입니다. - 승훈
    
    public DeathPane() {
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

        restartButton = new JButton("다시 시작");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI.changeScreen(ScreenState.MAIN);
                Game.getInstance().resetGame();
                GUI.redrawMap();
                
            }
        });
        restartButton.setBounds(589,400,200,50);
        add(restartButton);
        
        exitButton = new JButton("게임 종료");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 게임 종료
                System.exit(0);
            }
        });
        exitButton.setBounds(589,500, 200, 50); // 적절한 위치로 변경
        add(exitButton);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, this);
     }
    
}
