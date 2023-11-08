package UI.Pane;

import javax.swing.*;
import java.awt.*;

import Game.*;

public class MonsterPane extends JLayeredPane{
    public static final int WIDTH = 160;
    public static final int HEIGHT = 210;
    public static final int barHeight = 15;
    public final int arrayIndex;

    private BaseObject monster;
    
    private JLabel name;
    private JLabel healthBar;
    private JLabel currentHealthBar;
    private JLabel healthText;
    private JLabel shield;
    

    public final int x, y;

    public MonsterPane(int x, int y, Monster monster, int arrayIndex) {
        this.x = x;
        this.y = y;
        setBounds(x,y,WIDTH,HEIGHT);
        setOpaque(true);
        setBackground(Color.PINK);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        this.monster = monster;
        this.arrayIndex = arrayIndex;

        //몬스터 이름
        name = new JLabel(monster.getName(),SwingConstants.CENTER);
        name.setBounds(0,0,WIDTH,barHeight);
        name.setBackground(Color.black);
        name.setOpaque(true);
        name.setForeground(Color.white);
        add(name);

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
        
        int health = monster.getHealth();
        if(health<=0) {
            Container parent = getParent();
            parent.remove(this);
            BattlePane.monsters[arrayIndex] = null;
            parent.repaint();
            return;
        }
        int maxHealth = monster.getMaxHealth();
        int healthBarSize = (int)(((double)health / maxHealth) * WIDTH);
        currentHealthBar.setBounds(0, HEIGHT-barHeight, healthBarSize, barHeight);
        healthText.setText(health + " / " + maxHealth);

        int shieldAmount = monster.getShield();
        shield.setText(Integer.toString(shieldAmount));

    }

    public Monster getMonster() {
        if(monster != null) return (Monster)monster;
        return null;
    }

    public void highlight() {
        setBackground(Color.MAGENTA);
    }

    public void deHighlight() {
        setBackground(Color.PINK);
    }

}
