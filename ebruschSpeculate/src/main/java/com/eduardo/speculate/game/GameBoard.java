package com.eduardo.speculate.game;

public class GameBoard {

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

	public boolean isOne() {
		return one;
	}


	public void setOne(boolean one) {
		this.one = one;
	}


	public boolean isTwo() {
		return two;
	}


	public void setTwo(boolean two) {
		this.two = two;
	}


	public boolean isThree() {
		return three;
	}


	public void setThree(boolean three) {
		this.three = three;
	}


	public boolean isFour() {
		return four;
	}


	public void setFour(boolean four) {
		this.four = four;
	}


	public boolean isFive() {
		return five;
	}


	public void setFive(boolean five) {
		this.five = five;
	}


	public int getSix() {
		return six;
	}


	public void addSix() {
		six ++;
	}

}
