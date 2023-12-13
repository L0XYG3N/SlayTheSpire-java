package Game;

import static Game.Stat.StackingBehavior.*;

class Stat {
    public enum StackingBehavior {
        INTENSITY, DURATION
    };

    public int stack;

    private final StackingBehavior stackingBehavior;

    public Stat(StackingBehavior behavior) {
        stack = 0;
        this.stackingBehavior = behavior;
    }

    public void update() {
        switch (stackingBehavior) {
            case INTENSITY:
            		break;
            case DURATION:
                stack = Math.max(0, --stack);
        }
    }

    public int getStack() {
        return stack;
    }
}

public class BuffStatus {
    public Stat strength; // 힘, stack만큼 데미지 증가/감소, 전투가 끝날 때 까지 유지
    public Stat vulnerable; // 취약, stack동안 받는 데미지 50% 증가
    public Stat weak; // 약화, stack동안 주는 데미지 25% 감소
    public Stat dexterity; // 민첩, stack만큼 카드로부터 얻는 방어도 증가
    public Stat regeneration;// 재생, 턴이 끝날때마다 stack만큼 체력 회복 후 1 감소
    public Stat steelArmor; // 판금갑옷, 턴이 끝날때 stack만큼 방어도 추가 후 1 감소

    public BuffStatus() {
    	init();
    }

    public String getPlayerStatus() {
        return "힘: " + strength.getStack() + 
               "  취약: " + vulnerable.getStack() +
               "  약화: " + weak.getStack();
               //"  Dexterity: " + dexterity.getStack() +
               //"  Regeneration: " + regeneration.getStack() +
               //"  SteelArmor: " + steelArmor.getStack();
    }
    
    public void updateEffects() {
        // 매 턴이 끝날때 실행되며, 모든 스탯의 지속시간을 1씩 감소시킨다.
        strength.update();
        vulnerable.update();
        weak.update();
        dexterity.update();
        regeneration.update();
        steelArmor.update();
    }

    public void init() {
        strength = new Stat(INTENSITY);
        vulnerable = new Stat(DURATION);
        weak = new Stat(DURATION);
        dexterity = new Stat(INTENSITY);
        regeneration = new Stat(DURATION);
        steelArmor = new Stat(DURATION);
    }

}
