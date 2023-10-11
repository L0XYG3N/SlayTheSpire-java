package UI;

import Game.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TempUI extends JFrame {
    public JLabel playerStatus;
    public JLabel bossStatus;
    public JLabel cardStatus;
    public JLabel fieldStatus;
    public Game game;
    public ArrayList<JButton> cardArray;
    public JPanel jp;

    public TempUI() {
        //UI 초기화 함수, 지금은 생성자에 다 때려박았지만 나중에 함수별로 정리해야함
        initialize();
        game = Game.getInstance();
        jp = new JPanel();
        cardArray = new ArrayList<JButton>();
        Container c = getContentPane();
        
        cardStatus = new JLabel();
        bossStatus = new JLabel();
        playerStatus = new JLabel();
        setLocationRelativeTo(null);

        for (int i = 0; i < game.player.cards.field.size(); i++) {
            //버튼 생성용 코드, 뜯어고쳐야함
            String cardname = "card" + Integer.toString(i);
            cardArray.add(new JButton(cardname));
            jp.add(cardArray.get(i), BorderLayout.SOUTH);
            cardArray.get(i).addActionListener(new MyActionListener());
        }
        //턴 종료 버튼, 항상 맨 아래 오른쪽에 붙어있게 하는 방법 찾아야함
        JButton turnEnd = new JButton("End Turn");


        c.setLayout(new BorderLayout());

        c.add(jp, BorderLayout.SOUTH);
        c.add(cardStatus, BorderLayout.NORTH);
        c.add(bossStatus, BorderLayout.EAST);
        c.add(playerStatus, BorderLayout.WEST);
        jp.add(turnEnd, BorderLayout.CENTER);
        turnEnd.addActionListener(new MyActionListener());
        
        updateLabel();
        setVisible(true);
    }

    private void initialize() {
        setTitle("game");
        setLayout(null);
        setSize(1366, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void updateButton() {
        // 필드에 있는 카드 갯수대로 버튼 생성하는 코드
        for (int i = 0; i < game.player.cards.field.size(); i++) {
            cardArray.clear();
        }
        for (int i = 0; i < game.player.cards.field.size(); i++) {
            String cardname = "card" + Integer.toString(i);
            cardArray.add(new JButton(cardname));
            jp.add(cardArray.get(i), BorderLayout.SOUTH);
            cardArray.get(i).addActionListener(new MyActionListener());
        }
    }

    public void updateLabel() {
        // 정보 표시하는 JLabel들 업데이트하는 함수
        String cardstatus = "";
        for (int i = 0; i < 5; i++) {
            Card card;
            try {
                card = game.player.cards.field.get(i);
            } catch (Exception e) {
                break;
            }

            int d = card.getCardID();

            cardstatus += (Integer.toString(d) + ",");
        }

        cardStatus.setText(cardstatus);

        String userInfo = "<html>";
        userInfo += "Health : " + Integer.toString(game.player.health) + "/" + Integer.toString(game.player.maxHealth) + "<br>";
        userInfo += "Mana : " + Integer.toString(game.player.mana) + "<br>";
        playerStatus.setText(userInfo);

        // String monsterInfo = "<html>";
        // monsterInfo += "Name : " + game.enemy.name + "<br>";
        // monsterInfo += "Health : " + Integer.toString(game.enemy.health) + "/" + Integer.toString(game.enemy.maxHealth) + "<br>";
        // bossStatus.setText(monsterInfo);

    }

    class MyActionListener implements ActionListener {
        Game game = Game.getInstance();

        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            // switch (b.getText()) {
            //     case "card0":
            //         game.player.useCard(0, game.enemy);
            //         break;
            //     case "card1":
            //         game.player.useCard(1, game.enemy);
            //         break;
            //     case "card2":
            //         game.player.useCard(2, game.enemy);
            //         break;
            //     case "card3":
            //         game.player.useCard(3, game.enemy);
            //         break;
            //     case "card4":
            //         game.player.useCard(4, game.enemy);
            //         break;
            //     case "End Turn":
            //         game.turnEnd();
            //         break;
            // }
            updateLabel();
            // updateButton(); //카드 갯수에 맞게 버튼 초기화. 근데 작동 안되서 빼놓음

        }
    }

}
