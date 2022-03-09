package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int INIT_DISTRIBUTE_SIZE = 2;
    private static final int ADD_CARD_CONDITION = 16;
    private static final String DELIMITER = ", ";
    private static final String BASIC_DISTRIBUTE = "딜러와 %s에게 " + INIT_DISTRIBUTE_SIZE + "을 나누어주었습니다.";

    public static void printPlayersInitCardInfo(final Players players) {
        final List<Participant> participants = players.getParticipants();
        final Dealer dealer = players.getDealer();

        System.out.println();
        System.out.printf((BASIC_DISTRIBUTE) + "%n", String.join(DELIMITER, extractNames(participants)));
        printInitDealerInfo(dealer);
        printParticipantsInfo(participants);
    }

    private static List<String> extractNames(final List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    private static void printInitDealerInfo(final Dealer dealer) {
        Card dealerCard = dealer.getCardOne();
        System.out.println("딜러: " + dealerCard.getScore().getSymbol() + dealerCard.getType().getName());
    }

    private static void printParticipantsInfo(final List<Participant> participants) {
        participants.forEach(OutputView::printParticipantCards);
        System.out.println();
    }

    public static void printParticipantCards(final Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        System.out.println(String.join(DELIMITER, convertToText(participant.getCards())));
    }

    private static List<String> convertToText(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getScore().getSymbol() + card.getType().getName())
                .collect(Collectors.toList());
    }

    public static void printParticipantOverMaxScore(final String name) {
        System.out.println(name + "은 최고점수를 초과하여 카드를 더 이상 받을 수 없습니다.");
    }

    public static void printDealerAcceptCard() {
        System.out.println("딜러는 " + ADD_CARD_CONDITION + "이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printDealerDenyCard() {
        System.out.println("딜러는 " + ADD_CARD_CONDITION + "을 초과하여 스탠드 하였습니다.");
        System.out.println();
    }

    public static void printFinishDealerInfo(Dealer dealer) {
        System.out.print("딜러 카드: ");
        System.out.print(String.join(DELIMITER, convertToText(dealer.getCards())));
        System.out.println(" - 결과:" + dealer.calculateScore());
    }

    public static void printFinishParticipantInfo(List<Participant> participants) {
        participants.forEach(participant -> {
            System.out.print(participant.getName() + "카드: ");
            System.out.print(String.join(DELIMITER, convertToText(participant.getCards())));
            System.out.println(" - 결과:" + participant.calculateScore());
        });
        System.out.println();
    }

    public static void printResult(Players players) {
        System.out.println("## 최종 승패");
        printDealerResult(players.getDealer());
        printParticipantsResult(players.getParticipants());
    }

    private static void printDealerResult(Dealer dealer) {
        System.out.printf("딜러: %d승 %d패%n", dealer.getWinCount(), dealer.getLoseCount());
    }

    private static void printParticipantsResult(List<Participant> participants) {
        participants.forEach(OutputView::printParticipantResult);
    }

    private static void printParticipantResult(Participant participant) {
        System.out.print(participant.getName() + ": ");
        if (participant.getWinState()) {
            System.out.println("승");
            return;
        }
        System.out.println("패");
    }
}
