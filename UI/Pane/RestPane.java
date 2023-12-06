package UI.Pane;

import javax.swing.JButton;
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
            , upgrade = new JButton("강화");


        rest.setBounds(300,334,200,100);
        upgrade.setBounds(866,334,200,100);
        add(rest);
        add(upgrade);

        rest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //체력 20퍼센트 회복 후 다시 지도 표시
                player.addHealth((int)(player.maxHealth * 0.25));
                GUI.changeScreen(ScreenState.MAP);
            }
        });

        upgrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });


    }
}
