package Game;

public class EnemySelector {
    public static Enemies[] getEnemies(int stageID) {
        Enemies[] enemyList = new Enemies[5];
        switch (stageID) {
            case 1:
                // enemyList[0] = new Cultist();
                break;
            case 2:
                //enemyList[0] = new JawWorm();
                break;
            case 3:
                enemyList[0] = new Louse();
                enemyList[1] = new Louse();
            case 4:
                //enemyList[0] = new Slime(2, 0);
                //enemyList[1] = new Slime(1, 0);
            case 5:
                //enemyList[0] = new Slime(2, 1);
                //enemyList[1] = new Slime(1, 1);

        }
        return enemyList;
    }
}
