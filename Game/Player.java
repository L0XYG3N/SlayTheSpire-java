package Game;

public class Player extends BaseObject {
    // 플레이어 관련 클래스

    public Deck cards;

    public int[] potions; // 포션 인벤토리, 시간남으면 구현하기
    public int maxMana;
    private int mana;
    private int gold;
    public int weakenShield;

    // 싱글톤 기법
    private static Player instance = new Player();

    private Player() {
        potions = new int[4];
        cards = new Deck();
        maxMana = 3;
        mana = maxMana;
        maxHealth = 70;
        health = maxHealth;
        gold=0;
        status = new BuffStatus();
    }

    public static Player getInstance() {
        return instance;
    }

    public void turnEnd() {
        mana = maxMana;
        
        status.updateEffects();

        cards.turnEnd();
    }

    // public boolean useCard(int index, BaseObject obj) {
    //     // 필드에 나와있는 카드들에 각각 인덱스를 부여해 코드면에서 선택이 쉽게 할 예정
    //     Card card = cards.field.get(index);
    //     if (card.cost > mana) {
    //         // 카드의 코스트가 현재 가지고있는 마나보다 크면 false 리턴
    //         return false;
    //     }
    //     mana -= card.getCost();
    //     // 카드 사용 후 필드에서 삭제하고 무덤에 넣기, 소멸 기능 구현안됨
    //     card.use(obj);
    //     cards.field.remove(index);
    //     cards.discard.add(card);
    //     return true;
    // }

    public void usePotion(int index) {
        // 포션 사용할때 실행되는 코드인데 다른거 다하고 만들기

    }

    public void addMana(int amount) {
        // 마나 추가
        mana += amount;
    }

    public void takeMana(int amount) {
        mana -= amount;
    }

    public boolean canTakeMana(int amount) {
        return mana>=amount;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void addGold(int amount) {
        gold+=amount;
    }

    public void takeGold(int amount) {
        gold-=amount;
    }

    public int getGold() {
        return gold;
    }

    

    



}
