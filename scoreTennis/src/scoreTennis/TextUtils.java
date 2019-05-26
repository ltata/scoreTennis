package scoreTennis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUtils {
	//Affiche le text d intro
	static void intro() {
		System.out.println("C'EST L'HEURE DU DUEL !");
		System.out.println("Charlotte et Coralie vont s'affronter pour la finale de Roland Garros !");
		System.out.println("Qui remportera la victoire ?");
		System.out.println("A vous de calculer les points de ce match ! \n");
		System.out.println("Quel User Story voulez-vous verifier ? (Saisir le chiffre de 1 à 4) \n");

		System.out.println("Sprint 1");
		System.out.println("\t 1 - US 1");
		System.out.println("\t 2 - US 2 \n");

		System.out.println("Sprint 2");
		System.out.println("\t 3 - US 1");
		System.out.println("\t 4 - US 2");
	}
	
	//Affiche le score actuel
	static void scoreNow(int scorePlayerOne, int scorePlayerTwo) {
		System.out.println("Score actuel :");
		System.out.println("\t Charlotte \t - \t   Coralie");
		if (scorePlayerOne <= 40 && scorePlayerTwo <= 40) {
			System.out.println("\t \t " + scorePlayerOne + " \t - \t \t " + scorePlayerTwo);
		}
	}

	//Affiche le joueur qui gagne et perd le jeu
	static void showVictory(Player playerOne, Player playerTwo) {
		if (playerOne.isVictory()) {
			System.out.println("\t \t Win game \t - \t \t Loose Game" );
		} else if (playerTwo.isVictory()) {
			System.out.println("\t \t Loose Game \t - \t \t Win Game");
		}
	}
	
	//Choix de l user story que l on veut voir
	static int choixSprint() {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while (true) {
			System.out.println("Saisissez un entier entre 1 et 4 : ");
			try {
				i = sc.nextInt();
				if (i >= 1 && i <= 4) {
					return i;
				} else {
					System.out.println("Erreur : Saisissez un entier compris entre 1 et 4");
				}
			} catch (InputMismatchException e) {
				System.out.println("Erreur : Saisissez un entier SVP.");
				sc.nextLine();
			}
		}
	}

}
