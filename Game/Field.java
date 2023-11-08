package Game;

enum endState {
    GAMEOVER, BATTLEWIN,
}

public class Field {
    // 게임 내 몬스터 관련 정보는 여기에 저장될 예정임.
    private static Field instance = new Field();
    public int currentTurn;
    public Monster enemies[];

    private Field() {
        // enemies = new Enemies[5]; // 필드에 최대 5마리의 몬스터가 나타날 예정
        initStage(3);
    }

    public void initStage(int stageID) {
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
                // 배틀 끝내고 획득할 카드 선택
                break;
        }
    }

}
