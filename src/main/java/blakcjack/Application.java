package blakcjack;

import blakcjack.controller.BlackJackController;

public class Application {
	public static void main(String[] args) {
		final BlackJackController blackJackController = new BlackJackController();
		blackJackController.run();
	}
}