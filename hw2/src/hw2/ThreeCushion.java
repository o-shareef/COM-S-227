package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Omran Shareef
 */
public class ThreeCushion {
	/**
	 * variable to hold what the current players cue ball is
	 */
	private BallType cueBall;
	/**
	 * variable letting you know what inning it is
	 */
	private int inning;
	/**
	 * variable for who won the lag
	 */
	private PlayerPosition lagWinner;
	/**
	 * variable for who is currently at the table, PLAYER_A or PLAYER_B
	 */
	private PlayerPosition inningPlayer;
	/**
	 * score of player A
	 */
	private int playerAScore;
	/**
	 * score of player B
	 */
	private int playerBScore;
	/**
	 * variable which lets you know if the shot was a bank shot
	 */
	private boolean isBankShot;
	/**
	 * variable letting you know if the shot is a break shot
	 */
	private boolean isBreakShot;
	/**
	 * variable to check if game is over
	 */
	private boolean isGameOver = false;
	/**
	 * variable for letting you know if inning started
	 */
	private boolean isInningStarted = false;
	/**
	 * variable letting you know if shot started, i.e. player took a shot
	 */
	private boolean isShotStarted = false;
	/**
	 * variable to track points to win
	 */
	private int pointsToWin;
	/**
	 * variable to check if the shot was valid
	 */
	private boolean isShotValid;
	/**
	 * variable to check if the shot the player took was a foul
	 */
	private boolean isShotFoul;
	/**
	 * variable to check if the red ball was struck
	 */
	private boolean redBallStruck;
	/**
	 * was the other player's (not currently taking the shot) cue ball struck during
	 * the shot?
	 */
	private boolean otherBallStruck;
	/**
	 * variable to track number of cushion impacts
	 */
	private int numOfCushionImpacts;
	/**
	 * true if cushions are touched 3 times for a bank shot
	 */
	private boolean potentialBank;

	/**
	 * constructs a new game of three-cusion billiards
	 * 
	 * @param lagWinner
	 * @param pointsToWin
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		this.lagWinner = lagWinner;
		this.pointsToWin = pointsToWin;

		this.inning = 1;
	}

	/**
	 * sets whether the player that won the lag chooses to break or not
	 * 
	 * @param selfBreak
	 * @param cueBall
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		// if player doesn't choose to break, then other player is the one starting
		if (!selfBreak && this.lagWinner == PLAYER_A) {
			this.inningPlayer = PLAYER_B;
		} else if (!selfBreak && this.lagWinner == PLAYER_B) {
			this.inningPlayer = PLAYER_A;
		} else {
			this.inningPlayer = this.lagWinner;
		}
		// set cue ball and break shot is true since first shot of game
		this.cueBall = cueBall;
		this.isBreakShot = true;
	}

	/**
	 * indicates the given ball has impacted the given cushion
	 */
	public void cueBallImpactCushion() {
		// only do something if the shot has been taken and game isn't over
		if (this.isShotStarted && !this.isGameOver) {

			this.numOfCushionImpacts++;

			// if cushions have been touched more that 3 times and other 2 balls been struck
			// then shot is valid
			if (this.numOfCushionImpacts >= 3 && this.redBallStruck && this.otherBallStruck) {
				this.isShotValid = true;
			}
		}
	}

	/**
	 * indicates the player's cue ball has struck the given ball
	 * 
	 * @param ball
	 */
	public void cueBallStrike(BallType ball) {
		// only do something if the shot has been taken and game isn't over
		if (this.isShotStarted && !this.isGameOver) {

			// if cushions have been touched more that 3 times and other 2 balls have NOT
			// been struck yet
			// then it is a potential bank shot
			if (this.numOfCushionImpacts == 3 && !this.redBallStruck && !this.otherBallStruck) {
				this.potentialBank = true;
			}

			if (ball == RED) {
				this.redBallStruck = true;
			}

			if (!getCueBall().equals(ball)) {
				this.otherBallStruck = true;
			}

			// if cushions have been touched more that 3 times and other 2 balls been struck
			// then shot is valid
			if (this.numOfCushionImpacts >= 3 && this.redBallStruck && this.otherBallStruck) {
				this.isShotValid = true;

				// if potential bank shot is true, at this point we know that other 2 balls have
				// been struck as well so it is a bank shot
				if (this.potentialBank) {
					this.isBankShot = true;
				}
			}
		}
	}

	/**
	 * indicates the cue stick has struck the given ball
	 * 
	 * @param ball
	 */
	public void cueStickStrike(BallType ball) {
		// Only do something if players have chosen their cue balls and game is not over
		if (getCueBall() != null && !this.isGameOver) {
			isShotFoul = false;
			this.isBankShot = false;

			// Start inning if not started
			if (!this.isInningStarted) {
				this.isInningStarted = true;
			}

			// Start shot if not already begun, else foul
			if (!this.isShotStarted) {
				this.isShotStarted = true;
			} else {
				foul();
				this.isInningStarted = false;
			}

			// Player struck wrong cue ball - foul
			if (!getCueBall().equals(ball)) {
				foul();
			}

		} else {
			this.isInningStarted = false;
		}
	}

	/**
	 * indicates the ball have stopped motion
	 */
	public void endShot() {
		// only do something if the shot has been taken and game isn't over
		if (!this.isGameOver && this.isShotStarted) {

			this.isShotStarted = false;
			this.isBreakShot = false;

			// If shot valid, add score. If points = pointsToWin, end game
			if (this.isShotValid) {
				if (getInningPlayer() == PLAYER_A) {
					this.playerAScore++;
					if (this.playerAScore == this.pointsToWin) {
						this.isGameOver = true;
					}
				} else {
					this.playerBScore++;
					if (this.playerBScore == this.pointsToWin) {
						this.isGameOver = true;
					}
				}

				// reset variables in relation to detecting valid, and bank shots
				this.redBallStruck = false;
				this.otherBallStruck = false;
				this.numOfCushionImpacts = 0;
				this.potentialBank = false;

				// if shot is not valid and is not a foul, rest variables, and switch other
				// player & other cue ball
			} else if (!isShotFoul) {
				this.inning++;
				this.isInningStarted = false;
				this.redBallStruck = false;
				this.otherBallStruck = false;
				this.numOfCushionImpacts = 0;
				this.potentialBank = false;

				if (getInningPlayer() == PLAYER_A) {
					this.inningPlayer = PLAYER_B;
				} else {
					this.inningPlayer = PLAYER_A;
				}

				if (getCueBall() == WHITE) {
					this.cueBall = YELLOW;
				} else {
					this.cueBall = WHITE;
				}
			}
		}
	}

	/**
	 * a foul ends the player's inning, no point for the shot
	 */
	public void foul() {
		// Only do something if players have chosen their cue balls and game is not over
		// If foul occurs, reset variables and switch to other player & other cue ball
		if (getCueBall() != null && !this.isGameOver) {
			isShotFoul = true;
			this.inning++;
			this.isInningStarted = false;
			this.redBallStruck = false;
			this.otherBallStruck = false;
			this.numOfCushionImpacts = 0;
			this.isBankShot = false;
			this.potentialBank = false;

			if (getInningPlayer() == PLAYER_A) {
				this.inningPlayer = PLAYER_B;
			} else {
				this.inningPlayer = PLAYER_A;
			}

			if (getCueBall() == WHITE) {
				this.cueBall = YELLOW;
			} else {
				this.cueBall = WHITE;
			}
		}
	}

	/**
	 * gets cue ball of current player
	 * 
	 * @return
	 */
	public BallType getCueBall() {
		return this.cueBall;
	}

	/**
	 * gets current inning
	 * 
	 * @return
	 */
	public int getInning() {
		return this.inning;
	}

	/**
	 * gets the current player
	 * 
	 * @return
	 */
	public PlayerPosition getInningPlayer() {
		return this.inningPlayer;
	}

	/**
	 * gets score of player A
	 * 
	 * @return
	 */
	public int getPlayerAScore() {
		return this.playerAScore;
	}

	/**
	 * gets score of player B
	 * 
	 * @return
	 */
	public int getPlayerBScore() {
		return this.playerBScore;
	}

	/**
	 * returns true if the recent shot was a bank shot
	 * 
	 * @return
	 */
	public boolean isBankShot() {
		return this.isBankShot;
	}

	/**
	 * returns true if currently on a break shot
	 * 
	 * @return
	 */
	public boolean isBreakShot() {
		return this.isBreakShot;
	}

	/**
	 * returns true if the game is over
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		return this.isGameOver;
	}

	/**
	 * returns true of player has taken the first shot
	 * 
	 * @return
	 */
	public boolean isInningStarted() {
		return this.isInningStarted;
	}

	/**
	 * returns true if player has taken a shot
	 * 
	 * @return
	 */
	public boolean isShotStarted() {
		return this.isShotStarted;
	}

	// TODO: EVERTHING ELSE GOES HERE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}
