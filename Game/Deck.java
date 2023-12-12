package Game;

import java.util.ArrayList;
import java.util.Random;

import UI.Pane.PlayerPane;

public class Deck {
    private Random random;

    // 필드, 무덤, 덱 3가지 카드의 ArrayList
    public ArrayList<Card> field;
    public ArrayList<Card> deck;
    public ArrayList<Card> discard;

    // 가지고 있는 전체 카드 리스트
    public ArrayList<Card> cardList;

    // deck에서 카드를 뽑을때 뽑는 카드의 개수. 상황에 따라 바뀔수 있음
    public int drawCount;

    public Deck() {
        field = new ArrayList<Card>();
        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();
        cardList = new ArrayList<Card>();
        random = new Random();
        drawCount = 5;

        initialize();
        
    }

    private void initialize() {
        // 초반 15개의 카드를 deck에 넣는 함수

        // 테스트용 코드, 나중에 삭제할 것


        cardList.add(new Bash());
        cardList.add(new Strike());
        cardList.add(new Strike());
        cardList.add(new Strike());
        cardList.add(new Strike());
        cardList.add(new Strike());
        cardList.add(new Defend());
        cardList.add(new Defend());
        cardList.add(new Defend());
        cardList.add(new Defend());

        for(Card card : cardList) {
            field.add(card);
        }
    }

    public void initBattle(){
        //전투 시작시 호출되는 함수
        //세 가지 arraylist를 전부 초기화한다.
        discard.clear();
        field.clear();
        deck.clear();
        Player.getInstance().init();

        for(Card card : cardList) {
            deck.add(card);
        }

        shuffle();

        drawCard(drawCount);
        Player.getInstance().initShield(); // 이 부분을 추가
        PlayerPane.getInstance().updateLabel();
    }

    public void shuffle() {
        int n = deck.size();
        for (int i = n - 1; i > 0; i--) {	//	덱 ArrayList의 크기만큼 섞음
    		int randomIndex = random.nextInt(i + 1);
    		
    		Card temp = deck.get(i);
    		deck.set(i, deck.get(randomIndex));
    		deck.set(randomIndex, temp);
    	}
    }

    public void turnEnd() {
        for (Card card : field) {
            if (card.ethereal) {
                card.exhausted = true;
            }
            discard.add(card);
        }
        field.clear();

        drawCard(drawCount);
    }

    public void drawCard(int cardCount) {
        // 카드를 cardCount만큼 deck에서 뽑는 함수, deck이 비어있으면 refillDeck 함수를 실행
        for (int i = 0; i < cardCount; i++) {
            if (deck.isEmpty()) {
                refillDeck();
            }
            Card c = deck.remove(0);
            field.add(c);
        }

    }

    public void refillDeck() {
        // 무덤에 있는 카드를 하나씩 deck으로 옮기는 함수, 카드가 소멸 상태이면 건너뛴다.
        for (Card card : discard) {
            if (!card.exhausted)
                deck.add(card);
        }
        discard.clear();
        shuffle();
    }
}
