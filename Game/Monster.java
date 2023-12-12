package Game;

import UI.Pane.BattlePane;
import UI.Pane.MonsterPane;

public abstract class Monster extends BaseObject {

    public Field field;
    public MonsterPane monsterpane;
      public BattlePane battlepane;
    // 몬스터용 추상 클래스
    public String name;
    
    public String imagePath; // 몬스터 이미지를 나타내기 위해 추가 - 승훈
    
    public String intentionText;  // 의도 텍스트를 저장하는 변수
    
    public int nextMove;

    public int Thieverynum = 0;

    public int[] moveSet;
    int dmg;
    int dfd;

    public void initTurn() {
        Field.getInstance().currentTurn = 1;
        for(int i = 0; i < 5;i++) {
             if(BattlePane.monsters[i] != null) {
                 BattlePane.monsters[i].updateLabel();
             }
         }
     }
     
     public void turnPlus() {
        Field.getInstance().currentTurn++;
        for(int i = 0; i < 5;i++) {
             if(BattlePane.monsters[i] != null) {
                 BattlePane.monsters[i].updateLabel();
             }
         }
     }
    
    public String getIntentionText() {
        return intentionText;
    }

    public void setNextMove() {
        nextMove = Rand.getNumByProbability(moveSet);

    }

    public void setIntentionText(String intentionText) {
       this.intentionText = intentionText;
    }
    
    public void continuePattern() {
       if(status.regeneration.stack > 0) {
          addHealth(status.regeneration.stack);
       }
       if(status.steelArmor.stack > 0) {
          addShield(status.steelArmor.stack);
       }
    }

    public String getName() {
        return name;
    }
    @Override
    public String getImagePath() { // BaseObject 의 getImagePath 메서드를 오버라이드 하여 이미지 전달 추가 - 승훈
        // 각 몬스터에 대한 실제 이미지 경로를 제공합니다
        return imagePath;
    }
    
    
    public int getCalculatedDamage() {
       int damage = dmg;
       damage += status.strength.stack;
       if(status.weak.stack > 0) {
          damage = (int)(damage * 0.75);
       }
       return damage;
    }
    
    public void monsterIntention() {
        System.out.println(getIntentionText());
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

class LouseRed extends Monster {   // 빨간 공벌레
   private boolean roll;
    public final int rollShield;

    public LouseRed() {
        health = Rand.randInt(10, 15);
        maxHealth = health;
        name = "빨간 공벌레";
        moveSet = new int[] { 75, 25 };
        dmg = Rand.randInt(5, 7);
        status = new BuffStatus();
        rollShield = Rand.randInt(3, 7);
        initTurn();
        setNextMove();
        monsterIntention();
        imagePath = "resource/LouseRed.png"; // 이미지 경로 추가 - 승훈
        
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
    	initShield();
        if (nextMove == 0) {
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
            
        } else if (nextMove == 1) {
            Effects.addStatus(this, STATUS.STRENGTH, 3);
        }
        dmg = Rand.randInt(5, 7);
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "빨간 공벌레는 " + getCalculatedDamage() + " 의 공격을 하려 합니다.";
        } else if (nextMove == 1) {
            return "빨간 공벌레는 3의 힘증가 버프를 사용하려 합니다.";
        }
        return "???";
    }

}

class LouseGreen extends Monster {   // 초록 공벌레
   private boolean roll;
    public final int rollShield;

    public LouseGreen() {

        health = Rand.randInt(11, 17);
        name = "초록 공벌레";
        moveSet = new int[] { 75, 25 };
        dmg = Rand.randInt(5, 7);
        status = new BuffStatus();
        maxHealth = health;
        rollShield = Rand.randInt(3, 7);
        initTurn();
        setNextMove();
        imagePath = "resource/LouseGreen.png"; // 이미지 경로 추가 - 승훈
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
       initShield();
        if (nextMove == 0) {
           
           int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
            Effects.addStatus(Player.getInstance(), STATUS.WEAK, 3);
        }
        dmg = Rand.randInt(5, 7);
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "초록 공벌레는 " + getCalculatedDamage() + " 의 공격을 하려 합니다.";
        } else if (nextMove == 1) {
            return "빨간 공벌레는 당신에게 약화 3 을 걸려 합니다.";
        }
        return "???";
    }

}


class SpikeSlimeS extends Monster {   // 가시 슬라임 소
   public SpikeSlimeS() {
        health = Rand.randInt(10, 14);
        name = "가시 슬라임(소)";
        moveSet = new int[] { 100 };
        dmg = 5;
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/SpikeSlimeS.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }

    public void continuePattern() {
       initShield();
        if (nextMove == 0) {
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        }
        dmg = 5;
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "가시 슬라임(소)는 " + getCalculatedDamage() + "의 데미지를 주려 합니다.";
        }
        return "???";
    }

}

class SpikeSlimeM extends Monster {
   public SpikeSlimeM() {
        health = Rand.randInt(28, 32);
        name = "가시 슬라임(중)";
        moveSet = new int[] { 50, 50 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/SpikeSlimeM.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public static void Slimed(int pcs) {   // *점액투성이 카드 버린카드더미에 추가
       System.out.println("점액투성이 카드 " + pcs + "개 추가 ㅋ");
    }

    public void continuePattern() {
       initShield();
        if (nextMove == 0) {
            dmg = 8;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
            Slimed(1);
        } else if (nextMove == 1) {
            Effects.weakenShield(1);
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "가시슬라임(중)은 8의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "가시슬라임(중)은 당신에게 손상 디버프(1)을 주려 합니다.";
        }
        return "???";
    }

}

class SpikeSlimeL extends Monster {
   boolean splitbool = false;
    public SpikeSlimeL() {
        health = Rand.randInt(64, 70);
        name = "가시 슬라임(대)";
        moveSet = new int[] { 50, 50 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/SpikeSlimeL.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public static void Slimed(int pcs) {   // *점액투성이 카드 버린카드더미에 추가
       System.out.println("점액투성이 카드 " + pcs + "개 추가 ㅋ");
    }
    
    public void split() {   // 분열
       health = 0;
       for(int i = 0; i < 5;i++) {
            if(BattlePane.monsters[i] != null) {
                BattlePane.monsters[i].splitupdateLabel();
            }
        }
       BattlePane.getInstance().splitSlime();
    }
    
    public void continuePattern() {   
       initShield();
       if (nextMove == 0) {
          if (health <= maxHealth / 2) {
             split();
          }
          else {
             dmg = 16;
             int damage = getCalculatedDamage();
             Effects.attack(damage, Player.getInstance());
             Slimed(2);
          }
       } else if (nextMove == 1) {
          if (health <= maxHealth / 2) {
             split();
             }
             else {
                Effects.weakenShield(2);
             }
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
        
    }

    public String getIntentionText() {
        if (nextMove == 0) {
            if (health <= maxHealth / 2) {
               return "분열하려 합니다!";
            }
            else {
               return "가시슬라임(대) 는 16의 데미지를 주려 합니다.";
            }
        } else if (nextMove == 1) {
           if (health <= maxHealth / 2) {
               return "분열하려 합니다!";
           }
            else { 
               return "가시슬라임(대) 는 당신에게 손상 디버프(2)를 주려 합니다.";
            }
        }
        return "???";
    }

}

class AcidSlimeS extends Monster {   // 산성 슬라임
   public AcidSlimeS() {
        health = Rand.randInt(8, 12);
        name = "산성 슬라임(소)";
        moveSet = new int[] { 50, 50 };
        status = new BuffStatus();
        maxHealth = health;
       initTurn();
        imagePath = "resource/AcidSlimeS.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }

    public void continuePattern() {
       initShield();
        if (nextMove == 0) {
            dmg = 3;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
           Effects.addStatus(Player.getInstance(), STATUS.WEAK, 1);
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "산성슬라임(소)은 3의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "산성슬라임(소)은 당신에게 약화 디버프(1)을 주려 합니다.";
        }
        return "???";
    }
}

class AcidSlimeM extends Monster {
   public AcidSlimeM() {
        health = Rand.randInt(28, 32);
        name = "산성 슬라임(중)";
        moveSet = new int[] { 30, 40, 30};
        status = new BuffStatus();
        maxHealth = health;
       initTurn();
        imagePath = "resource/AcidSlimeM.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public static void Slimed(int pcs) {   // *점액투성이 카드 버린카드더미에 추가
       System.out.println("점액투성이 카드 " + pcs + "개 추가 ㅋ");
    }

    public void continuePattern() {
       initShield();
        if (nextMove == 0) {
            dmg = 7;
            int damage = dmg;
            Effects.attack(damage, Player.getInstance());
            Slimed(1);
        } else if (nextMove == 1) {
           dmg = 10;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 2) {
           Effects.addStatus(Player.getInstance(), STATUS.WEAK, 1);
        }
        super.continuePattern();
        setNextMove();
       turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "산성슬라임(중)은 7의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "산성슬라임(중)은 10의 피해를 주려 합니다.";
        } else if (nextMove == 2) {
            return "산성슬라임(중)은 약화(1) 디버프를 주려 합니다.";
        }
        return "???";
    }
}

class AcidSlimeL extends Monster {
   public AcidSlimeL() {
        health = Rand.randInt(65, 69);
        name = "산성 슬라임(대)";
        moveSet = new int[] { 30, 40, 30};
        status = new BuffStatus();
        maxHealth = health;
       initTurn();
        imagePath = "resource/AcidSlimeL.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public static void Slimed(int pcs) {   // *점액투성이 카드 버린카드더미에 추가
       System.out.println("점액투성이 카드 " + pcs + "개 추가 ㅋ");
    }
    
    public void split() {   // 분열
       health = 0;
       for(int i = 0; i < 5;i++) {
            if(BattlePane.monsters[i] != null) {
                BattlePane.monsters[i].splitupdateLabel();
            }
        }
       BattlePane.getInstance().AcidSlime();
    }
    

    public void continuePattern() {
       initShield();
        if (nextMove == 0) {
           if (health <= maxHealth / 2) {
              split();
           }
           else {
              dmg = 11;
                int damage = getCalculatedDamage();
                Effects.attack(damage, Player.getInstance());
                Slimed(2);
           }
        } else if (nextMove == 1) {
           if (health <= maxHealth / 2) {
              split();
           }
           else {
              dmg = 16;
                int damage = getCalculatedDamage();
                Effects.attack(damage, Player.getInstance());
           }
        } else if (nextMove == 2) {
           if (health <= maxHealth / 2) {
              split();
           }
           else {
              Effects.addStatus(Player.getInstance(), STATUS.WEAK, 2);
           }
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "산성슬라임(대)은 11의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "산성슬라임(대)은 16의 피해를 주려 합니다.";
        } else if (nextMove == 2) {
            return "산성슬라임(대)은 약화(1) 디버프를 주려 합니다.";
        }
        return "???";
    }
}

class Looter extends Monster {   // 도적
   public Looter() {
        health = Rand.randInt(44, 48);
        name = "도적";
        moveSet = new int[] { 50, 50 };
        status = new BuffStatus();
        maxHealth = health;
        imagePath = "resource/Looter.png"; // 이미지 경로 추가 - 승훈
        initTurn();
        setNextMove();
    }
    
    public void Thievery() {   // 골드 강탈
       Player.getInstance().Thievery();
       System.out.println("15원 들고감 ㅋ");
       Thieverynum++;
    }

    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
          if (Field.getInstance().currentTurn <= 2) {
                dmg = 11;
                int damage = getCalculatedDamage();
                Effects.attack(damage, Player.getInstance());
                Thievery();
            }
          else {
             dfd = 6;
            int defend = dfd;
            addShield(defend);
            for(int i = 0; i < 5;i++) {
                if(BattlePane.monsters[i] != null) {
                    BattlePane.monsters[i].updateLabel();
                }
            }
          }
          
        } else if (nextMove == 1) {
           if (Field.getInstance().currentTurn <= 2) {
                dmg = 11;
                int damage = getCalculatedDamage();
                Effects.attack(damage, Player.getInstance());
                Thievery();
            }
           else {
              dmg = 12;
             int damage = getCalculatedDamage();
             Effects.attack(damage, Player.getInstance());
             Thievery();
           }
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
           if (Field.getInstance().currentTurn <= 2) {
                return "도적은 11의 피해를 입히려 합니다.";
            }
          else {
             return "도적은 6의 방어도를 얻으려 합니다.";
          }

        } else if (nextMove == 1) {
           if (Field.getInstance().currentTurn <= 2) {
                return "도적은 11의 피해를 입히려 합니다.";
            }
           else {
               return "도적은 12의 피해를 입히고 도망칠 준비를 합니다!";
           }
        }
        return "???";
    }
}

class JawWorm extends Monster {   // 턱벌레
   public JawWorm() {
        health = Rand.randInt(40, 44);
        name = "턱벌레";
        moveSet = new int[] { 45, 30, 25};
        status = new BuffStatus();
        maxHealth = health;
       initTurn();
        imagePath = "resource/JawWorm.png"; // 이미지 경로 추가 - 승훈
        setNextMove();        
    }
    
    
    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
           dmg = 11;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
           dmg = 7;
            int damage = getCalculatedDamage();
           dfd = 5;
           int defend = dfd;
           Effects.attack(damage, Player.getInstance());
           addShield(defend);
        } else if(nextMove == 2) {
           dfd = 3;
           int defend = dfd;
           addShield(defend);
           Effects.addStatus(this, STATUS.STRENGTH, 3);
        }
          super.continuePattern();
          setNextMove();
          turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "턱벌레는 11의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "턱벌레는 7의 피해를 주고 5의 방어도를 얻으려 합니다.";
        } else if (nextMove == 2) {
            return "턱벌레는 3의 방어도를 얻고 3의 힘을 증가시키려 합니다.";
        }
        return "???";
    }
}

class BlueSlaver extends Monster {   // 파랑 노예상인
   public BlueSlaver() {
        health = Rand.randInt(46, 50);
        name = "노예 상인 (파랑)";
        moveSet = new int[] { 60, 40 };
        status = new BuffStatus();
        maxHealth = health;
       initTurn();
        imagePath = "resource/BlueSlaver.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
          dmg = 12;
           int damage = getCalculatedDamage();
           Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
           dmg = 7;   
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
            Effects.addStatus(Player.getInstance(), STATUS.WEAK, 1);
           
        }
          super.continuePattern();
          setNextMove();
          turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "파랑 노예상인은 12의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "파랑 노예상인은 7의 피해를 주며 당신에게 약화(1)의 디버프를 걸려 합니다.";
        } 
        return "???";
    }
}

class RedSlaver extends Monster { // 빨강 노예상인
   public RedSlaver() {
        health = Rand.randInt(46, 50);
        name = "노예 상인 (빨강)";
        moveSet = new int[] { 60, 40};
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/RedSlaver.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
          dmg = 13;
           int damage = getCalculatedDamage();
           Effects.attack(damage, Player.getInstance());
       }
       else if (nextMove == 1) {
          if(Field.getInstance().currentTurn == 1) {
             dmg = 13;
                int damage = getCalculatedDamage();
                Effects.attack(damage, Player.getInstance());
          }
          else {
             dmg = 8;   
                int damage = getCalculatedDamage();
                Effects.attack(damage, Player.getInstance());
                Effects.addStatus(Player.getInstance(), STATUS.VULNERABLE, 1);
          }
       }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
           if (nextMove == 0) {
                return "빨강 노예상인은 13의 피해를 입히려 합니다.";
           } 
           
           else if (nextMove == 1) {
              if (Field.getInstance().currentTurn == 1) {
                  return "빨강 노예상인은 13의 피해를 입히려 합니다.";
              }
              else {
                  return "빨강 노예사인은 8의 피해를 입히고 취약(1)의 디버프를 걸려 합니다!";
              }
           }
           
           else return "???";
    }
}

class Cultist extends Monster {   // 광신자
   public Cultist() {
        health = Rand.randInt(48, 54);
        name = "광신자";
        moveSet = new int[] { 100 };
        dmg = 3;
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/Cultist.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public static void Ritual() {   // *의식
       System.out.println("1턴마다 공격력 올라감 ㅋ");
    }
    
    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
          if (Field.getInstance().currentTurn == 1) {
             Ritual();
          }
          else {
             Effects.addStatus(this, STATUS.STRENGTH, 3);
             int damage = getCalculatedDamage();
             Effects.attack(damage, Player.getInstance());
          }
       }
       dmg = 3;
       super.continuePattern();
       setNextMove();
       turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
           if (Field.getInstance().currentTurn == 1) {
               return "광신자가 의식(매 턴마다 힘3 증가) 버프를 사용하려 합니다!";
            }
            else {
                return "힘이 " + 3 + "만큼 증가된" + (getCalculatedDamage() + 3) + " 피해를 주려고 합니다."; 
             }
        }
        else return "???";
    }
    
}

class FungiBeast extends Monster {   // 동물하초
   public FungiBeast() {
        health = Rand.randInt(22, 28);
        name = "동물하초";
        moveSet = new int[] { 60, 40 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/FungiBeast.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
         dmg = 6;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
           Effects.addStatus(this, STATUS.STRENGTH, 3);
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "동물하초는 6의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "동물하초는 힘(3) 의 버프를 사용하려 합니다.";
        }
        return "???";
    }
}

class ShieldGremlin extends Monster {   // 방패 그렘린
   public ShieldGremlin() {
        health = Rand.randInt(12, 15);
        name = "방패 그렘린";
        moveSet = new int[] { 50, 50 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/ShieldGremlin.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public void continuePattern() {
       initShield();
       if (nextMove == 0) {
         dmg = 6;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
        } else if (nextMove == 1) {
           dfd = 7;
           int defend = dfd;
           addShield(defend);
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        if (nextMove == 0) {
            return "방패 그램린은 6의 피해를 주려 합니다.";
        } else if (nextMove == 1) {
            return "방패 그램린은 방어도 7을 얻으려 합니다.";
        }
        return "???";
    }
}

class GremlinWizard extends Monster {   // 마법사 그렘린
   public GremlinWizard() {
        health = Rand.randInt(23, 25);
        name = "마법사 그렘린";
        moveSet = new int[] { 100 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/gremlinWizard.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
        turnPlus();
    }
    
    public static void Charging() {   // 차징
       System.out.println("차징중");
    }
    
    public void continuePattern() {
       initShield();
       if (Field.getInstance().currentTurn <= 3) {
          Charging();
        } else if (Field.getInstance().currentTurn == 4) {
           dmg = 25;
            int damage = getCalculatedDamage();
            Effects.attack(damage, Player.getInstance());
            initTurn();
        }
        super.continuePattern();
        setNextMove();
        turnPlus();
        
    }
    
    public String getIntentionText() {
       if (Field.getInstance().currentTurn <= 3) {
            return "마법사 그램린은 강력한 공격을 차징중입니다!";
       } else if (Field.getInstance().currentTurn == 4) {
    	   initTurn();
           return "마법사 그램린은 25의 데미지를 주려합니다!!";
        }
        return "???";
    }
}

class MadGremlin extends Monster {   // 화난 그렘린
    public MadGremlin() {
       
        health = Rand.randInt(20, 24);
        name = "화난 그렘린";
        moveSet = new int[] { 100 };
        dmg = 4;
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/MadGremlin.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public void damage(int amount) { // 데미지를 받을시 힘 1증가
        super.damage(amount);
        Effects.addStatus(this, STATUS.STRENGTH, 1);
    }
        
    
    public void continuePattern() {
       initShield();
        int damage = getCalculatedDamage();
        Effects.attack(damage, Player.getInstance());
        dmg = 4;
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
       return "화난 그램린은 " + getCalculatedDamage() + "의 데미지를 주려 합니다.";
    }
}

class FatGremlin extends Monster {   // 뚱뚱한 그렘린
   public FatGremlin() {
        health = Rand.randInt(13, 17);
        name = "뚱뚱한 그렘린";
        moveSet = new int[] { 100 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/FatGremlin.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
    }
    
    public void continuePattern() {
       initShield();
       dmg = 4;
        int damage = getCalculatedDamage();
        Effects.attack(damage, Player.getInstance());
        Effects.addStatus(Player.getInstance(), STATUS.WEAK, 1);
        super.continuePattern();
        setNextMove();
    }
    
    public String getIntentionText() {
            return "뚱뚱한 그램린은 4의 피해를 주며 약화(1)의 디버프를 걸려 합니다.";
    }
}

class SneakyGremlin extends Monster {   // 교활한 그렘린
   public SneakyGremlin() {
        health = Rand.randInt(10, 14);
        name = "교활한 그렘린";
        moveSet = new int[] { 100 };
        status = new BuffStatus();
        maxHealth = health;
        initTurn();
        imagePath = "resource/SneakyGremlin.png"; // 이미지 경로 추가 - 승훈
        setNextMove();
        turnPlus();
    }
    
    public void continuePattern() {
       initShield();
       dmg = 9;
        int damage = getCalculatedDamage();
        Effects.attack(damage, Player.getInstance());
        super.continuePattern();
        setNextMove();
        turnPlus();
    }
    
    public String getIntentionText() {
        return "교활한 그램린은 9의 피해를 주려 합니다.";
    }
}

class TestMonster extends Monster {   // 교활한 그렘린
	   public TestMonster() {
	        health = 50;
	        name = "교활한 그렘린";
	        moveSet = new int[] { 100 };
	        status = new BuffStatus();
	        maxHealth = health;
	        initTurn();
	        setNextMove();
	        turnPlus();
	    }
	    
	    public void continuePattern() {
	       initShield();
	       dmg = 10;
	        int damage = getCalculatedDamage();
	        Effects.attack(damage, Player.getInstance());
	        Effects.addStatus(Player.getInstance(), STATUS.WEAK, 1);
	        super.continuePattern();
	        setNextMove();
	        turnPlus();
	    }
	    
	    public String getIntentionText() {
	        return "약화를 걸려고 합니다";
	    }
	}