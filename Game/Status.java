package Game;

class Stat {
    public int power;
    public int duration;

    public void update() {
        //임시 코드
        /*
         * duration을 1씩 감소시키며, 0 밑으로 내려가지 않는다.
         */
        duration = Math.max(0, --duration);
    }

}

public class Status {
    public Stat strength; // 힘
    public Stat weakness; // 취약
    public Stat fear; // 약화
    public Stat dexterity; // 민첩
    public Stat regeneration;// 재생
    public Stat steelArmor; // 판금갑옷

    public void updateEffects() {
        // 임시코드
        // 매 턴이 끝날때 실행되며, 모든 스탯의 지속시간을 1씩 감소시킨다.
        strength.update();
        weakness.update();
        fear.update();
        dexterity.update();
        regeneration.update();
        steelArmor.update();
    }

}
