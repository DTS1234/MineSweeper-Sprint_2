package uo.mp.minesweeper.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import uo.mp.minesweeper.utilities.CheckParameters;
import uo.mp.minesweeper.utilities.StateOfTheGame;

public class Game {

	private Board gameBoard;
	private long startTime;

	public Game(Board board) {

		gameBoard = board;

	}

	public void play() {

		startTime = System.currentTimeMillis() * 1000;

		while (gameBoard.getStateOfTheGame() == StateOfTheGame.PLAYING) {

			showTime(startTime);
			showFlagsLeftMessage();
			printBoard(gameBoard.getStatus());
			showFlagsLeftMessage();
			performMove();

		}

	}

	private void showFlagsLeftMessage() {
		System.out.println("Flags left: " + gameBoard.getFlagsLeft());
	}

	private void showTime(long start) {
		System.out.println("Time : " + (System.currentTimeMillis() * 1000 - start) / 1000000 + " seconds");
	}

	private void showActionMessage(int x, int y) {

		if (checkLoose()) {

			showLooseMessages();

		} else if (checkWin()) {

			showWinMessages();

		}

	}

	private boolean checkLoose() {
		return gameBoard.getStateOfTheGame() == StateOfTheGame.LOST;
	}

	private boolean checkWin() {
		return gameBoard.getStateOfTheGame() == StateOfTheGame.WON;
	}

	private void showLooseMessages() {
		System.out.println("BOOOM!!!!");
		printBoard(gameBoard.getStatus());
		System.out.println("GAME OVER");
	}

	private void showWinMessages() {
		System.out.println("YOU WON !");
		gameBoard.unveil();
		printBoard(gameBoard.getStatus());
		System.out.println("GAME OVER");
	}

	private void printBoard(char[][] charArray) {

		for (int i = 0; i < charArray.length; i++) {
			for (int j = 0; j < charArray[0].length; j++) {
				System.out.print("[" + charArray[i][j] + "]");
			}
			System.out.println();
		}
	}

	private void performMove() {

		int row = firstCoordinateSelection();
		int col = secondCoordinateSelection();
		char move = moveSelection();

		switch (move) {
		case 's':
			gameBoard.stepOn(row, col);
			showActionMessage(row, col);
			break;
		case 'f':

			gameBoard.flag(row, col);
			showActionMessage(row, col);
			break;
		case 'u':
			gameBoard.unflag(row, col);
			break;
		}
	}

	private int firstCoordinateSelection() {
		System.out.println("Pick first cordinate :");
		char rowChar = readChar();

		CheckParameters.check(rowChar);
		Integer row = Integer.parseInt(rowChar + "");

		return row;

	}

	private int secondCoordinateSelection() {

		System.out.println("Pick second cordinate :");
		char colChar = readChar();

		CheckParameters.check(colChar);
		Integer col = Integer.parseInt(colChar + "");

		return col;

	}

	private char moveSelection() {

		System.out.println("movement (s|f|u) :");
		char move = readChar();

		return move;
	}

	private char readChar() {

		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		char inputChar = ' ';

		try {
			inputChar = (char) reader.read();
		} catch (IOException e) {
			System.out.println("An error occured while reading data!");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("catched");
		}
		return inputChar;
	}

}
