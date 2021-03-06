package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.UserDrawContinue;

public class User extends AbstractPlayer {
    private static final String YES = "y";
    private static final String NO = "n";
    private boolean isDrawStop = false;

    public User(String name) {
        super(name);
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }

    public boolean isDrawContinue(UserDrawContinue userDrawContinue) {
        if (userDrawContinue.isContinue()) {
            return true;
        }
        isDrawStop = true;
        return false;
    }

    public ResultType getResult(Dealer dealer) {
        int userValue = getValue();
        int dealerValue = dealer.getValue();
        if (userValue > BLACKJACK || userValue < dealerValue) {
            return ResultType.LOSS;
        }
        if (userValue == dealerValue) {
            return ResultType.DRAW;
        }
        return ResultType.WIN;
    }

    private boolean isOverBlackJack() {
        return getValue() > BLACKJACK;
    }
}
