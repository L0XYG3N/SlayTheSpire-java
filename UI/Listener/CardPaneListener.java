package UI.Listener;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLayeredPane;

import Game.*;
import Game.Card.CardTarget;
import UI.*;
import UI.Pane.*;
import Util.CollideChecker;

public class CardPaneListener extends MouseAdapter {
    public CardPaneListener(CardPane pane) {
        this.pane = pane;
    }
    CardPane pane;

    //private int dragOffsetX;
    //private int dragOffsetY;
    int highlighted = -1;

    public void mouseDragged(MouseEvent evt) {
        pane.moveTo(
                pane.getX() + evt.getX() - CardPane.WIDTH/2,
                pane.getY() + evt.getY() -  CardPane.HEIGHT/2
        );

        switch(pane.card.getCardTarget()) {
            case ENEMY:
                selectEnemy();
                break;
            case ENEMYALL:
                break;
            case PLAYER:
                selectPlayer();
                break;
        }


        

        pane.repaint();
    }

    public void mouseReleased(MouseEvent evt) {
        JLayeredPane collidedPane;
        Container c;
        try {
            c = pane.getParent();
        } catch(Exception ex) {
            throw ex;
        }

        switch(pane.card.getCardTarget()) {
            case ENEMY:
                collidedPane = CollideChecker.getCollidedMonster(pane, BattlePane.monsters);
                if(highlighted != -1)
                BattlePane.monsters[highlighted].deHighlight();
                if(collidedPane != null) {
                    Monster collidedMonster = ((MonsterPane)collidedPane).getMonster();
                    if(collidedMonster!= null) {
                        pane.useCard(collidedMonster);
                        ((MonsterPane)collidedPane).updateLabel();
                        c.repaint();
                        return;
                    }
                }
                break;
            case ENEMYALL:
                break;
            case PLAYER:
                if(CollideChecker.isCollidedWithPlayer(pane)) {
                    pane.useCard(Player.getInstance());
                    c.repaint();
                }
        }
        pane.moveBack();
    }
    
    private void selectEnemy() {
        MonsterPane collided = CollideChecker.getCollidedMonster(pane, BattlePane.monsters);
        if(collided != null) {
            if(highlighted != -1) 
                BattlePane.monsters[highlighted].deHighlight();

            collided.highlight();
            highlighted = collided.arrayIndex;
            // 카드가 몬스터에 맞닿아있으면 카드 설명 업데이트 계속 호출
            // 카드 설명에 있는 데미지 바뀌게 하기 위한 코드
            //updateDescription() 
        } else {
            if(highlighted != -1)
                BattlePane.monsters[highlighted].deHighlight();
            highlighted = -1;
        }
    }

    private void selectPlayer() {
        if(CollideChecker.isCollidedWithPlayer(pane)) {
            
        }
    }
}
