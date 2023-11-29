package Util;

import UI.Pane.CardPane;
import UI.Pane.MonsterPane;
import UI.Pane.PlayerPane;

public class CollideChecker {

    public static MonsterPane getCollidedMonster(CardPane card, MonsterPane [] enemy) {
        // 카드와 충돌된 몬스터가 들어있는 JLayeredPane 리턴.
        // 여러개의 적과 충돌됬을 경우 최대 2개의 적과 충돌할 수 있기 때문에
        // 두 적 중 더 가까운 적을 리턴
        MonsterPane enemy1 = null;
        MonsterPane enemy2 = null;

        for(int i = 0; i < 5;i++) {


            if(enemy[i] == null) continue;
            if(enemy[i].getMonster() == null) continue;
            
            if(isCollidedWith(card,enemy[i])) {
                if(enemy1 == null) enemy1 = enemy[i];
                else enemy2 = enemy[i];
            }
        }

        if(enemy2 == null)  {
            if(enemy1 == null) return null;

            //System.out.println(enemy1.getMonster().getName());
            return enemy1;
        }
        

        float d1 = getDistance(card, enemy1);
        float d2 = getDistance(card, enemy2);
        return (d1 < d2 ? enemy1 : enemy2);
    }

    public static boolean isCollidedWithPlayer(CardPane card) {
        //카드와 플레이어의 충돌 체크 함수
        int x1 = card.getX();
        int y1 = card.getY();
        int x2 = PlayerPane.X;
        int y2 = PlayerPane.Y;

        if(x1 + CardPane.WIDTH  >= x2 && x1 <= x2 + PlayerPane.WIDTH &&
           y1 + CardPane.HEIGHT >= y2 && y1 <= y2 + PlayerPane.HEIGHT) {
            return true;
        }

        return false;
    }

    public static boolean isCollidedWith(CardPane card, MonsterPane enemy) {
        //카드와 몬스터의 충돌 체크 함수
        int x1 = card.getX();
        int y1 = card.getY();

        int x2 = enemy.getX();
        int y2 = enemy.getY();

        if(x1 + CardPane.WIDTH  >= x2 && x1 <= x2 + MonsterPane.WIDTH &&
           y1 + CardPane.HEIGHT >= y2 && y1 <= y2 + MonsterPane.HEIGHT) {
            return true;
        }

        return false;

    }

    public static float getDistance(CardPane card,MonsterPane enemy) {
        int cx = card.getX() + CardPane.WIDTH/2;
        int cy = card.getY() + CardPane.HEIGHT/2;
        int ex = enemy.getX() + MonsterPane.WIDTH/2;
        int ey = enemy.getY() + MonsterPane.HEIGHT/2;
        float xLen = cx-ex;
        float yLen = cy-ey;

        return (float)Math.sqrt(xLen*xLen+yLen*yLen);
    }

}
