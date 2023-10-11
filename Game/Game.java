package Game;

public class Game {
    // 게임의 전체적인 부분을 담은 클래스
    public Field field;
    public Player player;

    // 몬스터 테스트용 임시 코드, 나중에 수정할 것
    //public Enemies enemy = new JawWorm();

    // 싱글톤 기법
    private static Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private Game() {
        field = Field.getInstance();
        player = Player.getInstance();

    }

    public void turnEnd() {
        player.cards.turnEnd();
        for(int i = 0; i < 5; i++) {
            if(field.enemies[i] != null) {
                field.enemies[i].continuePattern();
            }
        }
    }

}
