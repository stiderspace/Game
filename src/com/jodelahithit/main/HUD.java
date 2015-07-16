package com.jodelahithit.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	private Main main;

	public HUD(Main main) {
		this.main = main;
	}

	public static float HEALTH = 100;

	private float greenValue = 255, redValue = 0;

	private int score = 0;
	private int level = 1;

	public void tick() {
		HEALTH = Main.clamp(HEALTH, 0, 100);
		Main.clamp(greenValue, 0, 255);
		Main.clamp(redValue, 0, 255);

		greenValue = HEALTH * 2;
		redValue = 255 - greenValue;

		score++;
		if (HEALTH == 0) {
			main.goToMenu();
		}
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public static void resetHealth() {
		HEALTH = 100;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color((int) redValue, (int) greenValue, 0));
		g.fillRect(15, 15, (int) HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);

		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
}
