package Game;

import java.util.Random;

import Game.Card.CardType;

public class CardGetter {
    //이거 어떻게 해야할지 모르겠는데... enum 하나 만들어서 카드별로 숫자매기고 매개변수를 enum으로 받을지 아니면 그냥 숫자로 받을지..
    public static Card GetCardById(int id) {
        switch(id) {
            //1~100 : 공격카드 (실제로 100장 넣을건 아님, 구분용)
            case 1:
                return new Strike();
            case 2:
                return new Bash();
            case 3:
            	return new BodySlam();
            case 4:
                return new Cleave();
            case 5:
                return new Clothesline();
            case 6:
                return new IronWave();
            case 7:
                return new Thunderclap();
            case 8:
            	return new Hemokinesis();
            case 9:
            	return new Uppercut();
            case 10:
            	return new Bludgeon();
            	
            //100~200 : 스킬 카드
            case 100:
                return new Defend();
            case 101:
                return new Bloodletting();
            case 102:
                return new Entrench();
            case 103:
                return new IntimidateCard();
            case 104:
                return new Shockwave();
            case 105:
                return new Barricade();
            //200~300 : 파워 카드

            case 200:
            	return new Combust();
            case 201:
            	return new Inflame();
            //1000 이상 : 방해 카드

            default:
                return new TestCard();
        }
    }

    public static Card getRandomCard(CardType type) {
        Random r = new Random();
        switch(type) {
            case ATTACK:
                return GetCardById(r.nextInt(10)+1);
            case SKILL:
                return GetCardById(r.nextInt(6)+100);
            case POWER:
                return GetCardById(200);

        }
        return new TestCard();
    }
}
