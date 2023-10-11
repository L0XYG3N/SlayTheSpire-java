package Game;

public class Field {
    //게임 내 몬스터의 정보는 여기에 저장될 예정임.
    private static Field instance = new Field();
    public int currentTurn;
    public Enemies enemies[];

    private Field() {
        enemies = new Enemies[5];   // 필드에 최대 5마리의 몬스터가 나타날 예정
    }

    public void initStage(int stage) {
        
    }
    
    public static Field getInstance() {
        return instance;
    }


}
