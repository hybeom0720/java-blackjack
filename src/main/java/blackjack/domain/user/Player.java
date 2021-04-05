package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import blackjack.domain.state.StateFactory;

public class Player extends User {

    private static final int DEALER_CALIBRATION = -1;

    private final String name;

    public Player(String name, UserDeck userDeck, Money money) {
        super(StateFactory.draw(userDeck, UserDeck.BLACK_JACK_NUMBER), money);
        this.name = name;
    }

    public OneGameResult betResult(Dealer dealer) {
        double profitRate = super.getProfitRate(dealer);
        OneGameResult gameResult = OneGameResult.getGameExpressResult(profitRate);
        calculateMoneyResult(dealer, profitRate);
        return gameResult;
    }

    private void calculateMoneyResult(Dealer dealer, double profitRate){
        int rawValue = super.getMoney().
            getValue();
        int rawResultValue = (int) profitRate * rawValue;
        super.calculateMoneyResult(rawResultValue);
        int dealerResultValue = DEALER_CALIBRATION * rawResultValue;
        dealer.calculateMoneyResult(dealerResultValue);
    }

    public String getName() {
        return name;
    }
}
