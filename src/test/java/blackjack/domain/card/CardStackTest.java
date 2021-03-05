package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardStackTest {

    @Test
    void create() {
        assertThatCode(() -> CardStack.create()).doesNotThrowAnyException();
    }

    @DisplayName("CardStack에서 2장의 카드를 반환")
    @Test
    void getTwoCards() {
        final CardStack cardStack = CardStack.create();
        assertThat(cardStack.getTwoCards().size()).isEqualTo(2);
    }

    @DisplayName("CardStack에서 1장의 카드를 반환")
    @Test
    void getSingleCard() {
        final CardStack cardStack = CardStack.create();
        assertThat(cardStack.getSingleCard()).isInstanceOf(Card.class);
    }

    @DisplayName("CardStack이 비어있지 않은 경우에는 False를 반환")
    @Test
    void isEmpty() {
        final CardStack cardStack = CardStack.create();
        assertThat(cardStack.isEmpty()).isFalse();
    }
}