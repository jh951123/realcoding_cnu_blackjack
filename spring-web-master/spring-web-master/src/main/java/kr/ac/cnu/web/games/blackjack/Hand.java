package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();
    private boolean isDouble = false;

    public void setIsDouble(){
        this.isDouble = true;
    }
    public boolean getIsDouble(){
        return this.isDouble;
    }

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        for(int i = 0; i < cardList.size(); i++){       //ace를 1,11중에 유리한 것으로 선택
            if(cardList.get(i).getRank() == 1){
                if(cardList.stream().mapToInt(card -> card.getRank()).sum() + 10 <= 21){
                    return cardList.stream().mapToInt(card -> card.getRank()).sum() + 10;
                }
            }
        }
        return cardList.stream().mapToInt(card -> card.getRank()).sum();
    }

    public void reset() {
        cardList.clear();
    }
    public int handSize(){
        return cardList.size();
    }

}
