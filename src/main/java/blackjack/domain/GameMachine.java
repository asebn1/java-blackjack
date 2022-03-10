package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class GameMachine {

    private final Deck deck;
    private final Players players;

    public GameMachine(List<String> names) {
        validationNames(names);
        this.deck = new Deck();

        this.players = new Players(createParticipants(names), createDealer());
    }

    private void validationNames(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참여자의 이름을 입력해주세요.");
        }
    }

    public List<Player> createParticipants(final List<String> names) {
        return names.stream()
                .map(name -> new Participant(deck.initDistributeCard(), name))
                .collect(Collectors.toList());
    }

    private Dealer createDealer() {
        return new Dealer(deck.initDistributeCard());
    }

    public Card playDraw() {
        return deck.draw();
    }

    public Players getPlayers() {
        return this.players;
    }

    public boolean isDealerGetCard() {
        if (((Dealer) players.getDealer()).acceptableCard()) {
            players.addDealerCard(deck.draw());
            return true;
        }
        return false;
    }
}