package UI.Listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UI.CollideChecker;
import UI.Pane.CardPane;

public class CardPaneListener extends MouseAdapter {
        public CardPaneListener(CardPane pane) {
            this.pane = pane;
        }
        CardPane pane;

        //private int dragOffsetX;
        //private int dragOffsetY;

        public void mouseDragged(MouseEvent evt) {
            pane.moveTo(
                    pane.getX() + evt.getX() - CardPane.WIDTH/2,
                    pane.getY() + evt.getY() -  CardPane.HEIGHT/2
            );

            //updateDescription() //카드 설명 업데이트?

            if(CollideChecker.getCollidedMonster(pane, null) != null) {
                
            }

            pane.repaint();
        }
        public void mouseReleased(MouseEvent e) {
            
            pane.moveBack();

        }

}
