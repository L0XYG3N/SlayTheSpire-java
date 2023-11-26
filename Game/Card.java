package Game;

public abstract class Card {
    public enum CardType {
        ATTACK, SKILL, POWER
    }

    public enum CardTarget {
        PLAYER, ENEMY, ENEMYALL
    }

    protected int cost;

    protected CardTarget cardTarget;

    protected CardType cardType;

    protected int cardID; // String 배열로부터 카드의 이름과 설명을 가져오기 위한 변수

    protected String cardName; // 아니면 카드 자체에 이름 넣기, 이름 배열보다 이게 더 나을수도..?
    protected String cardDescription;
    protected String imagePath = "";

    protected boolean exhausted; // "소멸", 한번 사용시 현재 전투에서 더이상 이 카드가 나타나지 않음
    protected boolean ethereal; // "휘발성", 사용여부에 상관없이 무덤 덱으로 들어가는 순간 현재 전투에서 더이상 나타나지 않음

    protected boolean isUpgrade;

    public abstract void use(BaseObject obj);

    public abstract void toggleUpgrade();

    public abstract void updateDescription();

    public int getCardID() {
        return cardID;
    }

    public int getCost() {
        return cost;
    }

    public String getCardName() {
        return cardName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        updateDescription();
        String front = "<html><div style='text-align:center;color:white'>";
        String back = "</div></html>";

        return front + cardDescription + back;
    }

    public Boolean isUpgraded() {
        return isUpgrade;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getCardTypeAsString() {
        switch (this.cardType) {
            default:
                return "";
            case ATTACK:
                return "공격";
            case SKILL:
                return "스킬";
            case POWER:
                return "파워";
        }
    }

    public CardTarget getCardTarget() {
        return cardTarget;
    }

}

class TestCard extends Card {
    private int damage;
    private int damageOrigin;
    private int damageUpgraded;

    public TestCard() {
        cardTarget = CardTarget.ENEMY;
        cost = 1;
        cardID = -11;
        damageOrigin = 3;
        damageUpgraded = damage = damageOrigin;

        cardName = "디버그 카드";
        cardDescription = "디버그 카드.<br>적에게 데미지 " + damage + " 부여";
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
        System.out.println("damage " + damage + " dealt to an monster, health remain : " + obj.health);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            damage = damageOrigin;
        } else {
            damage = damageUpgraded;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "디버그 카드.<br>적에게 데미지 " + damage + " 부여";
    }
}

class AttackCard extends Card { // 임시카드, 이후에 제대로 구현
    private int damage;
    private int damageOrigin;
    private int damageUpgraded;

    public AttackCard() {
        cardTarget = CardTarget.ENEMY;
        cost = 1;
        cardID = -12;
        damageOrigin = 3;
        damage = damageOrigin;
        cardName = "공격";
        cardDescription = "피해를 " + damage + " 줍니다.";
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            damage = damageOrigin;
        } else {
            damage = damageUpgraded;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "피해를 " + damage + " 줍니다.";
    }
}

class Strike extends Card {
    private int damage;
    private int damageOrigin;
    private int damageUpgraded;

    public Strike() {
        cardTarget = CardTarget.ENEMY;
        cardType = CardType.ATTACK;
        cost = 1;
        cardID = 1;
        damageOrigin = 6;
        damage = damageOrigin;
        cardName = "타격";
        cardDescription = "피해를 " + damage + " 줍니다.";
        imagePath = "Img/red/attack/strike.png";
        isUpgrade = false;
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            damage = damageOrigin;
        } else {
            damage = damageUpgraded;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "피해를 " + damage + " 줍니다.";
    }
}

class Bash extends Card {

    private int damage;
    private int damageOrigin;
    private int damageUpgraded;

    private int effectDuration;
    private int effectOrigin;
    private int effectUpgraded;

    public Bash() {
        cardTarget = CardTarget.ENEMY;
        cardType = CardType.ATTACK;
        cost = 2;
        cardID = 2;
        damageOrigin = 8;
        damageUpgraded = 10;
        effectOrigin = 2;
        effectUpgraded = 3;

        damage = damageOrigin;
        effectDuration = effectOrigin;

        cardName = "강타";
        cardDescription = "피해를 " + damage + " 줍니다.<br>" +
                "<font color='orange'>취약</font>을 " + effectDuration + " 부여합니다.";

        imagePath = "Img/red/attack/bash.png";
    }

    public void use(BaseObject obj) {
        Effects.attack(damage, obj);
        Effects.addStatus(obj, STATUS.VULNERABLE, effectDuration);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            damage = damageOrigin;
            effectDuration = effectOrigin;
        } else {
            damage = damageUpgraded;
            effectDuration = effectUpgraded;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "피해를 " + damage + " 줍니다.<br>" +
                "<font color='orange'>취약</font>을 " + effectDuration + " 부여합니다.";
    }
}

class Anger extends Card {
    public Anger() {
        cardTarget = CardTarget.ENEMY;
        cost = 0;
        cardID = 3;
        cardType = CardType.ATTACK;
    }

    public void use(BaseObject obj) {
    }

    public void toggleUpgrade() {
    }

    public void updateDescription() {
    }
}

class BodySlam extends Card {
    private int damage;

    public BodySlam() {
        cardTarget = CardTarget.ENEMY;
        cost = 0;
        cardID = 4;
        cardName = "몸통 박치기";
        cardDescription = "현재 <font color='orange'>방어도</font> 만큼의<br>피해를 줍니다.";
        cardType = CardType.ATTACK;
        imagePath = "Img/red/attack/body_slam.png";
    }

    public void use(BaseObject obj) {
        damage = Player.getInstance().getShield();
        Effects.attack(damage, obj);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            this.cost = 1;
        } else {
            this.cost = 0;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "현재 <font color='orange'>방어도</font> 만큼의<br>피해를 줍니다.";
    }
}

class Clash extends Card {
    public Clash() {
        cardTarget = CardTarget.ENEMY;
        cost = 0;
        cardID = 5;
    }

    public void use(BaseObject obj) {

    }

    public void toggleUpgrade() {

    }

    public void updateDescription() {
    }
}

class Defend extends Card {
    private int defend;
    private int defendOrigin;
    private int defendUpgraded;

    public Defend() {
        cardTarget = CardTarget.PLAYER;
        cardType = CardType.SKILL;
        cost = 1;
        cardID = 100;

        defendOrigin = 5;
        defendUpgraded = 8;
        defend = defendOrigin;

        cardName = "수비";
        cardDescription = "<font color='orange'>방어도</font>를 " + defend + " 얻습니다.";

        imagePath = "Img/red/skill/defend.png";

    }

    public void use(BaseObject obj) {
        obj.addShield(defend);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            defend = defendOrigin;
        } else {
            defend = defendUpgraded;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "<font color='orange'>방어도</font>를 " + defend + " 얻습니다.";
    }
}

class Armaments extends Card {
    public void use(BaseObject obj) {
    }

    public void toggleUpgrade() {
    }

    public void updateDescription() {
    }
}

class Flex extends Card {
    public void use(BaseObject obj) {
    }

    public void toggleUpgrade() {
    }

    public void updateDescription() {
    }
}

class Havoc extends Card {
    public void use(BaseObject obj) {
    }

    public void toggleUpgrade() {
    }

    public void updateDescription() {
    }
}

class ShrugOff extends Card {
    public void use(BaseObject obj) {
    }

    public void toggleUpgrade() {
    }

    public void updateDescription() {
    }
}