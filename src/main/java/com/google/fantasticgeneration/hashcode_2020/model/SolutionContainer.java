package com.google.fantasticgeneration.hashcode_2020.model;

import java.util.List;

public class SolutionContainer {

	public List<Library> libraries;

	public SolutionContainer(List<Library> libraries, int score) {
		super();
		this.libraries = libraries;
		this.score = score;
	}

	private int score;

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		// TODO
		return "";
	}

}