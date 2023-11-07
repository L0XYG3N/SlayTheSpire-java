package UI;

import java.awt.Dimension;

import Game.Monster;
import UI.Pane.CardPane;
import UI.Pane.EnemyPane;
import UI.Pane.PlayerPane;

public class CollideChecker {
    public static Monster getCollidedMonster(CardPane card, EnemyPane [] enemy) {
        EnemyPane enemy1 = null;
        EnemyPane enemy2 = null;

        for(int i = 0; i < 5;i++) {
            if(enemy[i] == null) continue;
            if(isCollideWith(card,enemy[i])) {
                if(enemy1 == null) enemy1 = enemy[i];
                else enemy2 = enemy[i];
            }
        }

        if(enemy2 == null) return enemy1.getMonster();
        
        float d1 = getDistance(card, enemy1);
        float d2 = getDistance(card, enemy2);
        return (d1 > d2 ? enemy1.getMonster() : enemy2.getMonster());
    }

    public static boolean isCollideWith(CardPane card, PlayerPane player) {
        int x1 = card.getX();
        int y1 = card.getY();
        int x2 = x1 + CardPane.WIDTH;
        int y2 = y1 + CardPane.HEIGHT;

        int x3 = player.getX();
        int y3 = player.getY();
        int x4 = x3 + PlayerPane.WIDTH;
        int y4 = y3 + PlayerPane.HEIGHT;

        if (x1 > x4 || x3 > x2)
            return false;

        if (y2 > y3 || y4 > y1)
            return false;

        return true;
    }

    public static boolean isCollideWith(CardPane card, EnemyPane enemy) {
        int x1 = card.getX();
        int y1 = card.getY();
        int x2 = x1 + CardPane.WIDTH;
        int y2 = y1 + CardPane.HEIGHT;

        int x3 = enemy.getX();
        int y3 = enemy.getY();
        int x4 = x3 + EnemyPane.WIDTH;
        int y4 = y3 + EnemyPane.HEIGHT;

        if (x1 > x4 || x3 > x2)
            return false;

        if (y2 > y3 || y4 > y1)
            return false;

        return true;


    }

    public static float getDistance(CardPane card,EnemyPane enemy) {
        int cx = card.getX() + CardPane.WIDTH/2;
        int cy = card.getY() + CardPane.HEIGHT/2;
        int ex = enemy.getX() + EnemyPane.WIDTH/2;
        int ey = enemy.getY() + EnemyPane.HEIGHT/2;
        float xLen = cx-ex;
        float yLen = cy-ey;

        return (float)Math.sqrt(xLen*xLen+yLen*yLen);
    }


}
