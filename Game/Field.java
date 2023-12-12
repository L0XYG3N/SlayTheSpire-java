package Game;
import UI.GUI;
import UI.GUI.ScreenState;

public class Field {
    // 전투 중에 쓰이는 클래스.
    // 전투 관련 정보(몬스터, 턴 수 등)는 여기에 저장될 예정임.
    private static Field instance = new Field();
    public int currentTurn = 1;   
    public Monster enemies[];
    

    public enum endState {
    GAMEOVER, BATTLEWIN,
    }

    private Field() {
        // enemies = new Enemies[5]; // 필드에 최대 5마리의 몬스터가 나타날 예정
    }

    public void initEasyStage(int stageID) {
        enemies = MonsterSelector.easyGetEnemies(stageID);
        Player.getInstance().cards.initBattle();
    }
    public void initStage(int stageID) {
        enemies = MonsterSelector.getEnemies(stageID);
        Player.getInstance().cards.initBattle();
    }
    
    public void split(int stageID) {
        enemies = MonsterSelector.getSplit(stageID);
    }

    public static Field getInstance() {
        return instance;
    }

    public void endBattle(endState state) {
        switch (state) {
            case GAMEOVER:
                // game.gameover();
                break;
            case BATTLEWIN:
                System.out.println("battle win");
                
                //보상 띄우기
                /*
                 * 일반몹 : 10-20코인
                 * 엘리트 : 25-35코인
                 * 보스몹 : 95:105코인
                 */

                //

                GUI.changeScreen(ScreenState.REWARD);
                //GUI.updateScreen();
                break;
        }
    }

    public boolean isBattleEnd() {
        for(int i = 0; i < 5; i++) {
            if(enemies[i] != null) {
                return false;
            }
        }
        return true;
    }

}