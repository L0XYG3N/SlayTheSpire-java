package UI;

import UI.Pane.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Game.BaseObject;
import Game.CardGetter;
import Game.Field;
import Game.Player;

import java.awt.*;

public class GUI{
    
    private Player player = Player.getInstance();


    
    
    public MainFrame frame;
    public BattlePane battlePane;
    public JLayeredPane shopPane;
    public JLayeredPane mapPane;

    
    public CardDeckPane drawPane;
    public CardDiscardPane discardPane;
    //public CardExhaustedPane exhaustedPane;

    private TitledBorder drawBorder;
    private TitledBorder discardBorder;
    public final boolean debugMode;

    public GUI(boolean debugMode) {
        initAllPanes();
        frame = new MainFrame();
        frame.updatePane(battlePane);
        this.debugMode = debugMode;
        
    }

    public void initAllPanes() {
        battlePane = new BattlePane();
        /*
        shopPane = DefaultPaneGetter.getNewJLayeredPane();
        mapPane = DefaultPaneGetter.getNewJLayeredPane();
        */
    }



}
