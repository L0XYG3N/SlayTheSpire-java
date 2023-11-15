package Game;

class Stat {
    public int power;
    public int duration;

    public Stat() {
        power = 0;
        duration = 0;
    }

    public void update() {
        /*
         * duration을 1씩 감소시키며, 0 밑으로 내려가지 않는다.
         */
        duration = Math.max(0, --duration);
    }

    
    public int getPower() {
        return power;
    }

    public int getDuration() {
        return duration;
    }
}


public class BuffStatus {
    public Stat strength; // 힘, 스탯의 power만큼 데미지 증가
    public Stat vulnerable; // 취약, duration동안 주는 데미지 50% 감소
    public Stat fear; // 약화, 
    public Stat dexterity; // 민첩
    public Stat regeneration;// 재생
    public Stat steelArmor; // 판금갑옷
    
    public BuffStatus() {
        strength = new Stat();
        vulnerable = new Stat();
        fear = new Stat();
        dexterity = new Stat();
        regeneration = new Stat();
        steelArmor = new Stat();
    }
    
    public void updateEffects() {
        // 매 턴이 끝날때 실행되며, 모든 스탯의 지속시간을 1씩 감소시킨다.
        //strength.update();
        vulnerable.update();
        fear.update();
        dexterity.update();
        regeneration.update();
        steelArmor.update();
    }

}
