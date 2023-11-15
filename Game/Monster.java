package Game;

public abstract class Monster extends BaseObject {
    // 몬스터용 추상 클래스
    public String name;
    //   
    public int nextMove;

    public int [] moveSet;
    int dmg;

    public void setNextMove() {
        nextMove = Rand.getNumByProbability(moveSet);
    }

    abstract void continuePattern();

    public String getName() {
        return name;
    }
}

class Rand {
    // a에서 b까지의 랜덤 int 리턴하는 함수(b 포함)
    public static int randInt(int a, int b) {
        return (int) Math.random() * ((b + 1) - a) + a;
    }

    // 각 인덱스가 나올 확률을 변수로 가진 배열을 받아 확률에 따라 int값을 리턴하는 함수
    // 리턴되는 값은 입력 배열의 사이즈 값보다 작거나 같다
    public static int getNumByProbability(int[] prob) {
        double [] probSum = new double[prob.length];
        probSum[0] = prob[0];
        for(int i = 1; i < prob.length;i++) {
            probSum[i] = probSum[i-1]+prob[i];
        }

        double decision = Math.random();
        for(int i = 0; i < prob.length;i++) {
            if(decision < probSum[i]) {
                return i;
            }
        }
        return prob.length-1;
    }
}

class LouseRed extends Monster {
    private boolean roll;
    public final int rollShield;

    public LouseRed() {
        health = Rand.randInt(10, 15);
        maxHealth = health;
        name = "빨간 공벌레";
        dmg = Rand.randInt(5,7);
        moveSet = new int[]{75,25};
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
            int damage = dmg; //+ status.strength.power;
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1){
            Effects.addStatus(this, STATUS.STRENGTH, 3, 5);
        }
        setNextMove();
    }
}

class LouseGreen extends Monster {
    private boolean roll;
    public final int rollShield;

    public LouseGreen() {

        health = Rand.randInt(11, 17);
        name = "초록 공벌레";
        dmg = Rand.randInt(5,7);
        moveSet = new int[]{75,25};
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
            int damage = dmg;// + status.strength.power;
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1){
            Effects.addStatus(Player.getInstance(), STATUS.FEAR, 3, 2);
        }
        setNextMove();
    }
}


/* 사이즈와 타입 전부 개별 클래스로 만들 것, 그게 편함
class Slime extends Enemies {
    // 슬라임, 사이즈(소,중,대), 타입(가시,산)

    private int size; // 1~3, higher is bigger
    private int type; // 0: spike slime, 1: acid slime
    
    public Slime(int size, int type) {
        this.size = size;
        this.type = type;
        if (type == 0) {
            name = "가시 슬라임";
            switch (size) {
                case 1:
                    maxHealth = Rand.randInt(10, 14);
                    dmg = 5;
                    break;
                case 2:
                    maxHealth = Rand.randInt(28, 32);
                    dmg = 8;
                    break;
                case 3:
                    maxHealth = Rand.randInt(64, 70);
                    dmg = 16;
                    break;
            }
        } else if (type == 1) {
            name = "산성 슬라임";
            switch (size) {
                case 1:
                    maxHealth = Rand.randInt(8, 12);
                    dmg = 3;
                    break;
                case 2:
                    maxHealth = Rand.randInt(28, 32);
                    dmg = 7;
                    break;
                case 3:
                    maxHealth = Rand.randInt(65, 69);
                    dmg = 11;
                    break;
            }
        }
        health = maxHealth;
        moveSet = new int[]{30,40,30};
    }

    public int getSize() {
        // 필요할수도 있으니 만들어놓음, 안쓰면 지울것
        return size;
    }

    public void continuePattern() {
        // 데미지 계산
        int damage = dmg + status.strength.power;

        // 턴 구분
        boolean isTurnEven = Field.getInstance().currentTurn % 2 == 0;

        // 플레이어 정보
        Player playerInstance = Player.getInstance();

        if (size == 1) {
            if (type == 0 || (type == 1 && isTurnEven)) {
                // 몬스터가 가시 슬라임이거나, 산성 슬라임이면서 짝수번째 턴일때
                Effects.attack(damage, playerInstance);
            } else {
                // 약화 부여, 강도와 지속시간은 임의로 적음, 추후수정
                Effects.addStatus(playerInstance, STATUS.FEAR, 1, 3);
            }
        } else {
            if (type == 0) {
                int r = Rand.randInt(0, 1);
                if (r == 0) {
                    Effects.attack(damage, playerInstance);
                    // TODO add 1 slime card
                } else {
                    Effects.weakenShield(size == 2 ? 1 : 2);
                }
            } else {
                int r = Rand.randInt(0, 2);
                switch (r) {
                    case 0:
                        Effects.attack(damage, playerInstance);
                        // TODO add 1 slime card
                        break;
                    case 1:
                        Effects.attack(size == 2 ? 10 : 16, playerInstance);
                        break;
                    case 2:
                        Effects.addStatus(playerInstance, STATUS.FEAR, size == 2 ? 1 : 2, 3);
                        break;
                }
            }
        }
    }
}

class JawWorm extends Enemies {
    boolean strengthen;

    public JawWorm() {
        maxHealth = Rand.randInt(40, 44);
        dmg = 11;
        name = "턱벌레";
        health = maxHealth;
        strengthen = false;
    }

    public void continuePattern() {
        Player player = Player.getInstance();
        if (strengthen)
            strengthen = false;
        if (Field.getInstance().currentTurn == 0) {
            Effects.attack(dmg, player);
        } else {
            int r = Rand.randInt(0, strengthen ? 1 : 2);
            switch (r) {
                case 0:
                    Effects.attack(dmg, player);
                    break;
                case 1:
                    Effects.attack(7, player);
                    Effects.addShield(5, this);
                    break;
                case 2:
                    Effects.addStatus(this, STATUS.STRENGTH, 2, 2);
                    break;
            }
        }
    }
}
*/