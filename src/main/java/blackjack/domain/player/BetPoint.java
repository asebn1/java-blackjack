package blackjack.domain.player;

public enum BetPoint {

    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    BOTH_BLACKJACK(0.0);

    private final double ratio;

    BetPoint(double ratio) {
        this.ratio = ratio;
    }

    public static int win(int betAmount) {
        return (int) (betAmount * WIN.getRatio());
    }

    public static int lose(int betAmount) {
        return (int) (betAmount * LOSE.getRatio());
    }

    public static int bothBlackjack(int betAmount) {
        return (int) (betAmount * BOTH_BLACKJACK.getRatio());
    }

    public static int blackjack(int betAmount) {
        return (int) (betAmount * BLACKJACK.getRatio());
    }

    public double getRatio() {
        return ratio;
    }
}
