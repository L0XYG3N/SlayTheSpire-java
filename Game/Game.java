package Game;

public class Game {
    // 게임의 전체적인 부분을 담은 클래스
    public Field field;
    public Player player;
    public GameMap gameMap; // 스테이지의 지도

    // 싱글톤 기법
    private static Game instance = new Game();

    public static Game getInstance() {
        return instance;
    }

    private Game() {
        field = Field.getInstance();
        player = Player.getInstance();
        gameMap = new GameMap();
    }

    public void turnEnd() {
        player.updatePlayerStatus();
        
        for (int i = 0; i < 5; i++) {
            if (field.enemies[i] != null) {
                field.enemies[i].continuePattern();
                field.enemies[i].status.updateEffects();
                field.enemies[i].monsterIntention();
            }
        }

        player.turnEnd();
    }

    public void resetGame() {
        player.initPlayer();
        gameMap = new GameMap();
    }

}
