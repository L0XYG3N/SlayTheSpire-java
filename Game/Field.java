package Game;
import UI.GUI;

public class Field {
    // 게임 내 몬스터 관련 정보는 여기에 저장될 예정임.
    private static Field instance = new Field();
    public int currentTurn;
    public Monster enemies[];

    public enum endState {
    GAMEOVER, BATTLEWIN,
    }

    private Field() {
        // enemies = new Enemies[5]; // 필드에 최대 5마리의 몬스터가 나타날 예정
        initStage(3);
    }

    public void initStage(int stageID) {
        currentTurn = 0;
        enemies = MonsterSelector.getEnemies(stageID);
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
                initStage(3);
                GUI.updateScreen();
                // 배틀 끝내고 획득할 카드 선택
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
