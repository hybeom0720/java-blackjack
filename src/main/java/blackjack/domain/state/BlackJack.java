package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class BlackJack extends Terminant{

    public static double BLACK_JACK_RATE = 1.5;

    public BlackJack(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public double getProfitRate(State dealerState) {
        if (dealerState.isBlackJack()) {
            return ZERO_RATE;
        }
        return BLACK_JACK_RATE;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }
}
