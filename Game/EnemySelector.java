package Game;

public class EnemySelector {
    public static Monster[] getEnemies(int stageID) {
        /*
         * 정해진 대로 적을 생성해주는 클래스.
         * 스테이지 id별로 적 배열을 생성해 리턴한다.
         */
        Monster[] enemyList = new Monster[5];
        switch (stageID) {
            case 1:
                // enemyList[0] = new Cultist();
                break;
            case 2:
                //enemyList[0] = new JawWorm();
                break;
            case 3:
                enemyList[0] = new Louse_Red();
                enemyList[1] = new Louse_Green();
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
