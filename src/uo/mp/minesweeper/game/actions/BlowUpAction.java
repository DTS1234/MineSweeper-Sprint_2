package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.utilities.CheckParameters;

public class BlowUpAction implements Action {

	private Board board;
	
	public BlowUpAction(Board board) {
		CheckParameters.check(board);
		this.board = board;
	}

	@Override
	public void activate() {
		board.markAsExploded();
		board.unveil();
	}
		
	
	
}
