package UI.Pane;
import javax.swing.*;
import java.awt.*;

import Game.Player;
import UI.GUI;
import UI.GUI.ScreenState;

public class PlayerPane extends JLayeredPane{

    private Player player;
    public static final int WIDTH = 160;
    public static final int HEIGHT = 260;
    public static final int barHeight = 15;
    public static final int statusHeight = 40;
    public static final int X = 90;
    public static final int Y = 160;

    private JLabel healthBar;
    private JLabel currentHealthBar;
    private JLabel healthText;
    private JLabel shield;
    private JLabel statusBar;
    private JLabel playerImage;  // 플레이어 이미지를 표시할 JLabel 추가
    
    private static PlayerPane instance = new PlayerPane();
    public static PlayerPane getInstance() {
        return instance;
    }

    private PlayerPane() {
    	
    	// player를 먼저 초기화 - 승훈
        player = Player.getInstance();
    	
    	// 플레이어 이미지 로드 - 승훈
        ImageIcon playerIcon = new ImageIcon(player.getImagePath());

        // 플레이어 이미지를 표시할 JLabel 설정 - 승훈
        playerImage = new JLabel(playerIcon);
        playerImage.setBounds(0, statusHeight , WIDTH, HEIGHT - statusHeight);
        
        // 빨간색 테두리 추가 - 승훈
        playerImage.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        add(playerImage);
        setLayer(playerImage, -1);  // 이미지를 가장 뒤로 보내어 다른 컴포넌트들이 위에 나타날 수 있도록 함 - 승훈
    	
        setBounds(X,Y,WIDTH,HEIGHT);
        setOpaque(true);
        setBackground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT); 

        player = Player.getInstance();

        //체력바 배경
        healthBar = new JLabel();
        healthBar.setBounds(0, HEIGHT-barHeight, WIDTH, barHeight);
        healthBar.setBackground(new Color(255,0,0));
        healthBar.setOpaque(true);
        add(healthBar);

        //현재 체력 체력바
        currentHealthBar = new JLabel();
        currentHealthBar.setBounds(0, HEIGHT-barHeight, WIDTH, barHeight);
        currentHealthBar.setBackground(new Color(80,0,0));
        currentHealthBar.setOpaque(true);
        add(currentHealthBar);

        // 레이어드 패널의 배경 설정
        setOpaque(true);
        Color layeredPaneBackgroundColor = new Color(255, 255, 255, 0); // RGB 값과 알파 값(0~255) 설정
        setBackground(layeredPaneBackgroundColor);

        // 상태바
        statusBar = new JLabel("", SwingConstants.CENTER);
        Font koreanFont = new Font("궁서", Font.PLAIN, 15);
        statusBar.setFont(koreanFont);
        statusBar.setBounds(0, 0, WIDTH, statusHeight);
        statusBar.setForeground(Color.white); // 텍스트 색상 설정
        add(statusBar);
        
        //체력 텍스트
        healthText = new JLabel("",SwingConstants.CENTER);
        healthText.setFont(new Font("Arial",Font.PLAIN,15));
        healthText.setBounds(0, HEIGHT-barHeight, WIDTH, barHeight);
        healthText.setForeground(Color.white);
        add(healthText);

        //방어막 텍스트
        shield = new JLabel("",SwingConstants.CENTER);
        shield.setFont(new Font("Arial", Font.PLAIN, 20));
        shield.setBounds(0, HEIGHT-barHeight-30, 30, 30);
        shield.setBackground(new Color(120,200,255));
        shield.setOpaque(true);
        shield.setForeground(Color.BLACK);
        add(shield);

        setLayer(healthBar, 1);
        setLayer(currentHealthBar, 2);
        setLayer(healthText, 3);
        setLayer(statusBar, 4);
        
        updateLabel();
    }

    public void updateLabel() {
        int health = player.getHealth();
        health = Math.max(0, health);

        int maxHealth = player.getMaxHealth();
        int healthBarSize = (int)(((double)health / maxHealth) * WIDTH);
        currentHealthBar.setBounds(0, HEIGHT - barHeight, healthBarSize, barHeight);
        healthText.setText(health + " / " + maxHealth);

        int shieldAmount = player.getShield();
        shield.setText(Integer.toString(shieldAmount));

        // 여기서 getPlayerStatus 메서드를 이용하여 BuffStatus의 상태 정보를 가져옵니다.
        String playerStatus = player.getStatus().getPlayerStatus();
        statusBar.setText(playerStatus);

        if (health <= 0) {
            GUI.changeScreen(ScreenState.Death);
        }
    }

    public void highlight() {
        // 배경색 대신 외곽선 변경
        playerImage.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
    }

    public void deHighlight() {
        // 기존의 빨간 테두리로 변경
        playerImage.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }
}
