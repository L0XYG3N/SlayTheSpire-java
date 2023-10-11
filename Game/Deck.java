package Game;

import java.util.ArrayList;

public class Deck {
    // 필드, 무덤, 덱 3가지 카드의 ArrayList
    public ArrayList<Card> field;
    public ArrayList<Card> deck;
    public ArrayList<Card> dead;

    // deck에서 카드를 뽑을때 뽑는 카드의 개수, 상황에 따라 바뀔수 있음
    public int drawCount;

    public Deck() {
        field = new ArrayList<Card>();
        deck = new ArrayList<Card>();
        dead = new ArrayList<Card>();
        initializeDeck();
        drawCount = 5;
    }

    private void initializeDeck() {
        // 초반 15개의 카드를 deck에 넣는 함수

        // 테스트용 코드, 나중에 삭제할 것
        for (int i = 0; i < 4; i++) {
            field.add(new AttackCard());
        }
        field.add(new Strike());
    }

    public void turnEnd() {
        for (Card card : field) {
            if (card.ethereal) {
                card.exhausted = true;
            }
            dead.add(card);
        }
        field.clear();

        drawCard(drawCount);
        // Player.getInstance().status.updateEffects();
        // TODO 상태이상 업데이트 함수효과 구현하기
    }

    public void drawCard(int cardCount) {
        // 카드를 cardCount만큼 deck에서 뽑는 함수, deck이 비어있으면 refillDeck 함수를 실행
        for (int i = 0; i < cardCount; i++) {
            if (deck.isEmpty()) {
                refillDeck();
            }
            Card randCard = deck.remove((int) Math.random() * deck.size());
            field.add(randCard);

        }
    }

    public void refillDeck() {
        // 무덤에 있는 카드를 하나씩 deck으로 옮기는 함수, 카드가 소멸 상태이면 건너뛴다.
        for (Card card : dead) {
            if (!card.exhausted)
                deck.add(card);
        }
    }
}
