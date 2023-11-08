package Game;

public class CardGetter {
    //이거 어떻게 해야할지 모르겠는데... enum 하나 만들어서 카드별로 숫자매기고 매개변수를 enum으로 받을지 아니면 그냥 숫자로 받을지..
    public static Card GetCardById(int id) {
        switch(id) {
            case 1:
                return new AttackCard();
            case 2:
                return new Strike();
            default:
                return new TestCard();
                

        }
    }
}
