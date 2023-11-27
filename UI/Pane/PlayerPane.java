package UI.Pane;
import javax.swing.*;
import java.awt.*;

import Game.Player;

public class PlayerPane extends JLayeredPane{

    private Player player;
    public static final int WIDTH = 180;
    public static final int HEIGHT = 250;
    public static final int barHeight = 15;
    public static final int X = 90;
    public static final int Y = 160;

    private JLabel healthBar;
    private JLabel currentHealthBar;
    private JLabel healthText;
    private JLabel shield;

    private static PlayerPane instance = new PlayerPane();
    public static PlayerPane getInstance() {
        return instance;
    }

    private PlayerPane() {
        setBounds(X,Y,WIDTH,HEIGHT);
        setOpaque(true);
        setBackground(Color.GREEN);
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

        updateLabel();
    }

    public void updateLabel() {
        System.out.println(player.getHealth());
        // 체력 텍스트, 체력바, 방어막 등 label 업데이트 함수
        int health = player.getHealth();
        health = Math.max(0,health);    // 체력이 0 미만으로 내려가지 않게 고정
        
        int maxHealth = player.getMaxHealth();
        int healthBarSize = (int)(((double)health / maxHealth) * WIDTH);
        currentHealthBar.setBounds(0, HEIGHT-barHeight, healthBarSize, barHeight);
        healthText.setText(health + " / " + maxHealth);

        int shieldAmount = player.getShield();
        shield.setText(Integer.toString(shieldAmount));

        if(health<=0) {
            //게임오버 판정
            return;
        }
    }

    public void highlight() {
        setBackground(new Color(0, 100, 0));
    }

    public void deHighlight() {
        setBackground(Color.green);
    }


}
