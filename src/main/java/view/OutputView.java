package view;

import java.util.Map;

import domain.GameParticipant;
import domain.PlayerResult;
import domain.Players;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.result.Result;

public class OutputView {

    private static final String DELIMITER = ", ";

    private static void printInitialDrawInstruction(GameParticipant participant) {
        Players players = participant.getPlayers();
        StringBuilder drawInstruction = new StringBuilder();
        String userNames = String.join(DELIMITER, players.getUserNames());
        drawInstruction.append("\n딜러와 ").append(userNames).append("에게 2장의 카드를 나누었습니다.");
        System.out.println(drawInstruction);
    }

    public static void printInitialDraw(GameParticipant participant) {
        printInitialDrawInstruction(participant);
        printDealerInitialDraw(participant.getDealer());
        printCardStatusForAllPlayers(participant.getPlayers());
    }

    private static void printDealerInitialDraw(Dealer dealer) {
        System.out.println(dealer.toStringFirstDraw());
    }

    public static void printCardStatus(Player player) {
        System.out.println(player.toString());
    }

    private static void printCardStatusForAllPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            printCardStatus(player);
        }
        System.out.println();
    }

    public static void printDealerAdditionalCard(int hitNumber) {
        for (int i = 0; i < hitNumber; i++) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
    }

    private static void printFinalScore(Participant participant) {
        System.out.println(participant.toString() + " - 결과 : " + participant.calculateScore());
    }

    public static void printFinalScoreBoard(GameParticipant gameParticipant) {
        Dealer dealer = gameParticipant.getDealer();
        Players players = gameParticipant.getPlayers();
        printFinalScore(dealer);
        for (Player player : players.getPlayers()) {
            printFinalScore(player);
        }
    }

    public static void printFinalResult(PlayerResult playerResult) {
        System.out.println("\n## 최종 승패");
        Map<String, Result> result = playerResult.getResult();
        printDealerResult(result);
        printPlayersResult(result);
    }

    private static void printDealerResult(Map<String, Result> userResultMap) {
        StringBuilder sb = new StringBuilder("딜러: ");
        int dealerWin = 0;
        int dealerDraw = 0;
        int dealerLose = 0;
        for (Result value : userResultMap.values()) {
            if (value == Result.WIN) {
                dealerLose++;
            }
            if (value == Result.DRAW) {
                dealerDraw++;
            }
            if (value == Result.LOSE) {
                dealerWin++;
            }
        }
        sb.append(dealerWin).append("승 ").append(dealerDraw).append("무 ").append(dealerLose).append("패");
        System.out.println(sb);
    }

    private static void printPlayersResult(Map<String, Result> userResultMap) {
        for (String playerName : userResultMap.keySet()) {
            System.out.println(playerName + ": " + userResultMap.get(playerName));
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}