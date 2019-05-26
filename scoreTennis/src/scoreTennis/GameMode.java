package scoreTennis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMode {

	private boolean deuce;
	private boolean modeSet;
	private boolean ptsEcarts;

	public GameMode(boolean deuce, boolean modeSet, boolean ptsEcarts) {
		this.deuce = deuce;
		this.modeSet = modeSet;
		this.ptsEcarts = ptsEcarts;
	}

	public boolean isDeuce() {
		return deuce;
	}

	public void setDeuce(boolean deuce) {
		this.deuce = deuce;
	}

	public boolean isModeSet() {
		return modeSet;
	}

	public void setModeSet(boolean modeSet) {
		this.modeSet = modeSet;
	}

	public boolean isPtsEcarts() {
		return ptsEcarts;
	}

	public void setPtsEcarts(boolean ptsEcarts) {
		this.ptsEcarts = ptsEcarts;
	}

	//Le premier joueur au dessus de 40 gagne (S1 US1)
	public void victoryNormalMode(Player playerOne, Player playerTwo) {
		if (playerOne.getScore() > 40) {
			playerOne.setVictory(true);
		} else if (playerTwo.getScore() > 40) {
			playerTwo.setVictory(true);
		}
	}

	//En cas d equalite au dernier point on met un avantage ou pas si un joueur gagne le point en cours
	//on considere que le score 55 est celui de l avantage (S1 US2)
	public void putAdvantage(Player playerOne, Player playerTwo) {
		if (playerOne.getScore() == 55 && playerTwo.getScore() == 40) {
			System.out.println("\t \t ADV \t - \t \t " + playerTwo.getScore());
			playerOne.setAdvantage(true);
			playerTwo.setAdvantage(false);
		} else if (playerOne.getScore() == 40 && playerTwo.getScore() == 55) {
			System.out.println("\t \t " + playerOne.getScore() + " \t - \t \t ADV");
			playerOne.setAdvantage(false);
			playerTwo.setAdvantage(true);
		}
	}
	
	
	//Selon l user story choisie on evalue le comportement a adopter en cas de victoire pour le joueur 1
	public void resultByModeForPlayerOne(Player playerOne, Player playerTwo) {
		//Si on calcule les points sans les Sets
		if (!this.isModeSet()) {
			playerOne.setVictory(true);
		//Si on joue avec les Sets (S2 US1)
		} else if (this.isModeSet() && (playerOne.getNumberSet() == 6 && playerTwo.getNumberSet() <= 4)
				&& !this.isPtsEcarts()) {
			playerOne.setVictory(true);
		//Si on joue avec les sets mais qu il faut aller jusqu a 7 sets (S2 US1)
		} else if (this.isModeSet() && playerOne.getNumberSet() == 7 && !this.isPtsEcarts()) {
			playerOne.setVictory(true);
		//On calcule les 2 points d ecart (S2 US2)
		} else if (this.isModeSet() && this.isPtsEcarts()
				&& (playerOne.getNumberSet() - playerTwo.getNumberSet()) == 2 && playerOne.getNumberSet() >= 6
				&& playerTwo.getNumberSet() >= 6) {
			playerOne.setVictory(true);
		} else if(this.isModeSet() && this.isPtsEcarts()
				&& playerOne.getNumberSet() == 7  && playerTwo.getNumberSet() <= 5) {
			playerOne.setVictory(true);
		}
	}
	
	//Selon l user story choisie on evalue le comportement a adopter en cas de victoire pour le joueur 2
	public void resultByModeForPlayerTwo(Player playerOne, Player playerTwo) {
		//Si on calcule les points sans les Sets
		if (!this.isModeSet()) {
			playerTwo.setVictory(true);
		//Si on joue avec les Sets (S2 US1)
		} else if (this.isModeSet() && playerTwo.getNumberSet() == 6 && playerOne.getNumberSet() <= 4
				&& !this.isPtsEcarts()) {
			playerTwo.setVictory(true);
		//Si on joue avec les sets mais qu il faut aller jusqu a 7 sets (S2 US1)
		} else if (this.isModeSet() && playerTwo.getNumberSet() == 7 && !this.isPtsEcarts()) {
			playerTwo.setVictory(true);
		//On calcule les 2 points d ecart (S2 US2)
		} else if (this.isModeSet() && this.isPtsEcarts()
				&& (playerTwo.getNumberSet() - playerOne.getNumberSet()) == 2 && playerOne.getNumberSet() >= 6
				&& playerTwo.getNumberSet() >= 6) {
			playerTwo.setVictory(true);
		} else if(this.isModeSet() && this.isPtsEcarts()
				&& playerTwo.getNumberSet() == 7  && playerOne.getNumberSet() <= 5) {
			playerTwo.setVictory(true);
		}
	}
	
	//On calcule les points a chaque tour
	public void calculScore(Player playerOne, Player playerTwo, GameMode gm) {
		Scanner pointPour = new Scanner(System.in);
		int j = 0;
		while (!playerOne.isVictory() && !playerTwo.isVictory()) {
			System.out.println("Si Charlotte marque le point, tapez 1, si c'est Coralie, faites le 2");
			try {
				j = pointPour.nextInt();
				if (j == 1) {
					System.out.println("Point pour Charlotte !");
					if (playerOne.getScore() == 30) {
						playerOne.setScore(playerOne.getScore() + 10);
					} else {
						playerOne.setScore(playerOne.getScore() + 15);
					}
				} else if (j == 2) {
					System.out.println("Point pour Coralie !");
					if (playerTwo.getScore() == 30) {
						playerTwo.setScore(playerTwo.getScore() + 10);
					} else {
						playerTwo.setScore(playerTwo.getScore() + 15);
					}
				} else {
					System.out.println("Erreur : Saississez 1 ou 2");
				}
			} catch (InputMismatchException e) {
				System.out.println("Erreur : Saisissez un entier SVP.");
				pointPour.nextLine();
			}
			showScore(playerOne, playerTwo, gm);
		}
	}
	
	//On affiche le score des joueur selon le mode de jeu, et les avantages
	public void showScore(Player playerOne, Player playerTwo, GameMode gm) {

		int scorePlayerOne = playerOne.getScore();
		int scorePlayerTwo = playerTwo.getScore();
		boolean advantagePlayerOne = playerOne.isAdvantage();
		boolean advantagePlayerTwo = playerTwo.isAdvantage();

		TextUtils.scoreNow(scorePlayerOne, scorePlayerTwo);
		
		//Si on joue avec la regle de l avantage
		if (gm.isDeuce()) {
			gm.putAdvantage(playerOne, playerTwo);
			// Si un des joueurs a l avantage
			if (advantagePlayerOne && scorePlayerTwo == 40) {
				playerOne.winSet();
				playerTwo.looseSet();
				gm.resultByModeForPlayerOne(playerOne, playerTwo);
			} else if (advantagePlayerOne && scorePlayerTwo == 55) {
				playerOne.looseAdvantage();
				playerTwo.backEquality();
				System.out.println("\t \t " + playerOne.getScore() + " \t - \t \t " + playerTwo.getScore());
			} else if (advantagePlayerTwo && scorePlayerOne == 40) {
				playerTwo.winSet();
				playerOne.looseSet();
				gm.resultByModeForPlayerTwo(playerOne, playerTwo);
			} else if (advantagePlayerTwo && scorePlayerOne == 55) {
				playerTwo.looseAdvantage();
				playerOne.backEquality();
				System.out.println("\t \t " + playerOne.getScore() + " \t - \t \t " + playerTwo.getScore());
			}

			// Si personne a l avantage
			if (playerOne.getScore() == 55 && !playerOne.isAdvantage()) {
				playerOne.winSet();
				playerTwo.looseSet();
				gm.resultByModeForPlayerOne(playerOne, playerTwo);
			} else if (playerTwo.getScore() == 55 && !playerTwo.isAdvantage()) {
				playerTwo.winSet();
				playerTwo.looseSet();
				gm.resultByModeForPlayerTwo(playerOne, playerTwo);
			}
		//Sinon on joue avec le mode simple (S1 US1)
		} else if (!gm.isDeuce()) {
			gm.victoryNormalMode(playerOne, playerTwo);
		}
		if(modeSet) {
			System.out.println("Set : \t \t" + playerOne.getNumberSet() + "\t - \t \t " + playerTwo.getNumberSet());
		}
		
		TextUtils.showVictory(playerOne, playerTwo);
	}
	
}
