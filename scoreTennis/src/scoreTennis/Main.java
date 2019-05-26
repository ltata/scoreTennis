package scoreTennis;

public class Main {

	public static void main(String[] args) {
		
		Player scorePlayerOne = new Player();
		Player scorePlayerTwo = new Player();
		
		boolean deuce = false;
		boolean modeSet = false;
		boolean ptsEcarts = false;
		
		GameMode gm = new GameMode(deuce, modeSet, ptsEcarts);

		TextUtils.intro();
		int i = TextUtils.choixSprint();
		
		switch (i) {
        case 1:
			System.out.println("Vous avez choisis le Sprint 1 - US1");
			gm.calculScore(scorePlayerOne, scorePlayerTwo, gm);
			break;
        case 2 :
        	System.out.println("Vous avez choisis le Sprint 1 - US2");
        	gm.setDeuce(true);
        	gm.calculScore(scorePlayerOne, scorePlayerTwo, gm);
        	break;
        case 3 :
        	System.out.println("Vous avez choisis le Sprint 2 - US1");
        	gm.setDeuce(true);
        	gm.setModeSet(true);
        	gm.calculScore(scorePlayerOne, scorePlayerTwo, gm);
        	break;
        case 4 :
        	System.out.println("Vous avez choisis le Sprint 2 - US2");
        	gm.setDeuce(true);
        	gm.setModeSet(true);
        	gm.setPtsEcarts(true);
        	gm.calculScore(scorePlayerOne, scorePlayerTwo, gm);
        	break;
		}
	}
}
