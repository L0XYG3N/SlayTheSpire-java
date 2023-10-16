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
    protected CardTarget cardTarget;

    protected int cardID; // String 배열로부터 카드의 이름과 설명을 가져오기 위한 변수
    protected boolean exhausted; // "소멸", 한번 사용시 현재 전투에서 더이상 이 카드가 나타나지 않음
    protected boolean ethereal; // "휘발성", 사용여부에 상관없이 무덤 덱으로 들어가는 순간 현재 전투에서 더이상 나타나지 않음

    public abstract void use(BaseObject obj);

    public abstract void upgrade();

    public int getCardID() {
        return cardID;
    }

}

class AttackCard extends Card { // 임시카드, 이후에 제대로 구현
    private int damage;

    public AttackCard() {
        cost = 1;
        cardID = 1;
        damage = 3;
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
    }

    public void upgrade() {
    }
}

class Strike extends Card {
    private int damage;

    public Strike() {
        cost = 1;
        cardID = 2;
        damage = 5;
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