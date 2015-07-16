package com.jodelahithit.main;

import java.util.Random;

import com.jodelahithit.objects.BasicEnemy;
import com.jodelahithit.objects.StraightEnemy;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();

	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void tick() {
		scoreKeep++;

		if (scoreKeep >= 100) {

			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			handler.addObject(new StraightEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), ID.StraightEnemy, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH), r.nextInt(Main.HEIGHT), ID.BasicEnemy, handler));
		}
	}
}
