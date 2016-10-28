package com.eduardo.speculate.game;

import java.io.Serializable;

public class GameBoard implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2L;
	private boolean one;
	private boolean two;
	private boolean three;
	private boolean four;
	private boolean five;
	private int six;


	public GameBoard() {

		setOne(true);
		setTwo(false);
		setThree(true);
		setFour(false);
		setFive(true);
		six = 0;

	}

	public boolean oneHasBall() {
		return one;
	}


	public void setOne(boolean one) {
		this.one = one;
	}


	public boolean twoHasBall() {
		return two;
	}


	public void setTwo(boolean two) {
		this.two = two;
	}


	public boolean threeHasBall() {
		return three;
	}


	public void setThree(boolean three) {
		this.three = three;
	}


	public boolean fourHasBall() {
		return four;
	}


	public void setFour(boolean four) {
		this.four = four;
	}


	public boolean fiveHasBall() {
		return five;
	}


	public void setFive(boolean five) {
		this.five = five;
	}


	public int getBallsInSix() {
		return six;
	}


	public void addSix() {
		six ++;
	}

}
