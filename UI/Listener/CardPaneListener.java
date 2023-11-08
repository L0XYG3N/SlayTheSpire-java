package UI.Listener;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game.Monster;
import UI.CollideChecker;
import UI.Pane.BattlePane;
import UI.Pane.CardPane;
import UI.Pane.MonsterPane;

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

            pane.repaint();
        }
        public void mouseReleased(MouseEvent e) {
            MonsterPane collidedMonsterPane = CollideChecker.getCollidedMonster(pane, BattlePane.monsters);
            
            Container c;

            if(highlighted != -1)
                BattlePane.monsters[highlighted].deHighlight();
                
            try {
                c = pane.getParent();
            } catch(Exception ex) {
                return;
            }
            if(collidedMonsterPane != null) {
                Monster collidedMonster = collidedMonsterPane.getMonster();
                if(collidedMonster!= null) {
                    pane.useCard(collidedMonster);
                    collidedMonsterPane.updateLabel();
                    c.repaint();
                    return;
                }
            }
            pane.moveBack();

            

        }

}
