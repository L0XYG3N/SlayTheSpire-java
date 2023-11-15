package Game;

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
                return new Anger();
            case 4:
                return new BodySlam();
            case 5:
                return new Clash();
            //100~200 : 스킬 카드
            case 100:
                return new Defend();

            //200~300 : 파워 카드

            //1000 이상 : 방해 카드

            default:
                return new TestCard();
        }
    }
}
