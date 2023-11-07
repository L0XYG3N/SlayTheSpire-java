package UI.Pane;

import javax.swing.*;
import java.awt.*;

import Game.*;

public class EnemyPane extends JLayeredPane{
    public static final int WIDTH = 160;
    public static final int HEIGHT = 210;
    public static final int barHeight = 15;
    private BaseObject enemy;
    private JLabel healthBar;
    private JLabel currentHealthBar;
    private JLabel healthText;
    public final int x, y;

    public EnemyPane(int x, int y, BaseObject enemy) {
        this.x = x;
        this.y = y;
        setBounds(x,y,WIDTH,HEIGHT);
        setOpaque(true);
        setBackground(Color.PINK);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        this.enemy = enemy;

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
        healthText = new JLabel("100/100",SwingConstants.CENTER);
        healthText.setFont(new Font("Arial",Font.PLAIN,15));
        healthText.setBounds(0, HEIGHT-barHeight, WIDTH, barHeight);
        healthText.setForeground(Color.white);
        add(healthText);

        setLayer(healthBar, 1);
        setLayer(currentHealthBar, 2);
        setLayer(healthText, 3);
        updateHealthLabel();
    }

    public void updateHealthLabel() {

        int health = enemy.getHealth();
        int maxHealth = enemy.getMaxHealth();
        int healthBarSize = (health / maxHealth) * WIDTH;
        currentHealthBar.setBounds(0, HEIGHT-barHeight, healthBarSize, barHeight);
        healthText.setText(health + " / " + maxHealth);
    }

    public Monster getMonster() {
        return (Monster)enemy;
    }


}
