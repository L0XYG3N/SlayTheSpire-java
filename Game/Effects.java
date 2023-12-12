package Game;

enum STATUS {
    STRENGTH, VULNERABLE, WEAK, DEXTERITY, REGENERATION, STEELARMOR
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
    public static void addStatus(BaseObject target, STATUS stat,int stack) {
        switch (stat) {

            case STRENGTH:
                target.status.strength.stack += stack;
                break;

            case VULNERABLE:
                target.status.vulnerable.stack += stack;
                break;

            case WEAK:
                target.status.weak.stack += stack;
                break;

            case DEXTERITY:
                target.status.dexterity.stack += stack;
                break;

            case REGENERATION:
                target.status.regeneration.stack += stack;
                break;

            case STEELARMOR:
                target.status.steelArmor.stack += stack;
                break;
        }
    }

    

    public static void weakenShield(int duration) {
        // 손상 디버프, 지속시간동안 방어도 획득량 25%만큼 줄어듬
        Player.getInstance().weakenShield = duration;
    }

    public static void addMana(int amount) {
        Player.getInstance().addMana(amount);
    }

    public static Card copyCard(int cardID) {
        return CardGetter.GetCardById(cardID);
    }

}