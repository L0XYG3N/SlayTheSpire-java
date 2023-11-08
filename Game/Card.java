package Game;

enum CardType {
    ATTACK, SKILL, POWER
}

enum CardTarget {
    PLAYER, ENEMY, ENEMYALL
}

public abstract class Card {
    protected int cost;

    protected CardType CardType;
    public CardTarget cardTarget;

    protected int cardID;       // String 배열로부터 카드의 이름과 설명을 가져오기 위한 변수

    protected String cardName;     // 아니면 카드 자체에 이름 넣기, 이름 배열보다 이게 더 나을수도..?
    protected String cardDescription;

    protected boolean exhausted; // "소멸", 한번 사용시 현재 전투에서 더이상 이 카드가 나타나지 않음
    protected boolean ethereal; // "휘발성", 사용여부에 상관없이 무덤 덱으로 들어가는 순간 현재 전투에서 더이상 나타나지 않음

    public abstract void use(BaseObject obj);

    public abstract void upgrade();

    public int getCardID() {
        return cardID;
    }

    public int getCost() {
        return cost;
    }

    public String getCardName() {
        return cardName;
    }

    public String getDescription() {
        return cardDescription;
    }
    
}

class TestCard extends Card {
    private int damage;
    private int damageOrigin;


    public TestCard() {
        cost = 1;
        cardID = 1;
        damageOrigin = 3;
        damage = damageOrigin;

        cardName = "디버그 카드";
        cardDescription = "디버그 카드.\n적에게 데미지 " + damage + " 부여";
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
        System.out.println("damage " + damage + " dealt to an monster, health remain : " + obj.health);
    }

    public void upgrade() {
        damage = 5;
    }
}


class AttackCard extends Card { // 임시카드, 이후에 제대로 구현
    private int damage;
    private int damageOrigin;

    public AttackCard() {
        cost = 1;
        cardID = 1;
        damageOrigin = 3;
        damage = damageOrigin;
        cardName = "공격";
        cardDescription = "피해를 "+damage+" 줍니다.";
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
    }

    public void upgrade() {
    }
}

class Strike extends Card {
    private int damage;
    private int damageOrigin;

    public Strike() {
        cost = 1;
        cardID = 2;
        damageOrigin = 6;
        damage = damageOrigin;
        cardName = "타격";
        cardDescription = "피해를 "+damage+" 줍니다.";
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
    }

    public void upgrade() {

    }
}

class Bash extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class Anger extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class BodySlam extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class Clash extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class Defend extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class Armaments extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class Flex extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class Havoc extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}

class ShrugOff extends Card {
    public void use(BaseObject obj) {
    }

    public void upgrade() {
    }
}