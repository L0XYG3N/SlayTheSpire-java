package Game;

public abstract class Monster extends BaseObject {
    // 몬스터용 추상 클래스
    public String name;
    //
    public int nextMove;

    public int[] moveSet;
    int dmg;

    public void setNextMove() {
        nextMove = Rand.getNumByProbability(moveSet);
    }

    public void continuePattern() {
        // 방어막 초기화
        shield = 0;

        // 재생
        if(status.regeneration.stack > 0) {
            addHealth(status.regeneration.stack);
        }

        // 판금 갑옷
        if(status.steelArmor.stack > 0) {
            addShield(status.steelArmor.stack);
        }
    }

    public String getName() {
        return name;
    }

    public int getCalculatedDamage() {
        int damage = dmg;
        damage += status.strength.stack;
        if(status.weak.stack > 0) {
            damage = (int)(damage * 0.75);
        }
        return damage;
    }
    
}

class Rand {
    // a에서 b까지의 랜덤 int 리턴하는 함수(b 포함)
    public static int randInt(int a, int b) {
        return (int)(Math.random() * ((b + 1) - a)) + a;
    }

    // 각 인덱스가 나올 확률을 변수로 가진 배열을 받아 확률에 따라 int값을 리턴하는 함수
    // 리턴되는 값은 입력 배열의 사이즈 값보다 작거나 같다
    public static int getNumByProbability(int[] prob) {
        double[] probSum = new double[prob.length];
        probSum[0] = prob[0];
        for (int i = 1; i < prob.length; i++) {
            probSum[i] = probSum[i - 1] + prob[i];
        }

        double decision = Math.random() * 100;
        for (int i = 0; i < prob.length; i++) {
            if (decision < probSum[i]) {
                return i;
            }
        }
        return prob.length - 1;
    }
}

class LouseRed extends Monster {
    private boolean roll;
    public final int rollShield;

    public LouseRed() {
        health = Rand.randInt(10, 15);
        maxHealth = health;
        name = "빨간 공벌레";
        dmg = Rand.randInt(5, 7);
        moveSet = new int[] { 75, 25 };
        status = new BuffStatus();
        rollShield = Rand.randInt(3, 7);
        setNextMove();
        
        // 몸 말기 스킬 사용중
        roll = true;
    }

    public void damage(int amount) {
        super.damage(amount);
        // 데미지를 받을 시 몸 말기 스킬이 끝나고 해당 효과 발생
        if (roll) {
            roll = false;
            addShield(rollShield);
        }
    }

    public void continuePattern() {
        // 공벌레의 패턴 진행, 공격 스킬과 상태이상 부여하는 스킬 중 정해진 확률대로 사용
        if (nextMove == 0) {
            //데미지 계산
            int damage = getCalculatedDamage();

            Effects.attack(damage, Player.getInstance());

            //Effects.attack(getCalculatedDamage(), Player.getInstance());
        } else if (nextMove == 1) {
            Effects.addStatus(this, STATUS.STRENGTH, 3);
        }
        dmg = Rand.randInt(5, 7);

        
        super.continuePattern();
        setNextMove();
    }
}

class LouseGreen extends Monster {
    private boolean roll;
    public final int rollShield;

    public LouseGreen() {

        health = Rand.randInt(11, 17);
        name = "초록 공벌레";
        dmg = Rand.randInt(5, 7);
        moveSet = new int[] { 75, 25 };
        status = new BuffStatus();
        maxHealth = health;
        rollShield = Rand.randInt(3, 7);
        setNextMove();

        // 몸 말기 스킬 사용중
        roll = true;
    }

    public void damage(int amount) {
        super.damage(amount);
        // 데미지를 받을 시 몸 말기 스킬이 끝나고 해당 효과 발생
        if (roll) {
            roll = false;
            addShield(rollShield);
        }
    }

    public void continuePattern() {
        // 공벌레의 패턴 진행, 공격 스킬과 상태이상 부여하는 스킬 중 정해진 확률대로 사용
        if (nextMove == 0) {
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
            Effects.addStatus(Player.getInstance(), STATUS.WEAK, 3);
        }
        dmg = Rand.randInt(5, 7);
        super.continuePattern();
        setNextMove();
    }
}
