package blackjack.domain.player;

import java.util.List;

public final class Bet {

    private final int betAmount;
    private int profit;

    public Bet(final int betAmount) {
        this.betAmount = betAmount;
        this.profit = 0;
    }

    public void win() {
        this.profit = BetPoint.win(betAmount);
    }

    public void lose() {
        this.profit = BetPoint.lose(betAmount);
    }

    public void bothBlackjack() {
        this.profit = BetPoint.bothBlackjack(betAmount);
    }

    public void blackjack() {
        this.profit = BetPoint.blackjack(betAmount);
    }

    public void calculateFinalProfit(final List<Integer> profits) {
        for (Integer profit : profits) {
            this.profit -= profit;
        }
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betAmount=" + betAmount +
                ", profit=" + profit +
                '}';
    }
}
