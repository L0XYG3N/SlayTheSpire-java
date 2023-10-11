package Game;

enum STATUS {
    STRENGTH, WEAKNESS, FEAR, DEXTERITY, REGENERATION, STEELARMOR
};

public class Effects {
    /*
     * 공격, 방어막 증가 등등의 효과가 구현된 클래스
     * BaseObject 클래스를 인자로 받아 그걸 대상으로 각종 효과 부여
     * 플레이어에게만 해당되는 함수는 BaseObject를 인자로 받지 않는다.
     */

    public static void attack(int amount, BaseObject obj) {
        obj.damage(amount);
    }

    public static void addShield(int amount, BaseObject obj) {
        obj.addShield(amount);
    }

    // 상태이상을 부여하는 함수, 특정 상태에 강도와 지속시간을 부여한다
    public static void addStatus(BaseObject target, STATUS stat, int power, int duration) {
        switch (stat) {
            case STRENGTH:
                target.status.strength.duration = duration;
                target.status.strength.power = power;
                break;

            case WEAKNESS:
                target.status.weakness.duration = duration;
                target.status.weakness.power = power;
                break;

            case FEAR:
                target.status.fear.duration = duration;
                target.status.fear.power = power;
                break;

            case DEXTERITY:
                target.status.dexterity.duration = duration;
                target.status.dexterity.power = power;
                break;

            case REGENERATION:
                target.status.regeneration.duration = duration;
                target.status.regeneration.power = power;
                break;

            case STEELARMOR:
                target.status.steelArmor.duration = duration;
                target.status.steelArmor.power = power;
                break;
        }
    }

    public static void weakenShield(int duration) {
        // 손상 디버프, 지속시간동안 방어도 획득량 줄어듬
        Player.getInstance().weakenShield = duration;
    }

    public static void addMana(int amount) {
        Player.getInstance().addMana(amount);
    }

    public static Card copyCard(int cardID) {
        return new AttackCard(); // TODO return a card based on cardID
    }

}