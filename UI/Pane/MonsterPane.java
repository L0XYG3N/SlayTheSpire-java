package UI.Pane;

import javax.swing.*;
import java.awt.*;

import Game.*;
import Game.Field.endState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MonsterPane extends JLayeredPane{
    public static final int WIDTH = 160;
    public static final int HEIGHT = 260;
    public static final int barHeight = 15;
    public static final int TOOLTIP = 260;
    public final int arrayIndex;

    private BaseObject monster;
    
    private JLabel tooltipLabel;  // 툴팁을 표시할 JLabel 추가
    private static final int TOOLTIP_DELAY = 500;  // 툴팁이 나타나기까지의 지연 시간 (밀리초)

    private JLabel name;
    private JLabel healthBar;
    private JLabel currentHealthBar;
    private JLabel healthText;
    private JLabel shield;
    private Field field = Field.getInstance();

    private JLabel monsterImage;	//	몬스터 이미지 불러오기 위한 Jlabel 추가 - 승훈
    
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
        
        tooltipLabel = new JLabel();
        tooltipLabel.setBounds(0, 0, WIDTH, TOOLTIP);
        tooltipLabel.setFont(new Font("Arial", Font.PLAIN, 8));
        tooltipLabel.setForeground(Color.BLACK);
        tooltipLabel.setBackground(Color.WHITE);
        tooltipLabel.setOpaque(false);
        add(tooltipLabel);
        
        
        //몬스터 이름
        name = new JLabel(monster.getName(),SwingConstants.CENTER);
        name.setBounds(0,0,WIDTH,barHeight);
        name.setBackground(Color.black);
        name.setOpaque(true);
        name.setForeground(Color.white);
        add(name);

        // 몬스터 이미지 추가 - 승훈
        monsterImage = new JLabel(new ImageIcon(monster.getImagePath()));
        monsterImage.setBounds(0, barHeight, WIDTH, HEIGHT - ( barHeight * 2));
        add(monsterImage);

        // 빨간색 테두리 추가 - 승훈
        monsterImage.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        

        
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

        // 마우스 이벤트 추가
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스를 가져다 대었을 때 툴팁을 패널 내부에 표시
                showTooltip();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 패인을 떠났을 때 툴팁을 감춤
                hideTooltip();
            }
        });
    }

    private void showTooltip() {
        String tooltipText = ((Monster) monster).getIntentionText();
        tooltipText = "<html><body style='width: " + (WIDTH - 40) + "px;'>" + tooltipText + "</body></html>";
        tooltipLabel.setText(tooltipText);
        Font koreanFont = new Font("맑은 고딕", Font.PLAIN, 15);
        tooltipLabel.setFont(koreanFont);
        tooltipLabel.setOpaque(true);
        tooltipLabel.setBackground(Color.WHITE);
        setLayer(tooltipLabel, JLayeredPane.PALETTE_LAYER);
    }

    private void hideTooltip() {
        tooltipLabel.setText("");  // 마우스를 떠난 경우에 툴팁을 비움
        tooltipLabel.setOpaque(false);  // 불투명에서 투명으로 변경
        setLayer(tooltipLabel, DEFAULT_LAYER);  // 툴팁을 기본 레이어로 변경하여 숨김
        // 마우스를 떠난 경우에 추가로 수행해야 할 동작이 있다면 여기에 구현
    }

    public void updateLabel() {
        // 체력 텍스트, 체력바, 방어막 등 label 업데이트 함수
        int health = monster.getHealth();
        if(health<=0) {
            Container parent = getParent();
            parent.remove(this);
            BattlePane.monsters[arrayIndex] = null;
            Field.getInstance().enemies[arrayIndex] = null;
            parent.repaint();

            if(field.isBattleEnd()) {
                field.endBattle(endState.BATTLEWIN);
                System.out.println("going floor " + Game.getInstance().gameMap.currentFloor);
            }
            return;
        }
        int maxHealth = monster.getMaxHealth();
        int healthBarSize = (int)(((double)health / maxHealth) * WIDTH);
        currentHealthBar.setBounds(0, HEIGHT-barHeight, healthBarSize, barHeight);
        healthText.setText(health + " / " + maxHealth);

        int shieldAmount = monster.getShield();
        shield.setText(Integer.toString(shieldAmount));
        
        // 몬스터 이미지 업데이트 추가 - 승훈
        monsterImage.setIcon(new ImageIcon(monster.getImagePath()));
        setLayer(monsterImage, -1);  // 이미지를 가장 뒤로 보내어 다른 컴포넌트들이 위에 나타날 수 있도록 함 - 승훈
    }

    public void splitupdateLabel() {
        int health = monster.getHealth();
         if(health<=0) {
             Container parent = getParent();
             parent.remove(this);
             BattlePane.monsters[arrayIndex] = null;
             Field.getInstance().enemies[arrayIndex] = null;
             parent.repaint();
             return;
         }
         int maxHealth = monster.getMaxHealth();
         int healthBarSize = (int)(((double)health / maxHealth) * WIDTH);
         currentHealthBar.setBounds(0, HEIGHT-barHeight, healthBarSize, barHeight);
         healthText.setText(health + " / " + maxHealth);

         int shieldAmount = monster.getShield();
         shield.setText(Integer.toString(shieldAmount));
         
         // 몬스터 이미지 업데이트 추가 - 승훈
         monsterImage.setIcon(new ImageIcon(monster.getImagePath()));
         setLayer(monsterImage, -1);
     }

    public Monster getMonster() {
        if(monster != null) return (Monster)monster;
        return null;
    }

    public void highlight() {
        // 배경색 대신 외곽선 변경
        monsterImage.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
    }

    public void deHighlight() {
        // 기존의 빨간 테두리로 변경
        monsterImage.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    }
}
