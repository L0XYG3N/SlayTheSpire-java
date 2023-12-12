package Game;

import UI.Pane.BattlePane;
import UI.Pane.PlayerPane;

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
    protected static Player player = Player.getInstance();

    public abstract void use(BaseObject obj);

    public void use(BaseObject [] obj){
        for(int i = 0;i<5;i++) {
            if(obj[i] != null) {
                use(obj[i]);
            }
        }
    };

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
        player.attack(damage, obj);
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
        player.attack(damage, obj);
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
       if (player == null) {
            player = Player.getInstance();
        }
        player.attack(damage, obj);
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
       if (player == null) {
            player = Player.getInstance();
        }
        player.attack(damage, obj);
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

class BodySlam extends Card {
    private int damage;

    public BodySlam() {
        cardTarget = CardTarget.ENEMY;
        cost = 1;
        cardID = 3;
        cardName = "몸통 박치기";
        cardDescription = "현재 <font color='orange'>방어도</font> 만큼의<br>피해를 줍니다.";
        cardType = CardType.ATTACK;
        imagePath = "Img/red/attack/body_slam.png";
    }

    public void use(BaseObject obj) {
       if (player == null) {
            player = Player.getInstance();
        }
        damage = Player.getInstance().getShield();
        player.attack(damage, obj);
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


class Cleave extends Card {
    private int damage;
    private int damageOrigin;
    private int damageUpgraded;

    public Cleave() {
        cardTarget = CardTarget.ENEMYALL;
        cardType = CardType.ATTACK;
        cost = 1;
        cardID = 4;
        damageOrigin = 8;
        damage = damageOrigin;
        cardName = "절단";
        cardDescription = "적 전체에게 피해를 " + damage + " 줍니다.";
        imagePath = "Img/red/attack/cleave.png";
        isUpgrade = false;
        
        // player 필드 초기화 추가
        player = Player.getInstance();
    }

    public void use(BaseObject obj) {
       if (player == null) {
            player = Player.getInstance();
        }
        player.attack(damage, obj);  
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
        cardDescription = "적 전체에게 피해를 " + damage + " 줍니다.";
    }
}

class Clothesline extends Card {
    private int damage;
    private int damageOrigin;
    private int damageUpgraded;
   
    private int effectDuration;
    private int effectOrigin;
    private int effectUpgraded;
   
    public Clothesline() {
        cardTarget = CardTarget.ENEMY;
        cardType = CardType.ATTACK;
        cost = 2;
        cardID = 5;
        damageOrigin =12;
        damageUpgraded = 14;
        effectOrigin = 2;
        effectUpgraded = 3;
   
        damage = damageOrigin;
        effectDuration = effectOrigin;
   
        cardName = "클로스라인";
        cardDescription = "피해를 " + damage + " 줍니다.<br>" + "<font color='orange'>약화</font>를 " + effectDuration + " 부여합니다.";
        imagePath = "Img/red/attack/clothesline.png";
    }
   
    public void use(BaseObject obj) {
       if (player == null) {
              player = Player.getInstance();
          }
           player.attack(damage, obj);
           Effects.addStatus(obj, STATUS.WEAK, effectDuration);
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
       cardDescription = "피해를 " + damage + " 줍니다.<br>" + "<font color='orange'>약화</font>를 " + effectDuration + " 부여합니다.";
    }
   }
   
   class IronWave extends Card {
       private int damage;
       private int damageOrigin;
       private int damageUpgraded;

       private int defend;
       private int defendOrigin;
       private int defendUpgraded;

       public IronWave() {
           cardTarget = CardTarget.ENEMY;
           cardType = CardType.ATTACK;
           cost = 1;
           cardID = 6;
           damageOrigin = 5;
           damageUpgraded = 7;
           damage = damageOrigin;

           defendOrigin = 5;
           defendUpgraded = 7;
           defend = defendOrigin;

           cardName = "철의 파동";
           cardDescription = "<font color='orange'>방어도</font>를 " + defend + " 얻습니다. <br>" + "피해를" + damage + " 줍니다.";
           imagePath = "Img/red/attack/iron_wave.png";
       }

       public void use(BaseObject obj) {
          if (player == null) {
               player = Player.getInstance();
           }
           player.attack(damage, obj);
           Player.getInstance().addShield(defend);
           PlayerPane.getInstance().updateLabel();
       }

       public void toggleUpgrade() {
           if (isUpgrade) {
               damage = damageOrigin;
               defend = defendOrigin;
           } else {
               damage = damageUpgraded;
               defend = defendUpgraded;
           }
           isUpgrade = !isUpgrade;
       }

       public void updateDescription() {
          cardDescription = "<font color='orange'>방어도</font>를 " + defend + " 얻습니다. <br>" + "피해를" + damage + " 줍니다.";
       }
   }
   
   class Thunderclap extends Card {
       private int damage;
       private int damageOrigin;
       private int damageUpgraded;

       private int effectDuration;
       private int effectOrigin;
       private int effectUpgraded;

       public Thunderclap() {
           cardTarget = CardTarget.ENEMYALL;
           cardType = CardType.ATTACK;
           cost = 1;
           cardID = 7;
           damageOrigin = 4;
           damageUpgraded = 6;
           damage = damageOrigin;

           effectOrigin = 1;
           effectUpgraded = 2;
           effectDuration = effectOrigin;

           cardName = "천둥";
           cardDescription = "적 전체에게 피해를 " + damage + "<br>"+" 주고 <font color='orange'>취약</font>을" +effectDuration+"<br>" +" 부여합니다.";
           imagePath = "Img/red/attack/thunder_clap.png";
       }

       public void use(BaseObject enemy) {
          if (player == null) {
               player = Player.getInstance();
           }
            player.attack(damage, enemy);
            Effects.addStatus(enemy, STATUS.VULNERABLE, effectDuration);
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
          cardDescription = "적 전체에게 피해를 " + damage + "<br>"+" 주고 <font color='orange'>취약</font>을" +effectDuration+"<br>" +" 부여합니다.";
       }
   }
   
   class Hemokinesis extends Card {
       private int damage;
       private int damageOrigin;
       private int damageUpgraded;
       private int health;

       public Hemokinesis() {
           cardTarget = CardTarget.ENEMY;
           cardType = CardType.ATTACK;
           cost = 1;
           cardID = 8;
           health = 2;
           damageOrigin = 15;
           damageUpgraded = 20;
           damage = damageOrigin;
           cardName = "혈류";
           updateDescription();
           imagePath = "Img/red/attack/hemokinesis.png";
       }

       public void use(BaseObject obj) {
          if (player == null) {
               player = Player.getInstance();
           }
           Player.getInstance().loseHealth(health);
           player.attack(damage, obj);
           PlayerPane.getInstance().updateLabel();
       }

       public void toggleUpgrade() {
           if (isUpgrade) {
               damage = damageOrigin;
           } else {
               damage = damageUpgraded;
           }
           isUpgrade = !isUpgrade;
           updateDescription();
       }

       public void updateDescription() {
           cardDescription = "체력을" + health + " 잃습니다.<br>" +"피해를 " + damage + " 줍니다.";
       }
   }

   class Uppercut extends Card {
       private int damage;
       private int damageOrigin;
       private int damageUpgraded;

       private int effectDurationWeak;
       private int effectOriginWeak;
       private int effectUpgradedWeak;

       private int effectDurationVulnerable;
       private int effectOriginVulnerable;
       private int effectUpgradedVulnerable;

       public Uppercut() {
           cardTarget = CardTarget.ENEMY;
           cardType = CardType.ATTACK;
           cost = 2;
           cardID = 9;
           damageOrigin = 13;
           damageUpgraded = 16;
           effectOriginWeak = 1;
           effectUpgradedWeak = 2;
           effectOriginVulnerable = 1;
           effectUpgradedVulnerable = 2;

           damage = damageOrigin;
           effectDurationWeak = effectOriginWeak;
           effectDurationVulnerable = effectOriginVulnerable;

           cardName = "어퍼컷";
           cardDescription = "피해를 " + damage + " 줍니다. <br>" +
                   "<font color='orange'>약화</font>를 " + effectDurationWeak + ",<font color='orange'> 취약</font>을" + effectDurationVulnerable + "<br> 부여합니다.";

           imagePath = "Img/red/attack/uppercut.png";
       }

       public void use(BaseObject obj) {
          if (player == null) {
               player = Player.getInstance();
           }
           player.attack(damage, obj);
           Effects.addStatus(obj, STATUS.WEAK, effectDurationWeak);
           Effects.addStatus(obj, STATUS.VULNERABLE, effectDurationVulnerable);
       }

       public void toggleUpgrade() {
           if (isUpgrade) {
               damage = damageOrigin;
               effectDurationWeak = effectOriginWeak;
               effectDurationVulnerable = effectOriginVulnerable;
           } else {
               damage = damageUpgraded;
               effectDurationWeak = effectUpgradedWeak;
               effectDurationVulnerable = effectUpgradedVulnerable;
           }
           isUpgrade = !isUpgrade;
       }

       public void updateDescription() {
          cardDescription = "피해를 " + damage + " 줍니다. <br>" +
                   "<font color='orange'>약화</font>를 " + effectDurationWeak + ", <font color='orange'> 취약</font>을" + effectDurationVulnerable + "<br> 부여합니다.";
       }
   }
   
   class Bludgeon extends Card {
       private int damage;
       private int damageOrigin;
       private int damageUpgraded;

       public Bludgeon() {
           cardTarget = CardTarget.ENEMY;
           cardType = CardType.ATTACK;
           cost = 0;
           cardID = 10;
           damageOrigin = 32;
           damageUpgraded = 42;
           damage = damageOrigin;

           cardName = "몽둥이질";
           cardDescription = "피해를 " + damage + " 줍니다.";

           imagePath = "Img/red/attack/bludgeon.png";
       }

       public void use(BaseObject obj) {
          if (player == null) {
               player = Player.getInstance();
           }
           player.attack(damage, obj);
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
       if (player == null) {
            player = Player.getInstance();
        }
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

class Bloodletting extends Card {
    private Player player = Player.getInstance();
    private int health;
    private int mana; // 변수명 변경: cost -> mana

    public Bloodletting() {
        cardTarget = CardTarget.PLAYER;
        cardType = CardType.SKILL;
        cost = 0;
        cardID = 101;

        health = 3;
        mana = 2; // 수정된 부분: cost를 mana로 변경

        cardName = "사혈";
        updateDescription();
        imagePath = "Img/red/skill/bloodletting.png";
    }

    public void use(BaseObject obj) {
       if (player == null) {
            player = Player.getInstance();
        }
        player.loseHealth(health);
        PlayerPane.getInstance().updateLabel();
        player.addMana(mana);
        BattlePane.getInstance().updateManaPanel();
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            mana = 2;
        } else {
            mana = 3;
        }
        isUpgrade = !isUpgrade;
        updateDescription();
    }

    public void updateDescription() {
        cardDescription = "체력을 " + health + " 잃습니다.<br>" + " 에너지 " + mana + " 를 얻습니다."; // 수정된 부분: cost를 mana로 변경
    }
}

class Entrench extends Card {
    private int blockMultiplier;
    private int blockMultiplierOrigin;
    private int blockMultiplierUpgraded;

    public Entrench() {
        cardTarget = CardTarget.PLAYER;
        cardType = CardType.SKILL;
        cost = 2;
        cardID = 102;

        blockMultiplierOrigin = 2;
        blockMultiplierUpgraded = 3;
        blockMultiplier = blockMultiplierOrigin;

        cardName = "참호";
        updateDescription();
        imagePath = "Img/red/skill/entrench.png";
    }

    public void use(BaseObject obj) {
       if (player == null) {
            player = Player.getInstance();
        }
        int currentBlock = Player.getInstance().getShield();
        Player.getInstance().addShield(currentBlock * (blockMultiplier - 1));
        PlayerPane.getInstance().updateLabel();
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            blockMultiplier = blockMultiplierOrigin;
        } else {
            blockMultiplier = blockMultiplierUpgraded;
        }
        isUpgrade = !isUpgrade;
        updateDescription();
    }

    public void updateDescription() {
        cardDescription = "<font color='orange'>방어도</font>가 " + blockMultiplier + "배로 증가합니다.";
    }
}

class IntimidateCard extends Card {
    private int effectDuration;
    private int effectOrigin;
    private int effectUpgraded;

    public IntimidateCard() {
        cardTarget = CardTarget.ENEMYALL;
        cardType = CardType.SKILL;
        cost = 0; 
        cardID = 103;

        effectOrigin = 1;
        effectUpgraded = 1;
        effectDuration = effectOrigin;

        cardName = "위압";
        cardDescription = "적 전체에게 <font color='orange'>약화</font> " + effectDuration + "을 부여합니다.";

        imagePath = "Img/red/skill/intimidate.png";
    }

    public void use(BaseObject obj) {
       if (player == null) {
            player = Player.getInstance();
        }
       Effects.addStatus(obj, STATUS.WEAK, effectDuration);
    }
    
    public void toggleUpgrade() {
        if (isUpgrade) {   
            effectDuration = effectOrigin;
        } else {
            effectDuration = effectUpgraded;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "적 전체에게 <font color='orange'>약화</font> " + effectDuration + "을 부여합니다.";
    }
}

class Shockwave extends Card {
   private int effectDurationWeak;
    private int effectOriginWeak;
    private int effectUpgradedWeak;

    private int effectDurationVulnerable;
    private int effectOriginVulnerable;
    private int effectUpgradedVulnerable;

    public Shockwave() {
        cardTarget = CardTarget.ENEMYALL;
        cardType = CardType.SKILL;
        cost = 2;
        cardID = 104;
        
        effectOriginWeak = 3;
        effectUpgradedWeak = 5;
        effectOriginVulnerable = 3;
        effectUpgradedVulnerable = 5;
        
        effectDurationWeak = effectOriginWeak;
        effectDurationVulnerable = effectOriginVulnerable;

        cardName = "충격파";
        cardDescription = "적 전체에게 <font color='orange'>약화</font>" + effectDurationWeak+ "<br>"+ "<font color='orange'>취약</font> " + effectDurationVulnerable + " 부여합니다.";
        imagePath = "Img/red/skill/shockwave.png";
    }
    
    public void use(BaseObject enemy) {
       if (player == null) {
            player = Player.getInstance();
        }
        Effects.addStatus(enemy, STATUS.WEAK, effectDurationWeak);
        Effects.addStatus(enemy, STATUS.VULNERABLE, effectDurationVulnerable);
    }

    public void toggleUpgrade() {
        if (isUpgrade) {
            effectDurationWeak = effectOriginWeak;
            effectDurationVulnerable = effectOriginVulnerable;
        } else {
            effectDurationWeak = effectUpgradedWeak;
            effectDurationVulnerable = effectUpgradedVulnerable;
        }
        isUpgrade = !isUpgrade;
    }

    public void updateDescription() {
        cardDescription = "적 전체에게 <font color='orange'>약화</font>" + effectDurationWeak+ "<br>"+ "<font color='orange'>취약</font> " + effectDurationVulnerable + " 부여합니다.";
    }
}

class Barricade extends Card {
    private int defend;
    private int defendOrigin;
    private int defendUpgraded;

    public Barricade() {
        cardTarget = CardTarget.PLAYER;
        cardType = CardType.SKILL;
        cost = 3;
        cardID = 105;

        defendOrigin = 15;
        defendUpgraded = 21;
        defend = defendOrigin;

        cardName = "바리케이드";
        cardDescription = "<font color='orange'>방어도</font>를 " + defend + " 얻습니다.";

        imagePath = "Img/red/power/barricade.png";

    }

    public void use(BaseObject obj) {
       if (player == null) {
            player = Player.getInstance();
        }
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

class Combust extends Card {
    private int damage;
    private int damageOrigin;
    private int damageUpgraded;
    private int health;

    public Combust() {
        cardTarget = CardTarget.ENEMYALL;
        cardType = CardType.POWER;
        cost = 1;
        cardID = 200;
        health = 1;

        damageOrigin = 5;
        damageUpgraded = 7;
        damage = damageOrigin;

        cardName = "연소";
        cardDescription = "턴 종료 시 체력을 1 <br>" + "잃고 적 전체에게 <br>" + "피해를 " + damage + " 줍니다.";

        imagePath = "Img/red/power/combust.png";
    }

    public void use(BaseObject obj) {
       
    }
    
    public void use(BaseObject[] enemies) {
        for (BaseObject enemy : enemies) {
            if (enemy != null && enemy instanceof Monster) {
                player.attack(damage, enemy);
            }
        }
        Player.getInstance().loseHealth(1);
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
       cardDescription = "턴 종료 시 체력을" + health +"<br>" + "잃고 적 전체에게 <br>" + "피해를 " + damage + " 줍니다.";
    }
}