package UI.Listener;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLayeredPane;

import Game.*;
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
    public void mousePressed(MouseEvent evt) {
        BattlePane.getInstance().setLayer(pane,401);
    }

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
                if(CollideChecker.getCollidedMonster(pane, BattlePane.monsters) != null) {
                    for(int i = 0; i < 5;i++) {
                        if(BattlePane.monsters[i] != null) {
                            BattlePane.monsters[i].highlight();
                        }
                    }
                }
                
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
        BattlePane battlePane = BattlePane.getInstance();
        PlayerPane playerPane = PlayerPane.getInstance();

        try {
            c = pane.getParent();
        } catch(Exception ex) {
            throw ex;
        }

        switch(pane.card.getCardTarget()) {
            // 카드의 사용 타겟을 확인 후 카드에 맞는 코드 실행
            // useCard()에서 false가 리턴되면 마나 부족으로 실행 실패한 것
            case ENEMY:
                collidedPane = CollideChecker.getCollidedMonster(pane, BattlePane.monsters);
                if(highlighted != -1)
                BattlePane.monsters[highlighted].deHighlight();
                if(collidedPane != null) {
                    Monster collidedMonster = ((MonsterPane)collidedPane).getMonster();
                    if(collidedMonster!= null) {
                        if(pane.useCard(collidedMonster) == false) break;
                        ((MonsterPane)collidedPane).updateLabel();
                        battlePane.updateCardPane();
                        c.repaint();
                        return;
                    }
                }
                break;
            case ENEMYALL:
                for(int i = 0; i < 5;i++) {
                    if(BattlePane.monsters[i] != null) {
                        BattlePane.monsters[i].deHighlight();
                    }
                }
                if(pane.useCard(Field.getInstance().enemies) == false) {
                    break;
                }
                battlePane.updateCardPane();
                c.repaint();
                for(int i = 0; i < 5;i++) {
                    if(BattlePane.monsters[i] != null) {
                        BattlePane.monsters[i].updateLabel();
                    }
                }
                break;
            case PLAYER:
                if(CollideChecker.isCollidedWithPlayer(pane)) {
                    playerPane.deHighlight();
                    if(pane.useCard(Player.getInstance()) == false) break;
                    playerPane.updateLabel();
                    battlePane.updateCardPane();
                    c.repaint();
                }
        }
        pane.moveBack();
        battlePane.setLayer(pane,400);
    }
    
    private void selectEnemy() {
        // 카드 옮길때 해당되는 적이 카드에 닿으면 적 배경색 바꿔서 하이라이트하는 함수
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
        PlayerPane playerPane = PlayerPane.getInstance();
        
        if(CollideChecker.isCollidedWithPlayer(pane)) {
            playerPane.highlight();
        } else {
            playerPane.deHighlight();
        }
    }
}
