package blackjack.domain.card;

import blackjack.exception.BlackJackException;
import java.util.Collections;
import java.util.Stack;

public class CardDeck {

    public static final String EXTERMINATION_MESSAGE = "[ERROR] 카드를 모두 소진했습니다.";
    private final Stack<Card> deck = new Stack<>();

    public CardDeck() {
        generateCardWithSymbol();
        Collections.shuffle(this.deck);
    }

    private void generateCardWithSymbol() {
        for (SymbolCandidate symbolCandidate : SymbolCandidate.values()) {
            CardSymbol cardSymbol = CardSymbol.from(symbolCandidate.getSymbol());
            generateCardWithNumber(cardSymbol);
        }
    }

    private void generateCardWithNumber(CardSymbol cardSymbol) {
        for (NumberCandidate numberCandidate : NumberCandidate.values()) {
            CardNumber cardNumber = CardNumber.from(numberCandidate.getNumber());
            deck.add(new Card(cardNumber, cardSymbol));
        }
    }

    public synchronized UserDeck generateUserDeck() {
        UserDeck userDeck = new UserDeck();
        userDeck.add(this.draw());
        userDeck.add(this.draw());
        return userDeck;
    }

    public synchronized Card draw() {
        if (deck.isEmpty()) {
            throw new BlackJackException(EXTERMINATION_MESSAGE);
        }
        return deck.pop();
    }
}
